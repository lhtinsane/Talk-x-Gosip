package com.kamar18lt1.talkxgosip.FeedsGosip

import android.support.v7.widget.LinearLayoutManager
import android.util.Log

import android.widget.Toast
import com.kamar18lt1.talkxgosip.Model.Post
import com.kamar18lt1.talkxgosip.Model.PostFromDB
import com.kamar18lt1.talkxgosip.Model.User
import com.kamar18lt1.talkxgosip.PreferenceHelper.PreferenceHelper
import kotlinx.android.synthetic.main.activity_gosip.*

class GosipPresenter(var view: GosipActivity) : GosipContract.GosipPresenterInterface,GosipContract.GosipInteractorInterface.onGosipPostListener,GosipContract.GosipInteractorInterface.onLoadPostListener{
    override fun onLikeSucces() {

    }

    override fun onDislikeSucces() {

    }

    override fun likePostBy(idpos:String, nickname: User) {
        interactor.likePost(idpos,nickname,this)
    }

    override fun dislikePostBy(idpos:String, nickname: User) {
        interactor.dislikePost(idpos,nickname,this)
    }

    var interactor = GosipInteractor()

    //interactor for loading gossip
    override fun onLoadSucces(listPost: ArrayList<PostFromDB>): ArrayList<PostFromDB> {
        if (listPost.isEmpty()){

        }else{
            try {
                view.refresh.isRefreshing = false
                view.recycler_view.setHasFixedSize(true)
                val layoutManager = LinearLayoutManager(view)
                view.recycler_view.layoutManager = layoutManager
                var adapter = GosipAdapter(view,listPost)
                view.recycler_view.adapter = adapter
            }catch (e : Exception){
                Log.e("error", e.printStackTrace().toString())
            }
            setRecyclerView(listPost)
        }
        return listPost
    }

    fun setRecyclerView(listPost: ArrayList<PostFromDB>) {
        view.recycler_view.setHasFixedSize(true)
        view.recycler_view.layoutManager = LinearLayoutManager(view)
        view.recycler_view.adapter =GosipAdapter(view,listPost)
    }

    override fun onLoadFailure() {
        Toast.makeText(view.applicationContext,"Failed to Load Data",Toast.LENGTH_SHORT).show()
    }

    override fun loadGosip() {
        interactor.loadPost(this)
    }



    //Interactor for saving post
    override fun onSucces() {
        Toast.makeText(view.applicationContext,"Anda berhasil menyimpan post",Toast.LENGTH_SHORT).show()
    }

    override fun onFailure() {
        Toast.makeText(view.applicationContext,"Gagal menyimpan",Toast.LENGTH_SHORT).show()
    }

    override fun postGosip(string: String) {
        var preferenceHelper = PreferenceHelper(view)
        var pending = Post(preferenceHelper.isLogin.getString(preferenceHelper.USER_NICKNAME,null),string,0,0,0)
        interactor.savePost(pending,this)
    }
}