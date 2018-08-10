package com.kamar18lt1.talkxgosip.FeedsGosip

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kamar18lt1.talkxgosip.Model.Post
import com.kamar18lt1.talkxgosip.R
import kotlinx.android.synthetic.main.list_item.view.*

class GosipAdapter(val context: Context,val listGosip : ArrayList<Post>) : RecyclerView.Adapter<GosipAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item,parent,false))
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
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nickname.text = listGosip[position].nickname
        holder.post.text = listGosip[position].post
        holder.dates.text = listGosip[position].date.toString()
        holder.numLike.text = listGosip[position].numLike.toString()
        holder.numDislike.text = listGosip[position].numDislike.toString()
    }
}