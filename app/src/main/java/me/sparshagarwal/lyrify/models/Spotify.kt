package me.sparshagarwal.lyrify.models

import java.io.Serializable

// TODO: SPOTIFY MODEL NOT DONE
data class Spotify(
    val album: SpotifyAlbum,
    val artist: ArrayList<SpotifyArtist>,
    val available_markets: String,
    val disc_number: Int,
    val duration_ms: Int,
    val explicit: Boolean,
    val external_ids: SpotifyExternalIds,
    val external_urls: SpotifyExternalUrls,
    val href: String,
    val id: String,
    val is_local: Boolean,
    val name: String,
    val popularity: Int,
    val track_number: Int,
    val type: String,
    val uri: String
) : Serializable

data class SpotifyAlbum(
    val album_type: String
):Serializable

data class SpotifyArtist(
    val album_type: String
):Serializable

data class SpotifyExternalIds(
    val isrc: String
): Serializable

data class SpotifyExternalUrls(
    val spotify: String
):Serializable