package com.eslamwael74.minlyTask.data.remote

import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part


interface ImageService {
    @GET("images")
    suspend fun getAllImages(): Response<List<String>>

    @Multipart
    @POST("images")
    suspend fun uploadImage(
        @Part file: MultipartBody.Part?
    ): Response<ResponseBody>
}