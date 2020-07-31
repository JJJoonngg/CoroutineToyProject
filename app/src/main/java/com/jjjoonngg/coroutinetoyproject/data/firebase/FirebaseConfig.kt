package com.jjjoonngg.coroutinetoyproject.data.firebase

import com.google.firebase.firestore.FirebaseFirestore

object FirebaseConfig {
    fun getFireStoreInstance() = FirebaseFirestore.getInstance()
}

const val postId = "postId"
const val title = "title"
const val content = "content"
const val date = "date"