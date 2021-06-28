package com.faizmokhtar.weatherapp.model

import com.squareup.moshi.Json

data class CurrentImageData (
    val total: Long,

    @Json(name="total_pages")
    val totalPages: Long,

    val results: List<Result>
)

data class Result (
    val id: String,

    @Json(name="created_at")
    val createdAt: String,

    @Json(name="updated_at")
    val updatedAt: String,

    @Json(name="promoted_at")
    val promotedAt: String,

    val width: Long,
    val height: Long,
    val color: String,

    @Json(name="blur_hash")
    val blurHash: String,

    val description: String,

    @Json(name="alt_description")
    val altDescription: String?,

    val urls: Urls,
)

data class Urls (
    val raw: String,
    val full: String,
    val regular: String,
    val small: String,
    val thumb: String
)
