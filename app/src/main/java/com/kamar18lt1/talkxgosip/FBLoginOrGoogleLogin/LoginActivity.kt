package com.kamar18lt1.talkxgosip.FBLoginOrGoogleLogin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.TextView
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.signin.SignIn
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.kamar18lt1.talkxgosip.FeedsGosip.GosipActivity
import com.kamar18lt1.talkxgosip.NicknameRegister.RegisterNicknameActivity
import com.kamar18lt1.talkxgosip.PreferenceHelper.PreferenceHelper
import com.kamar18lt1.talkxgosip.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), GoogleApiClient.OnConnectionFailedListener, OnUserExistListener {
    //    Shared Preference
    lateinit var preferenceHelper: PreferenceHelper

    //utk kebutuhan google sign in
    lateinit var mGoogleSignInClient : GoogleSignInClient

    val RC_SIGN_IN = 0
    //firebase
    val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //textview google sign in
        var text = googleLogin.getChildAt(0) as TextView
        text.text = "Login With Google"


        //SharedPreference Check Login or Not
        preferenceHelper = PreferenceHelper(applicationContext)
        if (preferenceHelper.isUserLogin()){
            var intent = Intent(this,GosipActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        googleInit()
        googleLogin.setOnClickListener {
            signIn()
        }
    }


    //Google Inititialisation Sign In
    private fun googleInit(){
        var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this,gso)

    }

    private fun signIn(){
        var signInIntent = mGoogleSignInClient.signInIntent
        val RC_SIGN_IN = 0
        startActivityForResult(signInIntent,RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            task.addOnCompleteListener {
                val id_user = task.result.id
                var account = it.result
                preferenceHelper.setEmailRegistered(id_user!!)
                checkUser(id_user,this)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if(preferenceHelper.isUserLogin()){
            var intent = Intent(this,GosipActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }
    }


    override fun onConnectionFailed(p0: ConnectionResult) {

    }



    fun checkUser(string: String, listener: OnUserExistListener){
        db.collection("user").whereEqualTo("id",string).get().addOnCompleteListener {
            if (it.result.documents.size ==1){
                listener.onExist(it.result.documents[0]["nickname"] as String,it.result.documents[0]["status"] as String)
            }else
                listener.onNotExist()
        }
    }

    override fun onExist(string: String,status: String) {
        preferenceHelper.setLoginUser(string,status)
        Log.e("kesininya dia??","wkwkwk")
        Log.e("info User",string+" adalah "+status)
        var intent = Intent(this,GosipActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        startActivity(intent)
    }

    override fun onNotExist() {
        Log.e("atau kesini","wkwkkwkw")
        var intent = Intent(this,RegisterNicknameActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        startActivity(intent)
    }

}
