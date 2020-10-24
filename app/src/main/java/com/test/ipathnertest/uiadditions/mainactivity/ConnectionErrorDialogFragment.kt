package com.test.ipathnertest.uiadditions.mainactivity

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.test.ipathnertest.App

class ConnectionErrorDialogFragment(val mContext : Context) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder =  AlertDialog.Builder(mContext)
        builder.setTitle("Ошибка подключения")
            .setMessage("Проверьте соединение с сетью")
            .setNeutralButton("Обновить данные",object : DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    dialog?.cancel()
                    App.networkManager.getEntries()
                }

            })
        return builder.create()
    }
}