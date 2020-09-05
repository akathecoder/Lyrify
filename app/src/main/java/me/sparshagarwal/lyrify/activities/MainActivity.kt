package me.sparshagarwal.lyrify.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import me.sparshagarwal.lyrify.R
import me.sparshagarwal.lyrify.models.AudDResponse
import me.sparshagarwal.lyrify.network.AudDService
import me.sparshagarwal.lyrify.utils.Constants
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        internetBtn.setOnClickListener {
            getSongDetails("https://audd.tech/example1.mp3")
        }

        findLyricsBtn.setOnClickListener {
            val intent = Intent(this, FindLyricsFromSearch::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun getSongDetails(url: String) {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(AudDService::class.java)
        val call: Call<AudDResponse> = service.getSongNameByUrl(Constants.API_TOKEN, url)

        call.enqueue(object : Callback<AudDResponse> {


            override fun onResponse(call: Call<AudDResponse>, response: Response<AudDResponse>) {
                if (response!!.isSuccessful) {
                    val auddResponse: AudDResponse? = response.body()
                    Log.i("Result", "$auddResponse")
                    tv_flag.text = auddResponse.toString()
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

            override fun onFailure(call: Call<AudDResponse>, t: Throwable) {
                Log.e("Errorrrrr", t!!.message.toString())
            }

        })
    }
}