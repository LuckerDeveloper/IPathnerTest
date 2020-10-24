package com.test.ipathnertest

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.test.ipathnertest.commons.convertLongToTime
import com.test.ipathnertest.models.Entry

class EntryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.entry_item)

        val daTextView = findViewById<TextView>(R.id.da_text_view)
        val dmTextView = findViewById<TextView>(R.id.dm_text_view)
        val bodyTextView = findViewById<TextView>(R.id.body_text_view)

        val entry = intent.getParcelableExtra<Entry>(ENTRY_KEY)

        if (entry != null) {
            daTextView.text=resources.getText(R.string.dm_textview).toString().plus(entry.dm.convertLongToTime())
            bodyTextView.text = entry.body
            if (entry.da!=entry.dm){
                dmTextView.visibility= View.VISIBLE
                dmTextView.text=resources.getText(R.string.dm_textview).toString().plus(entry.dm.convertLongToTime())
            }
        }
    }
}