package me.sparshagarwal.lyrify.network

import me.sparshagarwal.lyrify.models.AudDLyricsResponse
import me.sparshagarwal.lyrify.models.AudDResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AudDService {

    @GET("recognize")
    fun getSongNameByUrl(
        @Query("api_token") api_token: String,
        @Query("url") url: String
    ):Call<AudDResponse>

    @GET("findLyrics")
    fun getLyricsByName(
        @Query("api_token") api_token: String,
        @Query("q") query:String
    ):Call<AudDLyricsResponse>

}