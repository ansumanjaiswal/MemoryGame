package com.jaiswal.memorygame

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.jaiswal.memorygame.api.GetImagesCall
import com.jaiswal.memorygame.api.ImageApiClient
import com.jaiswal.memorygame.models.response.ImagesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImageRemoteRepository {
    private var apiClient: GetImagesCall? = null

    fun getImages(mutableLiveData: MutableLiveData<ImagesResponse>): MutableLiveData<ImagesResponse>{
        apiClient = ImageApiClient.client.create(GetImagesCall::class.java)

        val call = apiClient?.getImages()


        call?.enqueue(object : Callback<ImagesResponse> {
            override fun onFailure(call: Call<ImagesResponse>?, t: Throwable?) {
                Log.d("Failure", t.toString())
            }

            override fun onResponse(call: Call<ImagesResponse>?, response: Response<ImagesResponse>?) {
                if (response?.isSuccessful!!) {
                    mutableLiveData.postValue(response.body())
                }
            }
        })
        return mutableLiveData
    }
}