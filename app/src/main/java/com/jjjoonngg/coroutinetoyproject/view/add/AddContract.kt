package com.jjjoonngg.coroutinetoyproject.view.add

interface AddContract {
    interface View {
        fun init()
        fun addData()
        fun clearData()
    }

    interface Presenter {
        var view: View
        fun sendData(datas: List<String>)

    }
}