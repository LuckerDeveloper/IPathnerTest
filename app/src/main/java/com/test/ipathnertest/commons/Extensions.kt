package com.test.ipathnertest.commons

import java.text.SimpleDateFormat
import java.util.*

fun Long.convertLongToTime(): String {
    val date = Date(this*1000)
    val format = SimpleDateFormat("dd.MM.yyyy HH:mm")
    return format.format(date)
}

