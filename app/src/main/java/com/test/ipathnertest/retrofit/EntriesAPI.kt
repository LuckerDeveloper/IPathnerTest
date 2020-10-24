package com.test.ipathnertest.retrofit

import com.test.ipathnertest.models.AddEntryResponse
import com.test.ipathnertest.models.GetEntriesResponse
import com.test.ipathnertest.models.NewSessionResponse
import retrofit2.Call
import retrofit2.http.*


interface EntriesAPI {

    @FormUrlEncoded
    @Headers("token: gx9K1l0-73-B92w56p")
    @POST(".")
    fun getPostWithID(@Field("a") a: String): Call<NewSessionResponse>

    @FormUrlEncoded
    @Headers("token: gx9K1l0-73-B92w56p")
    @POST(".")
    fun getEntries(@Field("a") a : String,
                   @Field("session") session: String): Call<GetEntriesResponse>

    @FormUrlEncoded
    @Headers("token: gx9K1l0-73-B92w56p")
    @POST(".")
    fun addEntry(@Field("a") a : String,
                 @Field("session") session: String,
                 @Field("body") body : String)  : Call<AddEntryResponse>
}