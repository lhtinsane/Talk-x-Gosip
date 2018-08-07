package com.kamar18lt1.talkxgosip.FeedsGosip

import com.kamar18lt1.talkxgosip.Model.Post

object GosipContract {

    interface GosipPresenterInterface{
        fun postGosip(string: String)
        fun loadGosip()
    }

    interface GosipViewInterface{
        fun showDialog()
        fun dialogPostError()
    }

    interface GosipInteractorInterface{
        interface onGosipPostListener{
            fun onSucces()
            fun onFailure()
        }
        interface onLoadPostListener{
            fun onLoadSucces(listPost: ArrayList<Post>):ArrayList<Post>
            fun onLoadFailure()
        }

        fun loadPost(listener : onLoadPostListener)
        fun savePost(string: String, listener : onGosipPostListener)
    }

}