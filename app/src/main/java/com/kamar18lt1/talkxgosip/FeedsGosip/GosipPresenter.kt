package com.kamar18lt1.talkxgosip.FeedsGosip

import android.support.v7.widget.LinearLayoutManager
import android.util.Log

import android.widget.Toast
import com.kamar18lt1.talkxgosip.Model.Post
import kotlinx.android.synthetic.main.activity_gosip.*

class GosipPresenter(var view: GosipActivity) : GosipContract.GosipPresenterInterface,GosipContract.GosipInteractorInterface.onGosipPostListener,GosipContract.GosipInteractorInterface.onLoadPostListener{

    var interactor = GosipInteractor()

    //interactor for loading gossip
    override fun onLoadSucces(listPost: ArrayList<Post>): ArrayList<Post> {
        Log.e("bisa ga",listPost[0].nickname)
        if (listPost.isEmpty()){

        }else{
            try {
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

    fun setRecyclerView(listPost: ArrayList<Post>) {
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
        interactor.savePost(string,this)
    }
}