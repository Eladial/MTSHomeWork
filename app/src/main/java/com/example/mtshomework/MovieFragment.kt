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


class MovieFragment : Fragment() {

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie, container, false)

        val title = view.findViewById<TextView>(R.id.tvName)
        val poster = view.findViewById<ImageView>(R.id.ivPoster)
        val description = view.findViewById<TextView>(R.id.tvDescription)
        val rating = view.findViewById<TextView>(R.id.tvRating)
        val starOne = view.findViewById<ImageView>(R.id.ivStarOne)
        val starTwo = view.findViewById<ImageView>(R.id.ivStarTwo)
        val starThree = view.findViewById<ImageView>(R.id.ivStarThree)
        val starFour = view.findViewById<ImageView>(R.id.ivStarFour)
        val starFive = view.findViewById<ImageView>(R.id.ivStarFive)

        val position = arguments?.getInt(ARG_MOVIE_DATA)
        val movie = MoviesDataSourceImpl().getMovies()[position!!]
        title.text = movie.title
        poster.load(movie.imageUrl)
        description.text = movie.full_description
        rating.text = movie.ageRestriction.toString() + "+"
        when (movie.rateScore){
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
        fun newInstance(movie_data: Int) =
            MovieFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_MOVIE_DATA, movie_data)
                }
            }
    }
}

private const val ARG_MOVIE_DATA = "movie_data"