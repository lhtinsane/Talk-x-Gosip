package com.kamar18lt1.talkxgosip

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.kamar18lt1.talkxgosip.FBLoginOrGoogleLogin.LoginActivity
import com.kamar18lt1.talkxgosip.FeedsGosip.GosipActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //splash screen dimulai
        var handler = Handler()
        handler.postDelayed({
            startActivity(Intent(this@MainActivity,LoginActivity::class.java))
            finish()
        },3000L)
    }
}
