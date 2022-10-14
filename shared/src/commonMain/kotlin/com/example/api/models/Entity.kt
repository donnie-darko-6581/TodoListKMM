package com.example.api.models

data class EntityResponse(
    val count: Int,
    val entries: List<Entity>
)

data class Entity(
    val API: String,
    val Auth: String,
    val Category: String,
    val Cors: String,
    val Description: String,
    val HTTPS: Boolean,
    val Link: String
)