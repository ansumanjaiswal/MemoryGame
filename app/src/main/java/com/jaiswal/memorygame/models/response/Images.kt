package com.jaiswal.memorygame.models.response

import com.google.gson.annotations.SerializedName

data class Images(
    @field:SerializedName("file")
    val file: String? = null
){
    fun getFinalImage(): String{
        return StringBuilder().append(INITIAL_URL).append(file).toString()
    }

    companion object {
        const val INITIAL_URL = "http://mobile-homework-82124333.us-east-1.elb.amazonaws.com"
    }
}