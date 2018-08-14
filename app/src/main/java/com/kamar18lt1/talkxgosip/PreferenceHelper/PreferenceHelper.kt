package com.kamar18lt1.talkxgosip.PreferenceHelper

import android.content.Context
import android.util.Log

class PreferenceHelper(context : Context) {
    val PREFERENCE = "talk_x_gosip"
    val IS_USER_LOGIN = "is_user_login"
    val USER_NICKNAME = "user_nickname"
    val EMAIL_REGISTER = "email_register"
    val USER_STATUS = "user_status"
    val EMAIL = "email"

    val isLogin = context.getSharedPreferences(PREFERENCE,Context.MODE_PRIVATE)

    val preferenceEditor = isLogin.edit()

    fun setEmailRegistered(string: String){
        preferenceEditor.putString(EMAIL, string)
        preferenceEditor.putBoolean(EMAIL_REGISTER,true)
        preferenceEditor.apply()
    }

    fun getEmailRegistered() : Boolean{
        Log.e("EMail", isLogin.getString(EMAIL,null))
        return isLogin.getBoolean(EMAIL_REGISTER,false)
    }

    fun setLoginUser(nickname: String,status : String) {
        preferenceEditor.putBoolean(IS_USER_LOGIN,true)
        preferenceEditor.putString(USER_NICKNAME,nickname)
        preferenceEditor.putString(USER_STATUS,status)
        preferenceEditor.apply()
    }

    fun isUserLogin() : Boolean{
        return isLogin.getBoolean(IS_USER_LOGIN,false)
    }

    fun isUserAdmin() : Boolean{
        if (isLogin.getString(USER_STATUS,"normal") == "admin"){
            return true
        }else
            return false
    }

    fun destroyLogin(){
        preferenceEditor.clear()
        preferenceEditor.apply()
    }
}