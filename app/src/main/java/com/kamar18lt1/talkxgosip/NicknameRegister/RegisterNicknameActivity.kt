package com.kamar18lt1.talkxgosip.NicknameRegister

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.kamar18lt1.talkxgosip.FBLoginOrGoogleLogin.LoginActivity
import com.kamar18lt1.talkxgosip.FeedsGosip.GosipActivity
import com.kamar18lt1.talkxgosip.PreferenceHelper.PreferenceHelper
import com.kamar18lt1.talkxgosip.R
import kotlinx.android.synthetic.main.activity_register_nickname.*

class RegisterNicknameActivity : AppCompatActivity() {

    //shared preference
    lateinit var preferenceHelper: PreferenceHelper
    lateinit var presenter: RegisterPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_nickname)

        //presenter
        presenter = RegisterPresenter(this)



        //sharedPreference
        preferenceHelper = PreferenceHelper(this)
        //check existing account
        presenter.getUser(preferenceHelper.isLogin.getString("email",null))

        if (preferenceHelper.isUserLogin()){
            var intent = Intent(this,GosipActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }else if (!preferenceHelper.getEmailRegistered()){
            var intent = Intent(this,RegisterNicknameActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        register_btn.setOnClickListener {
            if (nickname_edt.text == null){
                nickname_edt.setError("Nickname Tidak Boleh Kosong!")
            }else{
                presenter.register(nickname_edt.text.toString())
            }
        }

    }

}
