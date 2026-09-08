package com.example.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Topic(
    val id: String? = null,
    val name: String,
    val imageUrl: String,
    val code: Int
)
