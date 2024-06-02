package com.example.moviedb.presentation.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.core.domain.usecase.MovieUseCase
import com.example.moviedb.DataDummy
import com.example.moviedb.MainDispatcherRule
import com.example.moviedb.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRules = MainDispatcherRule()

    private lateinit var viewModel: DetailViewModel
    private val dummyMovie = DataDummy.generateDetailDummyMovies()

    @Mock
    private lateinit var movieUseCase: MovieUseCase

    @Test
    fun `check if movie is favorite`() = runTest {
        val movieId = 1
        val expectedResult = true // Assuming the movie is favorite

        viewModel = DetailViewModel(movieUseCase)
        `when`(movieUseCase.isMovieFavorite(movieId)).thenReturn(flowOf(expectedResult))

        val result = viewModel.checkFavorite(movieId).getOrAwaitValue()

        assertEquals(expectedResult, result)
    }


    @Test
    fun `add movie to favorites`() = runTest {
        val movie = dummyMovie

        viewModel = DetailViewModel(movieUseCase)
        viewModel.setFavorite(movie)

        // Verify that insertFavoriteMovie is called once
        verify(movieUseCase, times(1)).insertFavoriteMovie(movie)
    }

    @Test
    fun `delete movie from favorites`() = runTest {
        val movie = dummyMovie

        viewModel = DetailViewModel(movieUseCase)
        viewModel.deleteFavorite(movie)

        // Verify that deleteFavoriteMovie is called once
        verify(movieUseCase, times(1)).deleteFavoriteMovie(movie)
    }

}