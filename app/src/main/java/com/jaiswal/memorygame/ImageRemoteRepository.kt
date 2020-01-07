package com.jaiswal.memorygame

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.jaiswal.memorygame.api.ImageApiClient
import com.jaiswal.memorygame.api.Images
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImageRemoteRepository {
    private val mutableLiveData = MutableLiveData<Any>()
    private var apiClient: Images? = null

    fun getImages(): MutableLiveData<Any>{
        apiClient = ImageApiClient.client.create(Images::class.java)

        val call = apiClient?.getImages()


        call?.enqueue(object : Callback<Any> {
            override fun onFailure(call: Call<Any>?, t: Throwable?) {
                Log.d("Failure", t.toString())
            }

            override fun onResponse(call: Call<Any>?, response: Response<Any>?) {
                if (response?.isSuccessful!!) {

                    println("Api response successful" )
                    mutableLiveData.postValue(response)
                }
            }
        })
        return mutableLiveData

    }
}