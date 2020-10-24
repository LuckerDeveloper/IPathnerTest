package com.test.ipathnertest.models

import com.google.gson.annotations.Expose

class NewSessionResponse(
        @Expose val status : Int,
        @Expose val data : Session) {
}

class Session(
        @Expose val session : String
)