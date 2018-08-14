package com.kamar18lt1.talkxgosip.AdminPackage

import android.app.Activity
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kamar18lt1.talkxgosip.Model.PendingPost
import com.kamar18lt1.talkxgosip.Model.Post
import com.kamar18lt1.talkxgosip.R
import kotlinx.android.synthetic.main.list_pending_post.view.*

class PendingPostAdapter(var activity: Activity, var dataList : ArrayList<PendingPost>) : RecyclerView.Adapter<PendingPostAdapter.ViewHolder>() {
    var adminPresenter = AdminPresenter(activity)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(activity).inflate(R.layout.list_pending_post,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pending_post.text = dataList[position].post
        holder.status.text = dataList[position].status
        holder.id_pos = dataList[position].idPost
        holder.itemView.layout_accept.setOnClickListener {
            adminPresenter.acceptPost(dataList[position])
            adminPresenter.removeFromPending(dataList[position])
        }
    }


    class ViewHolder(view :View) : RecyclerView.ViewHolder(view){
        var pending_post = view.pending_post
        var status = view.status
        var id_pos : String? = null
    }

}