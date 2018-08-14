package com.kamar18lt1.talkxgosip.AdminPackage

import android.app.Activity
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import com.kamar18lt1.talkxgosip.FBLoginOrGoogleLogin.LoginActivity
import com.kamar18lt1.talkxgosip.FeedsGosip.GosipActivity
import com.kamar18lt1.talkxgosip.Model.PendingPost
import kotlinx.android.synthetic.main.activity_admin.*

class AdminPresenter(var activity : Activity) : AdminContract.AdminPresenterInf , AdminContract.AdminInteractor.InteractorListener{
    override fun onRemove() {
        activity.recycler_pending_post.adapter.notifyDataSetChanged()
    }

    override fun removeFromPending(post: PendingPost) {
        var interactor = AdminInteractor()
        interactor.removeFromPending(post.idPost,this)
    }

    override fun onSavePost() {

    }

    override fun failSavePost() {

    }

    override fun acceptPost(post: PendingPost) {
        var interactor = AdminInteractor()
        interactor.savePost(post,this)
        activity.recycler_pending_post.adapter.notifyDataSetChanged()
        var intent = Intent(activity, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        activity.startActivity(intent)
    }

    override fun onSucces(dataList: ArrayList<PendingPost>) {
        activity.recycler_pending_post.setHasFixedSize(true)
        activity.recycler_pending_post.layoutManager = LinearLayoutManager(activity)
        activity.recycler_pending_post.adapter = PendingPostAdapter(activity,dataList)
    }

    override fun onFailed() {

    }



    override fun loadPendingPost() {
        var interactor = AdminInteractor()
        interactor.loadData(this)
    }


}