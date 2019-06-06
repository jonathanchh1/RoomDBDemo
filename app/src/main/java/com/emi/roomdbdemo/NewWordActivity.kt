package com.emi.roomdbdemo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class NewWordActivity : AppCompatActivity() {


    private lateinit var editTextView : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_word)

        editTextView = findViewById(R.id.edit_word)
        val button = findViewById<Button>(R.id.button_save)

        button.setOnClickListener {
            val intentReply = Intent()

            if(TextUtils.isEmpty(editTextView.text)){
                setResult(Activity.RESULT_CANCELED, intentReply)
            }else{
                val word = editTextView.text.toString()
                intentReply.putExtra(EXTRA_REPLY, word)
                setResult(Activity.RESULT_OK, intentReply)
            }

            finish()
        }
    }

    companion object{
        const val EXTRA_REPLY = "com.emi.android.wordsqlite.REPlY"
    }









}
