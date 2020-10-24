package com.test.ipathnertest.models

import com.google.gson.annotations.Expose

class AddEntryResponse(
        @Expose val status : Int,
        @Expose val data : Id
)

class Id(
    @Expose val id: String
)