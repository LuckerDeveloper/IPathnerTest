package com.test.ipathnertest.uiadditions.mainactivity

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.test.ipathnertest.R
import com.test.ipathnertest.commons.convertLongToTime
import com.test.ipathnertest.models.Entry

class Adapter(val clickListener: ItemClickListener, val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    var listOfEntries : List<Entry> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.entry_item, parent, false)
        return EntryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val entry = listOfEntries[position]
        holder as EntryViewHolder
        holder.daTextView.text=context.resources.getText(R.string.da_textview).toString().plus(entry.da.convertLongToTime())
        holder.bodyTextView.text = entry.body.take(200)
        holder.itemView.setOnClickListener {
            clickListener.onClick(listOfEntries[position])
        }
        if (entry.da!=entry.dm){
            holder.dmTextView.visibility=View.VISIBLE
            holder.dmTextView.text=context.resources.getText(R.string.dm_textview).toString().plus(entry.dm.convertLongToTime())
        }
    }

    override fun getItemCount(): Int {
        return listOfEntries.size
    }

    fun updateEntries(list : List<Entry>){
        listOfEntries= list
        notifyDataSetChanged()
    }

    class EntryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val daTextView = itemView.findViewById<TextView>(R.id.da_text_view)
        val dmTextView = itemView.findViewById<TextView>(R.id.dm_text_view)
        val bodyTextView = itemView.findViewById<TextView>(R.id.body_text_view)
    }

    interface ItemClickListener{
        fun onClick(entry : Entry)
    }

}