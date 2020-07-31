package com.jjjoonngg.coroutinetoyproject.data.db

data class TodoDTO(
    var postId: String?,
    var title: String,
    var date: String,
    var content: String,
    var type: String
) {
    constructor() : this(
        postId = null,
        title = "",
        date = "",
        content = "",
        type = ""
    )
}