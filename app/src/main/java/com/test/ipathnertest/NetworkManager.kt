package com.test.ipathnertest

import android.util.Log
import com.test.ipathnertest.models.AddEntryResponse
import com.test.ipathnertest.models.GetEntriesResponse
import com.test.ipathnertest.models.NewSessionResponse
import com.test.ipathnertest.retrofit.NetworkService
import com.test.ipathnertest.uiadditions.mainactivity.IMainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val TAG = "NetworkError"

class NetworkManager {

    lateinit var session: String
    private val networkService = NetworkService()
    lateinit var mainActivity : IMainActivity

    fun getEntries(){
        if (!this::session.isInitialized) initSession()
        else {
            networkService.getJsonApi()
                .getEntries("get_entries", session)
                .enqueue(object : Callback<GetEntriesResponse> {
                    override fun onResponse(
                        call: Call<GetEntriesResponse>,
                        response: Response<GetEntriesResponse>
                    ) {
                        val getEntriesResponse = response.body()
                        val listOfEntries = getEntriesResponse!!.data[0]
                        mainActivity.onEntriesDataUpdated(listOfEntries)
                    }

                    override fun onFailure(call: Call<GetEntriesResponse>, t: Throwable) {
                        Log.e(TAG, t.toString())
                        mainActivity.showDialogWindow()
                    }
                })
        }
    }

    fun addEntry(body : String){
        networkService.getJsonApi()
                .addEntry("add_entry", session, body)
                .enqueue(object : Callback<AddEntryResponse>{
                    override fun onResponse(call: Call<AddEntryResponse>, response: Response<AddEntryResponse>) {
                        getEntries()
                    }

                    override fun onFailure(call: Call<AddEntryResponse>, t: Throwable) {
                        Log.e(TAG, t.toString())
                        getEntries()
                    }
                })
    }

    private fun initSession(){
        networkService.getJsonApi()
                .getPostWithID("new_session")
                .enqueue(object : Callback<NewSessionResponse> {
            override fun onResponse(call: Call<NewSessionResponse>, response: Response<NewSessionResponse>) {
                Log.e(TAG, "onResponse session")
                val newSessionResponse  = response.body()
                session= newSessionResponse?.data?.session!!
                App.instance.saveSession(session)
                getEntries()
            }

            override fun onFailure(call: Call<NewSessionResponse>, t: Throwable) {
                Log.e(TAG, t.toString())
                mainActivity.showDialogWindow()
            }
        })
    }


}