package com.kamar18lt1.talkxgosip.AdminPackage

import com.kamar18lt1.talkxgosip.Model.PendingPost

object AdminContract {

    interface AdminPresenterInf{
        fun loadPendingPost()
        fun acceptPost(post : PendingPost)
        fun removeFromPending(post: PendingPost)
    }

    interface AdminInteractor{
        interface InteractorListener{
            fun onSucces(dataList : ArrayList<PendingPost>)
            fun onFailed()
            fun onSavePost()
            fun failSavePost()
            fun onRemove()
        }
        fun removeFromPending(string: String,listener: InteractorListener)
        fun savePost(post: PendingPost,listener: InteractorListener)
        fun loadData(listener : InteractorListener)
    }

}