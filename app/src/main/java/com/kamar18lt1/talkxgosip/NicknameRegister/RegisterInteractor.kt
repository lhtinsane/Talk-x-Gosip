package com.kamar18lt1.talkxgosip.NicknameRegister

import com.google.firebase.firestore.FirebaseFirestore
import com.kamar18lt1.talkxgosip.Model.User

class RegisterInteractor : RegisterNicknameContract.RegisterInteractorInterface {
    override fun loadData(id: String, listener: RegisterNicknameContract.RegisterInteractorInterface.OnLoadListener) {
        db.collection("user").whereEqualTo("id",id).get().addOnSuccessListener {
            if (it.documents.size == 1){
                listener.onSuccess(it.documents[0]["id"].toString())
            }
        }
                .addOnFailureListener {
                    listener.onFailed()
                }
    }

    val db = FirebaseFirestore.getInstance()

    override fun registerNickname(string: String,id:String,listener: RegisterNicknameContract.RegisterInteractorInterface.OnRegisterListener) {
        db.collection("user").document().set(User(id,string,"normal")).addOnSuccessListener {
            listener.registerComplete(string)
        }
                .addOnFailureListener {
                    listener.registerFailed()
                }
    }
}