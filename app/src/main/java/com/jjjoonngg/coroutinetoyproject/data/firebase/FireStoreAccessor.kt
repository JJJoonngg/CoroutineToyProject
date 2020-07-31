package com.jjjoonngg.coroutinetoyproject.data.firebase

import android.util.Log
import com.google.firebase.firestore.SetOptions
import com.jjjoonngg.coroutinetoyproject.data.db.TodoDTO
import com.jjjoonngg.coroutinetoyproject.util.*
import kotlinx.coroutines.tasks.await

object FireStoreAccessor {

    private val fireStore = FirebaseConfig.getFireStoreInstance()

    fun sendTodoData(data: TodoDTO) {
        data.postId = fireStore.collection(TODO).document().id
        fireStore.collection(TODO)
            .document(data.postId!!)
            .set(data, SetOptions.merge())
            .addOnSuccessListener {
                Log.d(TAG, "Success")
            }
            .addOnFailureListener {
                Log.d(TAG, "failed")
            }

    }

    suspend fun readTodo(): MutableList<TodoDTO> {
        val datas = mutableListOf<TodoDTO>()

        fireStore.collection(TODO)
            .whereEqualTo(type, TODO)
            .get()
            .addOnSuccessListener { items ->
                for (item in items) {
                    val postId = item.data[postId].toString()
                    val title = item.data[title].toString()
                    val content = item.data[content].toString()
                    val date = item.data[date].toString()
                    datas.add(
                        TodoDTO(
                            postId,
                            title,
                            content,
                            date,
                            TODO
                        )
                    )
                }
            }
            .await()

        return datas
    }

    suspend fun readDoing(): MutableList<TodoDTO> {
        val datas = mutableListOf<TodoDTO>()

        fireStore.collection(TODO)
            .whereEqualTo(type, DOING)
            .get()
            .addOnSuccessListener { items ->
                for (item in items) {
                    val postId = item.data[postId].toString()
                    val title = item.data[title].toString()
                    val content = item.data[content].toString()
                    val date = item.data[date].toString()
                    datas.add(
                        TodoDTO(
                            postId,
                            title,
                            content,
                            date,
                            DOING
                        )
                    )
                }
            }
            .await()

        return datas
    }

    suspend fun readDone(): MutableList<TodoDTO> {
        val datas = mutableListOf<TodoDTO>()

        fireStore.collection(TODO)
            .whereEqualTo(type, DONE)
            .get()
            .addOnSuccessListener { items ->
                for (item in items) {
                    val postId = item.data[postId].toString()
                    val title = item.data[title].toString()
                    val content = item.data[content].toString()
                    val date = item.data[date].toString()
                    datas.add(
                        TodoDTO(
                            postId,
                            title,
                            content,
                            date,
                            DONE
                        )
                    )
                }
            }
            .await()

        return datas
    }

    suspend fun sendToDoingFromTodo(data: TodoDTO) {
        fireStore.collection(TODO)
            .document(data.postId!!)
            .update(type, DOING)
            .addOnSuccessListener {
                Log.d(TAG, "Success")
            }
            .addOnFailureListener {
                Log.d(TAG, "failed")
            }.await()
    }

    suspend fun sendToTodoFromDoing(data: TodoDTO) {
        fireStore.collection(TODO)
            .document(data.postId!!)
            .update(type, TODO)
            .addOnSuccessListener {
                Log.d(TAG, "Success")
            }
            .addOnFailureListener {
                Log.d(TAG, "failed")
            }.await()
    }

    suspend fun sendToDoneFromDoing(data: TodoDTO) {
        fireStore.collection(TODO)
            .document(data.postId!!)
            .update(type, DONE)
            .addOnSuccessListener {
                Log.d(TAG, "Success")
            }
            .addOnFailureListener {
                Log.d(TAG, "failed")
            }.await()
    }

    suspend fun sendToDoingFromDone(data: TodoDTO) {
        fireStore.collection(TODO)
            .document(data.postId!!)
            .update(type, DOING)
            .addOnSuccessListener {
                Log.d(TAG, "Success")
            }
            .addOnFailureListener {
                Log.d(TAG, "failed")
            }.await()
    }
}