package com.example.moviedb.presentation.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.recyclerview.widget.ListUpdateCallback
import com.example.core.domain.model.Movie
import com.example.core.domain.usecase.MovieUseCase
import com.example.core.ui.MoviePagingAdapter
import com.example.moviedb.DataDummy
import com.example.moviedb.MainDispatcherRule
import com.example.moviedb.getOrAwaitValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRules = MainDispatcherRule()

    private lateinit var viewModel: HomeViewModel
    private val dummyMovie = DataDummy.generateDummyMovies()

    @Mock
    private lateinit var movieUseCase: MovieUseCase

    @Test
    fun `when Get Movie Should Not Null and Return Data`() = runTest {
        val data: PagingData<Movie> = MoviePagingSource.snapshot(dummyMovie)
        val movie: Flow<PagingData<Movie>> = flowOf(data)
        Mockito.`when`(movieUseCase.getMovies()).thenReturn(movie)

        viewModel = HomeViewModel(movieUseCase)
        val actualMovies: PagingData<Movie> = viewModel.movies.getOrAwaitValue()

        val differ = AsyncPagingDataDiffer(
            diffCallback = MoviePagingAdapter.DIFF_CALLBACK,
            updateCallback = noopListUpdateCallback,
            workerDispatcher = Dispatchers.Main,
        )
        differ.submitData(actualMovies)

        Assert.assertNotNull(differ.snapshot())
        Assert.assertEquals(dummyMovie.size, differ.snapshot().size)
        Assert.assertEquals(dummyMovie[0], differ.snapshot()[0])
    }

    @Test
    fun `when Get Movie Empty Should Return No Data`() = runTest {
        val data: PagingData<Movie> = PagingData.from(emptyList())
        val movie: Flow<PagingData<Movie>> = flowOf(data)
        Mockito.`when`(movieUseCase.getMovies()).thenReturn(movie)

        viewModel = HomeViewModel(movieUseCase)
        val actualMovies: PagingData<Movie> = viewModel.movies.getOrAwaitValue()

        val differ = AsyncPagingDataDiffer(
            diffCallback = MoviePagingAdapter.DIFF_CALLBACK,
            updateCallback = noopListUpdateCallback,
            workerDispatcher = Dispatchers.Main,
        )
        differ.submitData(actualMovies)

        Assert.assertEquals(0, differ.snapshot().size)
    }

}

val noopListUpdateCallback = object : ListUpdateCallback {
    override fun onInserted(position: Int, count: Int) {}
    override fun onRemoved(position: Int, count: Int) {}
    override fun onMoved(fromPosition: Int, toPosition: Int) {}
    override fun onChanged(position: Int, count: Int, payload: Any?) {}
}

class MoviePagingSource : PagingSource<Int, LiveData<List<Movie>>>() {
    companion object {
        fun snapshot(items: List<Movie>): PagingData<Movie> {
            return PagingData.from(items)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, LiveData<List<Movie>>>): Int {
        return 0
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LiveData<List<Movie>>> {
        return LoadResult.Page(emptyList(), 0, 1)
    }
}