package com.example.mtshomework

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import coil.load
import com.bumptech.glide.Glide
import kotlin.math.roundToInt


class MovieFragment : Fragment() {

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_details, container, false)
        val description = view.findViewById<TextView>(R.id.tvDescription)
        val title = view.findViewById<TextView>(R.id.tvName)
        val poster = view.findViewById<ImageView>(R.id.ivPoster)
        val starOne = view.findViewById<ImageView>(R.id.ivStarOne)
        val starTwo = view.findViewById<ImageView>(R.id.ivStarTwo)
        val starThree = view.findViewById<ImageView>(R.id.ivStarThree)
        val starFour = view.findViewById<ImageView>(R.id.ivStarFour)
        val starFive = view.findViewById<ImageView>(R.id.ivStarFive)



        val movie: MovieResponse = arguments?.getParcelable(ARG_MOVIE_DATA)!!
        val rateScore = (movie.voteAverage / 2).roundToInt()
        title.text = movie.title
        description.text = movie.overview
        Glide.with(poster).load("https://image.tmdb.org/t/p/w500" + movie.posterPath).into(poster)
        when (rateScore) {
            5 -> {
                starFive.setImageResource(R.drawable.ic_star)
                starFour.setImageResource(R.drawable.ic_star)
                starThree.setImageResource(R.drawable.ic_star)
                starTwo.setImageResource(R.drawable.ic_star)
                starOne.setImageResource(R.drawable.ic_star)
            }
            4 -> {
                starFive.setImageResource(R.drawable.ic_star_empty)
                starFour.setImageResource(R.drawable.ic_star)
                starThree.setImageResource(R.drawable.ic_star)
                starTwo.setImageResource(R.drawable.ic_star)
                starOne.setImageResource(R.drawable.ic_star)
            }
            3 -> {
                starFive.setImageResource(R.drawable.ic_star_empty)
                starFour.setImageResource(R.drawable.ic_star_empty)
                starThree.setImageResource(R.drawable.ic_star)
                starTwo.setImageResource(R.drawable.ic_star)
                starOne.setImageResource(R.drawable.ic_star)
            }
            2 -> {
                starFive.setImageResource(R.drawable.ic_star_empty)
                starFour.setImageResource(R.drawable.ic_star_empty)
                starThree.setImageResource(R.drawable.ic_star_empty)
                starTwo.setImageResource(R.drawable.ic_star)
                starOne.setImageResource(R.drawable.ic_star)
            }
            1 -> {
                starFive.setImageResource(R.drawable.ic_star_empty)
                starFour.setImageResource(R.drawable.ic_star_empty)
                starThree.setImageResource(R.drawable.ic_star_empty)
                starTwo.setImageResource(R.drawable.ic_star_empty)
                starOne.setImageResource(R.drawable.ic_star)
            }
            else -> {
                starFive.setImageResource(R.drawable.ic_star_empty)
                starFour.setImageResource(R.drawable.ic_star_empty)
                starThree.setImageResource(R.drawable.ic_star_empty)
                starTwo.setImageResource(R.drawable.ic_star_empty)
                starOne.setImageResource(R.drawable.ic_star_empty)
            }
        }
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(movieData: MovieDto) =
            MovieFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_MOVIE_DATA, movieData)
                }
            }
    }
}

private const val ARG_MOVIE_DATA = "movie_data"