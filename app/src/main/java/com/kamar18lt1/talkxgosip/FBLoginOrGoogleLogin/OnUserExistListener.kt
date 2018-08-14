package com.kamar18lt1.talkxgosip.FBLoginOrGoogleLogin

interface OnUserExistListener {

    fun onExist(string: String,status : String)
    fun onNotExist()
}