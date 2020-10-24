package com.test.ipathnertest.uiadditions.mainactivity

import com.test.ipathnertest.models.Entry

interface IMainActivity {

    fun onEntriesDataUpdated(listOfEntries : List<Entry>)
    fun showDialogWindow()
}