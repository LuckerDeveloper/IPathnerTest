package com.test.ipathnertest

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CreateEntryActivity : AppCompatActivity() {

    val networkManager = App.networkManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_entry)

        val buttonSave = findViewById<Button>(R.id.buttonSave)
        val buttonCancel = findViewById<Button>(R.id.buttonCancel)
        val editText = findViewById<EditText>(R.id.editText)

        buttonSave.setOnClickListener {
            val text = editText.text.toString()
            if (!text.isBlank()){
                networkManager.addEntry(text)
                onBackPressed()
            } else {
                Toast.makeText(this, "Введите текст", Toast.LENGTH_SHORT).show()
            }
        }

        buttonCancel.setOnClickListener {
            onBackPressed()
        }
    }
}