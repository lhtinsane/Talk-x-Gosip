package com.kamar18lt1.talkxgosip.Model

data class PostFromDB(var docId :String,var nickname :String, var post: String, var date : Long,var numLike : Long,var numDislike : Long)