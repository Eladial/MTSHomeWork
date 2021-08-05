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

class MyMoviesAdapter(context: Context,
                      private val moviesListener: (Int) -> Unit,
                      private val movies: List<MovieDto>): RecyclerView.Adapter<MyMoviesAdapter.ViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.item_movie, parent, false))
    }

    private fun getItem(position: Int): MovieDto = movies[position]

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))

        holder.itemView.setOnClickListener{
            moviesListener(position)
        }
    }


    override fun getItemCount(): Int = movies.size

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val textTitle: TextView = view.findViewById(R.id.tv_film_name)
        private val textDescription: TextView = view.findViewById(R.id.tv_film_description)
        private val iconPoster: ImageView = view.findViewById(R.id.iv_film_poster)
        private val textAge: TextView = view.findViewById(R.id.tvRating)
        private val iconStarOne: ImageView = view.findViewById(R.id.ivStarOne)
        private val iconStarTwo: ImageView = view.findViewById(R.id.ivStarTwo)
        private val iconStarThree: ImageView = view.findViewById(R.id.ivStarThree)
        private val iconStarFour: ImageView = view.findViewById(R.id.ivStarFour)
        private val iconStarFive: ImageView = view.findViewById(R.id.ivStarFive)

        @SuppressLint("SetTextI18n")
        fun bind(movie: MovieDto) {
            iconPoster.load(movie.imageUrl)
            textTitle.text = movie.title
            textDescription.text = movie.description
            textAge.text = movie.ageRestriction.toString() + "+"
            when (movie.rateScore){
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