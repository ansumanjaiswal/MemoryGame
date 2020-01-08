package com.jaiswal.memorygame.models.response

import com.google.gson.annotations.SerializedName

data class ImagesResponse(
    @field:SerializedName("count")
    val count: Long? = null,

    @field:SerializedName("images")
    val images: List<Images>? = null,

    @field:SerializedName("created")
    val created: Long? = null
)