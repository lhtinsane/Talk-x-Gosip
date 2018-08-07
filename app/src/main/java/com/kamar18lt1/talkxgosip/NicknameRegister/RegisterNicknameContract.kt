package com.kamar18lt1.talkxgosip.NicknameRegister

object RegisterNicknameContract {

    interface RegisterPresenterInterface{
        fun register(string: String)
        fun getUser(string: String)
    }

    interface RegisterInteractorInterface{
        fun registerNickname(string: String,id:String,listener : OnRegisterListener)
        fun loadData(id: String,listener: OnLoadListener)
        interface OnRegisterListener{
            fun registerComplete(string: String)
            fun registerFailed()
        }
        interface OnLoadListener{
            fun onSuccess(string: String)
            fun onFailed()
        }
    }

}