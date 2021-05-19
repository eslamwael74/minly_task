package com.eslamwael74.minlyTask.data.remote

import okhttp3.MultipartBody
import javax.inject.Inject

/**
 * Created by eslamwael74 on 5/18/2021.
 * Email: eslamwael74@outlook.com
 */
class ImageRemoteDataSource @Inject constructor(
    private val service: ImageService
) : BaseDataSource() {

    suspend fun getImages() = getResult { service.getAllImages() }


    suspend fun uploadImage(file: MultipartBody.Part) = getResult { service.uploadImage(file) }

}