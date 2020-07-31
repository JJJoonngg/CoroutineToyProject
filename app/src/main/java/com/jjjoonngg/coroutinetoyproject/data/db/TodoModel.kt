package com.jjjoonngg.coroutinetoyproject.data.db

import com.jjjoonngg.coroutinetoyproject.data.firebase.FireStoreAccessor

object TodoModel {

    fun insertFireStore(title: String, content: String, date: String, type: String) {
        FireStoreAccessor.sendTodoData(
            TodoDTO(
                "",
                title,
                content,
                date,
                type
            )
        )
    }

    suspend fun readTodoFromFireStore(): MutableList<TodoDTO>? {
        return FireStoreAccessor.readTodo()
    }

    suspend fun readDoingFromFireStore(): MutableList<TodoDTO>? {
        return FireStoreAccessor.readDoing()
    }

    suspend fun readDoneFromFireStore(): MutableList<TodoDTO>? {
        return FireStoreAccessor.readDone()
    }

    suspend fun sendToDoingFromTodo(data: TodoDTO) {
        FireStoreAccessor.sendToDoingFromTodo(data)
    }

    suspend fun sendToTodoFromDoing(data: TodoDTO) {
        FireStoreAccessor.sendToTodoFromDoing(data)
    }

    suspend fun sendToDoneFromDoing(data: TodoDTO) {
        FireStoreAccessor.sendToDoneFromDoing(data)
    }

    suspend fun sendToDoingFromDone(data: TodoDTO) {
        FireStoreAccessor.sendToDoingFromDone(data)
    }
}