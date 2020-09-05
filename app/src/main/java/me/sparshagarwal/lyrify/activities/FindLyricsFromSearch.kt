package me.sparshagarwal.lyrify.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_find_lyrics_from_search.*
import me.sparshagarwal.lyrify.R
import me.sparshagarwal.lyrify.models.AudDLyricsResponse
import me.sparshagarwal.lyrify.models.SongResult
import me.sparshagarwal.lyrify.network.AudDService
import me.sparshagarwal.lyrify.utils.Constants
import me.sparshagarwal.lyrify.utils.SongListAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FindLyricsFromSearch : AppCompatActivity() {

    private var songList: ArrayList<SongResult>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_lyrics_from_search)

        searchBtn.setOnClickListener {
            getLyrics(et_song.text.toString())
        }
    }

    private fun setupSongsResultRecyclerView() {
        rv_song_list.layoutManager = GridLayoutManager(
            this, 2, GridLayoutManager.VERTICAL,false
        )
        val adapter = SongListAdapter(songList!!, this)
        rv_song_list.adapter = adapter

        adapter.setOnClickListener(object: SongListAdapter.OnClickListener{
            override fun onClick(position: Int, model: SongResult) {
                val intent = Intent(this@FindLyricsFromSearch, SongActivity::class.java)
                intent.putExtra(SONG_DETAILS, model)
                startActivity(intent)
                finish()
            }

        })
    }

    private fun getLyrics(query: String) {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(AudDService::class.java)
        val call: Call<AudDLyricsResponse> = service.getLyricsByName(Constants.API_TOKEN, query)

        call.enqueue(object : Callback<AudDLyricsResponse> {
            override fun onResponse(
                call: Call<AudDLyricsResponse>,
                response: Response<AudDLyricsResponse>
            ) {
                if (response.isSuccessful) {
                    songList = response.body()?.result
                    setupSongsResultRecyclerView()
                } else {
                    when (response.code()) {
                        400 -> {
                            Log.e("Error 400", "Bad Request")
                        }
                        404 -> {
                            Log.e("Error 404", "Not Found")
                        }
                        else -> {
                            Log.e("Error", "Generic Error")
                        }
                    }
                }
            }

            override fun onFailure(call: Call<AudDLyricsResponse>, t: Throwable) {
                Log.e("Errorrrrr", t!!.message.toString())
            }

        })

    }

    companion object{
        var SONG_DETAILS = "song_details"
    }
}