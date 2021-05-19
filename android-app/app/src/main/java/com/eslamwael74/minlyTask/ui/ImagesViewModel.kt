package com.eslamwael74.minlyTask.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eslamwael74.minlyTask.data.repository.ImageRepository
import com.eslamwael74.minlyTask.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ImagesViewModel @Inject constructor(
    private val repository: ImageRepository
) : ViewModel() {

    val images = MutableLiveData<Resource<List<String>>>()
    val uploadImage = MutableLiveData<Resource<ResponseBody>>()

    fun getImages() {

        images.postValue(Resource.loading())

        viewModelScope.launch(Dispatchers.IO) {

            val response = repository.fetchImages()

            response.let {
                val imagesList = it.data

                if (imagesList != null) {
                    images.postValue(Resource.success(imagesList))
                } else
                    images.postValue(Resource.error("Error while loading images"))

            }
        }

    }

    fun uploadImage(file: File) {

        uploadImage.postValue(Resource.loading())

        viewModelScope.launch(Dispatchers.IO) {

            val response = repository.uploadImage(file)

            response.let {
                val body = it.data

                if (body != null) {
                    uploadImage.postValue(Resource.success(body))
                } else {
                    uploadImage.postValue(Resource.error("Upload Failed, Try Again!"))
                }
            }
        }

    }
}