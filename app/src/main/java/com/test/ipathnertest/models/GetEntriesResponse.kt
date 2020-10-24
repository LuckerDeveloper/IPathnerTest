package com.test.ipathnertest.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import com.google.gson.annotations.Expose

class GetEntriesResponse(
        @Expose val status : Int,
        @Expose val data : List<List<Entry>>
)

@Parcelize
data class Entry(
        @Expose val id : String,
        @Expose val body : String,
        @Expose val da : Long,
        @Expose val dm : Long
) : Parcelable