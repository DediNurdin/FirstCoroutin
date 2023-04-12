package com.tedaindo.firstcoroutin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tedaindo.firstcoroutin.databinding.AdapterMovieBinding

class MovieAdapter : RecyclerView.Adapter<MainViewHolder>() {

    var movieList = mutableListOf<Movie>()
    private var mFlag = true

    fun setMovies(movies: List<Movie>) {
        this.movieList = movies.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterMovieBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

        val movie = movieList[position]
        if (ValidationUtil.validateMovie(movie)) {
            holder.binding.name.text = movie.name
            holder.binding.tvDetail.text = movie.desc
            holder.binding.ivDetail.setOnClickListener{
                mFlag = if (mFlag) {
                    holder.binding.tvDetail.visibility = ViewGroup.VISIBLE
                    holder.binding.ivDetail.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up)
                    false
                } else {
                    holder.binding.tvDetail.visibility = ViewGroup.GONE
                    holder.binding.ivDetail.setImageResource(R.drawable.ic_keyboard_arrow_down)
                    true
                }
            }
            Glide.with(holder.itemView.context).load(movie.imageUrl).into(holder.binding.imageview)
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}

class MainViewHolder(val binding: AdapterMovieBinding) : RecyclerView.ViewHolder(binding.root) {

}