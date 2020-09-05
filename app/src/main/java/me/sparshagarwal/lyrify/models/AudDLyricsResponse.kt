package me.sparshagarwal.lyrify.models

import java.io.Serializable

data class AudDLyricsResponse(
    val status: String,
    val result: ArrayList<SongResult>
):Serializable

data class SongResult(
    val song_id: String,
    val artist_is: String,
    val title:String,
    val title_with_featured:String,
    val full_title: String,
    val artist: String,
    val lyrics: String,
val media: String
):Serializable