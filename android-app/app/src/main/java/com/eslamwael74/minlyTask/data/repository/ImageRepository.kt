package com.eslamwael74.minlyTask.data.repository

import com.eslamwael74.minlyTask.data.remote.ImageRemoteDataSource
import com.eslamwael74.minlyTask.utils.Resource
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import java.io.File
import javax.inject.Inject

class ImageRepository @Inject constructor(
    private val remoteDataSource: ImageRemoteDataSource
) {
    suspend fun fetchImages(): Resource<List<String>> {
        return remoteDataSource.getImages()
    }

    suspend fun uploadImage(file: File): Resource<ResponseBody> {
        return remoteDataSource.uploadImage(file = convertImgFileToMultipart(file))
    }

    private fun convertImgFileToMultipart(file: File): MultipartBody.Part {
        return MultipartBody.Part.createFormData(
            "image",
            file.name,
            RequestBody.create(
                "image/*".toMediaTypeOrNull(), file
            )
        )
    }
}