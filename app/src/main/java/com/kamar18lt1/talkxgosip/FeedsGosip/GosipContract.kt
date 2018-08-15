package com.kamar18lt1.talkxgosip.FeedsGosip

import com.kamar18lt1.talkxgosip.Model.Post
import com.kamar18lt1.talkxgosip.Model.PostFromDB
import com.kamar18lt1.talkxgosip.Model.User

object GosipContract {

    interface GosipPresenterInterface{
        fun postGosip(string: String)
        fun loadGosip()
        fun likePostBy(idpos:String, nickname: User)
        fun dislikePostBy(idpos:String, nickname: User)
    }

    interface GosipViewInterface{
        fun showDialog()
        fun dialogPostError()
    }

    interface GosipInteractorInterface{
        interface onGosipPostListener{
            fun onSucces()
            fun onFailure()
            fun onLikeSucces()
            fun onDislikeSucces()
        }
        interface onLoadPostListener{
            fun onLoadSucces(listPost: ArrayList<PostFromDB>):ArrayList<PostFromDB>
            fun onLoadFailure()
        }
        fun likePost(idPos:String, nickname: User, listener: onGosipPostListener)
        fun dislikePost(idPos:String, nickname: User, listener: onGosipPostListener)
        fun loadPost(listener : onLoadPostListener)
        fun savePost(string: Post, listener: onGosipPostListener)
    }

}