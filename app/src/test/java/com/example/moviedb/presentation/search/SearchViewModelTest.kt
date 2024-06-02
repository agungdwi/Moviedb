package com.example.moviedb.presentation.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.core.data.Resource
import com.example.core.domain.model.Movie
import com.example.core.domain.usecase.MovieUseCase
import com.example.moviedb.DataDummy
import com.example.moviedb.MainDispatcherRule
import com.example.moviedb.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class SearchViewModelTest {

    private lateinit var viewModel: SearchViewModel
    private val dummyMovie= DataDummy.generateDummyMovies()

    @Mock
    private lateinit var movieUseCase: MovieUseCase

    @Mock
    private lateinit var observer: Observer<Resource<List<Movie>>>

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setUp() {
        viewModel = SearchViewModel(movieUseCase)
    }


    @Test
    fun getSearchResult() {
        val title = "dummy"
        val dummy = Resource.Success(dummyMovie)
        val results = flowOf(dummy)
        Mockito.`when`(movieUseCase.searchMovies(title)).thenReturn(results)

        viewModel.searchMovies(title)
        val searchResult = viewModel.movies.getOrAwaitValue()
        Mockito.verify(movieUseCase).searchMovies(title)
        assertNotNull(searchResult)
        assertEquals(100, searchResult.data?.size ?: 0)

        viewModel.movies.observeForever(observer)
        Mockito.verify(observer).onChanged(dummy)
    }
}