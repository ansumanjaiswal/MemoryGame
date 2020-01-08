package com.jaiswal.memorygame.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ImageApiClient {

    companion object {

        private var retrofit: Retrofit? = null

        val client: Retrofit
            get() {
                if (retrofit == null) {
                    retrofit = Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl("http://mobile-homework-82124333.us-east-1.elb.amazonaws.com/")
                        .build()
                }
                return retrofit!!
            }
    }
}