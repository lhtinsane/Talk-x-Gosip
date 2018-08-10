package com.kamar18lt1.talkxgosip.AdminPackage

import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.kamar18lt1.talkxgosip.Model.PendingPost

class AdminInteractor : AdminContract.AdminInteractor {
    override fun savePost(post: PendingPost, listener: AdminContract.AdminInteractor.InteractorListener) {
        db.collection("post").document().set(post).addOnCompleteListener {
            if (it.isSuccessful){
                listener.onSavePost()
            }else
                listener.failSavePost()
        }
    }

    var db = FirebaseFirestore.getInstance()

    override fun loadData(listener: AdminContract.AdminInteractor.InteractorListener) {
        db.collection("pending_post").get()
                .addOnCompleteListener {
                    if (it.isSuccessful){
                        var dataList = ArrayList<PendingPost>()
                        for (document in it.result){
                            var pendingPost = PendingPost(document["nickname"].toString(),document["post"].toString(),
                                    document["date"] as Long,document["numLike"] as Long, document["numDislike"] as Long,
                                    document["status"].toString())
                            dataList.add(pendingPost)
                        }
                        listener.onSucces(dataList)
                    }else
                        listener.onFailed()
                }
    }
}