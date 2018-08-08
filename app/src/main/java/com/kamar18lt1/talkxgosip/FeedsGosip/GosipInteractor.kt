package com.kamar18lt1.talkxgosip.FeedsGosip

import android.support.v7.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.kamar18lt1.talkxgosip.Model.Post

class GosipInteractor() :GosipContract.GosipInteractorInterface {
    var db = FirebaseFirestore.getInstance()


    //Loading Interactor
    override fun loadPost(listener: GosipContract.GosipInteractorInterface.onLoadPostListener){
        var listPost =ArrayList<Post>()

        db.collection("post").get().addOnCompleteListener {
            if (it.isSuccessful){
                for (documents in it.result){
                    listPost.add(Post(documents["nickname"].toString(),documents["post"].toString(),documents["date"] as Long,
                            documents["numLike"] as Long,documents["numDislike"] as Long))
                }
                listener.onLoadSucces(listPost)
            }
        }.addOnFailureListener {
            listener.onLoadFailure()
        }
    }


    //saving interactor
    override fun savePost(string: String, listener: GosipContract.GosipInteractorInterface.onGosipPostListener) {
        db.collection("pending_post").document().set(Post("xxx",string,0,0,0))
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        listener.onSucces()
                    }
                }.addOnFailureListener {
                    listener.onFailure()
                }
    }
}