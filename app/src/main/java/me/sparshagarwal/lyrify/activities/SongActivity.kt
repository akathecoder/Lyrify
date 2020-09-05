package me.sparshagarwal.lyrify.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_song.*
import me.sparshagarwal.lyrify.R
import me.sparshagarwal.lyrify.models.SongResult

class SongActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song)

        var songDetails: SongResult? = null

        if (intent.hasExtra(FindLyricsFromSearch.SONG_DETAILS)) {
            songDetails =
                intent.getSerializableExtra(FindLyricsFromSearch.SONG_DETAILS) as SongResult
        }

        if (songDetails != null) {
            // TODO Set Image
            tv_song_name.text = songDetails.title
            tv_artist_name.text = songDetails.artist
            tv_lyrics.text = songDetails.lyrics
        }

    }
}