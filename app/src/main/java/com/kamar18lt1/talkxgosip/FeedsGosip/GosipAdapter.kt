package com.kamar18lt1.talkxgosip.FeedsGosip

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kamar18lt1.talkxgosip.Model.PostFromDB
import com.kamar18lt1.talkxgosip.Model.User
import com.kamar18lt1.talkxgosip.PreferenceHelper.PreferenceHelper
import com.kamar18lt1.talkxgosip.R
import kotlinx.android.synthetic.main.list_item.view.*

class GosipAdapter(val activity: GosipActivity, val listGosip: ArrayList<PostFromDB>) : RecyclerView.Adapter<GosipAdapter.ViewHolder>() {
    var presenter = GosipPresenter(activity)
    var preferenceHelper = PreferenceHelper(activity)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        return ViewHolder(LayoutInflater.from(activity).inflate(R.layout.list_item,parent,false))
    }

    override fun getItemCount(): Int {
        return listGosip.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

        var nickname = view.nickname!!
        var post = view.post!!
        var dates = view.date!!
        var numLike = view.num_like!!
        var numDislike = view.num_dislike!!
        var actionLike = view.like
        var actionDislike = view.dislike
        var postId : String? = null
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nickname.text = listGosip[position].nickname
        holder.postId = listGosip[position].docId
        holder.post.text = listGosip[position].post
        holder.dates.text = listGosip[position].date.toString()
        holder.numLike.text = listGosip[position].numLike.toString()
        holder.numDislike.text = listGosip[position].numDislike.toString()
        holder.actionDislike.setOnClickListener {
            var user = User(preferenceHelper.isLogin.getString("email",null),preferenceHelper.isLogin.getString("user_nickname",null),preferenceHelper.isLogin.getString("user_status",null))
            presenter.dislikePostBy(holder.postId!!,user)
        }
        holder.actionLike.setOnClickListener{
            var user = User(preferenceHelper.isLogin.getString("email",null),preferenceHelper.isLogin.getString("user_nickname",null),preferenceHelper.isLogin.getString("user_status",null))
            presenter.likePostBy(holder.postId!!,user)
        }
    }
}