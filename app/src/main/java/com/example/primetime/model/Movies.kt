package com.example.primetime.model

import com.squareup.moshi.Json

data class Movies(
    val page: Page
)

data class Page(
    @field:Json(name = "content-items") val contentitems: ContentItems,

    @field:Json(name = "page-num") val pagenum: String,
    @field:Json(name = "page-size")  val pagesize: String,
    val title: String,
    @field:Json(name = "total-content-items")  val totalcontentitems: String
)

data class ContentItems(
    val content: List<Content>
)

data class Content(
    val name: String,
    @field:Json(name = "poster-image")  val posterimage: String
)