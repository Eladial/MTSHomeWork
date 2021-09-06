package com.example.mtshomework

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
import kotlin.math.roundToInt

class MoviesAdapter(context: Context,
                    private val moviesListener: (Long) -> Unit,
                    private val moviesViewModel: MoviesViewModel
): RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.item_movie, parent, false))
    }

    private fun getItem(position: Int): MovieResponse? = moviesViewModel.movies.value?.get(position)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it, moviesListener) }
    }


    override fun getItemCount(): Int{
        return if (moviesViewModel.movies.value?.size != null) {
            moviesViewModel.movies.value?.size!!
        } else 0
    }

    fun getMovies(): List<MovieResponse>? = moviesViewModel.movies.value

    @SuppressLint("NotifyDataSetChanged")
    fun setMovies(new: List<MovieResponse>?) {
        notifyDataSetChanged()
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val textTitle: TextView = view.findViewById(R.id.tv_film_name)
        private val textDescription: TextView = view.findViewById(R.id.tv_film_description)
        private val iconPoster: ImageView = view.findViewById(R.id.iv_film_poster)
        private val iconStarOne: ImageView = view.findViewById(R.id.ivStarOne)
        private val iconStarTwo: ImageView = view.findViewById(R.id.ivStarTwo)
        private val iconStarThree: ImageView = view.findViewById(R.id.ivStarThree)
        private val iconStarFour: ImageView = view.findViewById(R.id.ivStarFour)
        private val iconStarFive: ImageView = view.findViewById(R.id.ivStarFive)


        @SuppressLint("SetTextI18n")
        fun bind(movie: MovieResponse, moviesListener: (Long) -> Unit) {
            Glide.with(iconPoster).load("https://image.tmdb.org/t/p/w500" + movie.posterPath).into(iconPoster)
            textTitle.text = movie.title
            textDescription.text = movie.overview

            itemView.setOnClickListener {
                moviesListener(movie.id)
            }
            val rateScore = (movie.voteAverage / 2).roundToInt()
            when (rateScore){
                5 -> {
                    iconStarFive.setImageResource(R.drawable.ic_star)
                    iconStarFour.setImageResource(R.drawable.ic_star)
                    iconStarThree.setImageResource(R.drawable.ic_star)
                    iconStarTwo.setImageResource(R.drawable.ic_star)
                    iconStarOne.setImageResource(R.drawable.ic_star)
                }
                4 -> {
                    iconStarFive.setImageResource(R.drawable.ic_star_empty)
                    iconStarFour.setImageResource(R.drawable.ic_star)
                    iconStarThree.setImageResource(R.drawable.ic_star)
                    iconStarTwo.setImageResource(R.drawable.ic_star)
                    iconStarOne.setImageResource(R.drawable.ic_star)
                }
                3 -> {
                    iconStarFive.setImageResource(R.drawable.ic_star_empty)
                    iconStarFour.setImageResource(R.drawable.ic_star_empty)
                    iconStarThree.setImageResource(R.drawable.ic_star)
                    iconStarTwo.setImageResource(R.drawable.ic_star)
                    iconStarOne.setImageResource(R.drawable.ic_star)
                }
                2 -> {
                    iconStarFive.setImageResource(R.drawable.ic_star_empty)
                    iconStarFour.setImageResource(R.drawable.ic_star_empty)
                    iconStarThree.setImageResource(R.drawable.ic_star_empty)
                    iconStarTwo.setImageResource(R.drawable.ic_star)
                    iconStarOne.setImageResource(R.drawable.ic_star)
                }
                1 -> {
                    iconStarFive.setImageResource(R.drawable.ic_star_empty)
                    iconStarFour.setImageResource(R.drawable.ic_star_empty)
                    iconStarThree.setImageResource(R.drawable.ic_star_empty)
                    iconStarTwo.setImageResource(R.drawable.ic_star_empty)
                    iconStarOne.setImageResource(R.drawable.ic_star)
                }
                else -> {
                    iconStarFive.setImageResource(R.drawable.ic_star_empty)
                    iconStarFour.setImageResource(R.drawable.ic_star_empty)
                    iconStarThree.setImageResource(R.drawable.ic_star_empty)
                    iconStarTwo.setImageResource(R.drawable.ic_star_empty)
                    iconStarOne.setImageResource(R.drawable.ic_star_empty)
                }
            }



        }
    }


}