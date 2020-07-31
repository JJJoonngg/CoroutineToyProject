package com.jjjoonngg.coroutinetoyproject.view.add

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.jjjoonngg.coroutinetoyproject.R
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity(R.layout.activity_add), AddContract.View,
    View.OnClickListener {


    private lateinit var inputTitle: EditText
    private lateinit var inputContent: EditText
    private lateinit var inputDate: EditText

    private val presenter: AddPresenter by lazy {
        AddPresenter().apply {
            view = this@AddActivity
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.addButton -> {
                addData()
            }
            R.id.deleteButton -> {
                clearData()
            }
            R.id.cancelButton -> {
                finish()
            }
        }
    }

    override fun init() {
        addButton.setOnClickListener(this)
        deleteButton.setOnClickListener(this)
        cancelButton.setOnClickListener(this)

        inputTitle = findViewById<EditText>(R.id.inputTitle)
        inputContent = findViewById<EditText>(R.id.inputContent)
        inputDate = findViewById<EditText>(R.id.inputDate)
    }

    override fun addData() {
        val tests = mutableListOf<String>()
        tests.add(inputTitle.text.toString())
        tests.add(inputContent.text.toString())
        tests.add(inputDate.text.toString())
        presenter.sendData(tests)
        setResult(Activity.RESULT_OK)
        finish()
    }

    override fun clearData() {
        inputTitle.text.clear()
        inputContent.text.clear()
        inputDate.text.clear()
    }
}