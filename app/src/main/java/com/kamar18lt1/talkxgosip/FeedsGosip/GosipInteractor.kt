package com.kamar18lt1.talkxgosip.FeedsGosip

import com.google.firebase.firestore.FirebaseFirestore
import com.kamar18lt1.talkxgosip.Model.Post
import com.kamar18lt1.talkxgosip.Model.PostFromDB
import com.kamar18lt1.talkxgosip.Model.User

class GosipInteractor() :GosipContract.GosipInteractorInterface {
    override fun likePost(idPos: String, nickname: User, listener: GosipContract.GosipInteractorInterface.onGosipPostListener) {

        db.collection("post").document(idPos)
    }

    override fun dislikePost(idPos: String, nickname: User, listener: GosipContract.GosipInteractorInterface.onGosipPostListener) {
        db.collection("post").document(idPos).update("dislikeBy", nickname).addOnCompleteListener {

        }
    }

    var db = FirebaseFirestore.getInstance()


    //Loading Interactor
    override fun loadPost(listener: GosipContract.GosipInteractorInterface.onLoadPostListener){
        var listPost =ArrayList<PostFromDB>()

        db.collection("post").get().addOnCompleteListener {
            if (it.result.isEmpty){
            }else{
                for (documents in it.result){
                    if (documents.exists()){
                        listPost.add(PostFromDB(documents.id,documents["nickname"].toString(),documents["post"].toString(),documents["date"] as Long,
                                documents["numLike"] as Long,documents["numDislike"] as Long))
                    }
                }
                listener.onLoadSucces(listPost)
            }
        }.addOnFailureListener {
            listener.onLoadFailure()
        }
    }


    //saving interactor
    override fun savePost(string: Post, listener: GosipContract.GosipInteractorInterface.onGosipPostListener) {
        db.collection("pending_post").document().set(string)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        listener.onSucces()
                    }
                }.addOnFailureListener {
                    listener.onFailure()
                }
    }
}