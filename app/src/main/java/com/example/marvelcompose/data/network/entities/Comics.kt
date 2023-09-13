package com.example.marvelcompose.data.network.entities

data class Comics(
    val available: Int,
    val collectionURI: String,
    val items: List<Comic>,
    val returned: Int
)