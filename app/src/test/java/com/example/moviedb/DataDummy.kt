package com.example.moviedb

import com.example.core.domain.model.Movie


object DataDummy {

    fun generateDummyMovies(count: Int = 100): List<Movie> {
        val items: MutableList<Movie> = arrayListOf()
        for (i in 0 until count) {
            val movie = Movie(
                id = i,
                title = "Title $i",
                overview = "Overview of movie $i",
                posterPath = "/path/to/poster$i.jpg",
                backdropPath = "/path/to/backdrop$i.jpg",
                popularity = i * 10.0,
                voteAverage = 7.5,
                releaseDate = "2024-01-01",
                page = 1
            )
            items.add(movie)
        }
        return items
    }

    fun generateDetailDummyMovies(): Movie {

            val movie = Movie(
                id = 1,
                title = "Title 1",
                overview = "Overview of movie 1",
                posterPath = "/path/to/poster1.jpg",
                backdropPath = "/path/to/backdrop1.jpg",
                popularity = 1 * 10.0,
                voteAverage = 7.5,
                releaseDate = "2024-01-01",
                page = 1
            )

        return movie
    }
}
