package com.faizmokhtar.catnetwork.model

import com.squareup.moshi.Json

data class CatBreedData(
    val name: String,
    val temperament: String
)

data class ImageResultData(
    @field:Json(name="url") val imageUrl: String,
    val breeds: List<CatBreedData>
)
