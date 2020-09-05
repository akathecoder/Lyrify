package me.sparshagarwal.lyrify.models

import java.io.Serializable

data class AudDResponse(
    val status: String,
    val result: MainResult
): Serializable