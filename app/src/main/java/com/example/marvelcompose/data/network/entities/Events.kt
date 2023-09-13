package com.example.marvelcompose.data.network.entities

data class Events(
    val available: Int,
    val collectionURI: String,
    val items: List<Event>,
    val returned: Int
)