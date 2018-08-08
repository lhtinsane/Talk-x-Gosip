package com.kamar18lt1.talkxgosip.FeedsGosip

import android.app.Dialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.Window
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.kamar18lt1.talkxgosip.FBLoginOrGoogleLogin.LoginActivity
import com.kamar18lt1.talkxgosip.PreferenceHelper.PreferenceHelper
import com.kamar18lt1.talkxgosip.R
import kotlinx.android.synthetic.main.activity_gosip.*
import kotlinx.android.synthetic.main.post_dialog.*
import java.util.zip.Inflater

class GosipActivity : AppCompatActivity(),GosipContract.GosipViewInterface {
    lateinit var mGoogleSignInClient : GoogleSignInClient
    lateinit var presenter : GosipPresenter
    lateinit var preferenceHelper : PreferenceHelper
    override fun showDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.getDefaultFeatures(this))
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.post_dialog)
        dialog.button_post.setOnClickListener {
            if (dialog.edit_post.text.toString().isEmpty()){
                dialog.edit_post.setError("Text tidak boleh kosong")
            }else{
                posting(dialog.edit_post.text.toString())
                dialog.hide()
            }
        }
        dialog.show()
    }

    //posting function
    fun posting(string: String){
            presenter.postGosip(string)
            Snackbar.make(gosipLayout,"Postingan anda sedang di verifikasi",Snackbar.LENGTH_SHORT)
    }

    override fun dialogPostError() {

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.logout,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == R.id.logout) {
            preferenceHelper.destroyLogin()
            signOut()
            revokeAccess()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gosip)

        //instantiate presenter
        presenter = GosipPresenter(this)
        presenter.loadGosip()

        //Google Signin
        googleInit()

        //preference helper
        preferenceHelper = PreferenceHelper(this)

        button_dialog.setOnClickListener{
            showDialog()
        }
    }

    private fun googleInit(){
        var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this,gso)

    }

    private fun signOut(){
        mGoogleSignInClient.signOut().addOnCompleteListener {
            if (it.isSuccessful){
                var intent = Intent(this,LoginActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                startActivity(intent)
            }
        }
    }

    private fun revokeAccess(){
        mGoogleSignInClient.revokeAccess().addOnCompleteListener {

        }
    }

}
