package com.kamar18lt1.talkxgosip.AdminPackage

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.kamar18lt1.talkxgosip.R
import kotlinx.android.synthetic.main.activity_admin.*

class AdminActivity : AppCompatActivity() {
    var presenter = AdminPresenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)
        presenter.loadPendingPost()

        //on refresh
        admin_refresh.setOnRefreshListener {
            admin_refresh.isRefreshing = true
            presenter.loadPendingPost()
        }
    }
}
