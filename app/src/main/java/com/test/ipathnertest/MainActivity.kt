package com.test.ipathnertest


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.test.ipathnertest.models.Entry
import com.test.ipathnertest.uiadditions.mainactivity.Adapter
import com.test.ipathnertest.uiadditions.mainactivity.ConnectionErrorDialogFragment
import com.test.ipathnertest.uiadditions.mainactivity.IMainActivity

const val ENTRY_KEY = "ENTRY_KEY"

class MainActivity : AppCompatActivity(), IMainActivity, Adapter.ItemClickListener {

    lateinit var networkManager : NetworkManager
    private val adapter= Adapter(this, this)
    lateinit var swipeRefreshLayout : SwipeRefreshLayout
    val dialogFragment = ConnectionErrorDialogFragment(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        swipeRefreshLayout = findViewById<SwipeRefreshLayout>(R.id.swipe_container)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        swipeRefreshLayout.setOnRefreshListener{
            networkManager.getEntries()
        }

        networkManager = App.networkManager
        networkManager.mainActivity=this
        swipeRefreshLayout.isRefreshing=true
        networkManager.getEntries()
    }

    override fun onEntriesDataUpdated(listOfEntries: List<Entry>){
        adapter.updateEntries(listOfEntries)
        swipeRefreshLayout.isRefreshing = false
        if (listOfEntries.isEmpty()){
            Toast.makeText(this, "Записи отсутствуют", Toast.LENGTH_SHORT).show()
        }
    }

    override fun showDialogWindow() {
        swipeRefreshLayout.isRefreshing=false
        dialogFragment.show(supportFragmentManager,"errorFragment")
    }

    fun createEntry(view: View) {
        startActivity(Intent(this, CreateEntryActivity::class.java))
    }

    override fun onClick(entry: Entry) {
        val intent = Intent(this, EntryActivity::class.java)
        intent.putExtra(ENTRY_KEY, entry)
        startActivity(intent)
    }
}

