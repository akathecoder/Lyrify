package me.sparshagarwal.lyrify.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.songs_search_recycler_view_items.view.*
import me.sparshagarwal.lyrify.R
import me.sparshagarwal.lyrify.models.SongResult

class SongListAdapter(val items: ArrayList<SongResult>, val context: Context) :
    RecyclerView.Adapter<SongListAdapter.ViewHolder>() {

    private var onClickListener: OnClickListener? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val songCoverArt = view.iv_song_cover_art
        val songName = view.tv_song_name
        val artistName = view.tv_artist_name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.songs_search_recycler_view_items, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // TODO("Not implemented Song Cover Art")
        val model: SongResult = items[position]

        holder.songName.text = model.title
        holder.artistName.text = model.artist

        holder.itemView.setOnClickListener {
            if (onClickListener!=null){
                onClickListener!!.onClick(position, model)
            }
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    interface OnClickListener{
        fun onClick(position: Int, model: SongResult)
    }
}