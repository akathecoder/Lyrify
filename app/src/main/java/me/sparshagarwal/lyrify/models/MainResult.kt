package me.sparshagarwal.lyrify.models

import java.io.Serializable

data class MainResult (
    val artist: String,
    val title: String,
    val album: String,
    val release_date: String,
    val label: String,
    val timecode: String,
    val song_link: String,
    val apple_music: AppleMusic,
    val spotify: Spotify
): Serializable