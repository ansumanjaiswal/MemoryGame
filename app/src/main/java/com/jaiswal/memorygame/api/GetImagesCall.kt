package com.jaiswal.memorygame.api

import com.jaiswal.memorygame.models.response.ImagesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface GetImagesCall {
    @Headers(value = ["X-SHO-verify:homework",
                    "Authorization:Bearer 308358df7811aa81e103a4b926cadf6f7f0dca2a"])
    @GET("images")
    fun getImages(): Call<ImagesResponse>
}