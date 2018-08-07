package com.kamar18lt1.talkxgosip.NicknameRegister

import android.content.Intent
import com.kamar18lt1.talkxgosip.FeedsGosip.GosipActivity
import com.kamar18lt1.talkxgosip.PreferenceHelper.PreferenceHelper

class RegisterPresenter(var activity: RegisterNicknameActivity) : RegisterNicknameContract.RegisterPresenterInterface, RegisterNicknameContract.RegisterInteractorInterface.OnRegisterListener, RegisterNicknameContract.RegisterInteractorInterface.OnLoadListener {
    override fun onSuccess(string: String) {
        preferenceHelper.setLoginUser(string)
        activity.startActivity(Intent(activity,GosipActivity::class.java))
    }

    override fun onFailed() {

    }

    override fun getUser(string: String){
        interactor.loadData(string,this)
    }

    val interactor = RegisterInteractor()
    var preferenceHelper = PreferenceHelper(activity)
    override fun registerComplete(nickname :String) {
        preferenceHelper.setLoginUser(nickname)
        activity.startActivity(Intent(activity,GosipActivity::class.java))
    }

    override fun registerFailed() {

    }

    override fun register(string: String) {
        var id = preferenceHelper.isLogin.getString("email",null)
        interactor.registerNickname(string,id,this)
    }
}