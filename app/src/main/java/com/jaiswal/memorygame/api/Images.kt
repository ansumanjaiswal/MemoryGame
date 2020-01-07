package com.jaiswal.memorygame.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface Images {
    @Headers(value = ["X-SHO-verify:homework",
                    "Authorization:308358df7811aa81e103a4b926cadf6f7f0dca2a"])
    @GET("images")
    fun getImages(): Call<Any>
}