package com.example.moviedb.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.core.data.Resource
import com.example.core.domain.model.Movie
import com.example.core.ui.MovieListAdapter
import com.example.moviedb.Utils.calculateSpanCount
import com.example.moviedb.databinding.FragmentSearchBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    private val searchViewModel: SearchViewModel by viewModel()
    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = MovieListAdapter()
        val layoutManager = GridLayoutManager(requireActivity(), calculateSpanCount(requireActivity()))
        binding.rvMovies.layoutManager = layoutManager
        binding.rvMovies.adapter = adapter

        adapter.onItemClick = {movie: Movie ->
            val action = SearchFragmentDirections.actionNavigationSearchToDetailActivity(movie)
            findNavController().navigate(action)
        }

        binding.searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                searchViewModel.searchMovies(query)
                binding.searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                // Handle text changes here if needed
                return false
            }
        })

        searchViewModel.movies.observe(viewLifecycleOwner){ movies ->
            if (movies != null){
                when(movies){
                    is Resource.Error -> {
                        binding.emptyLy.mainEmpty.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        binding.rvMovies.visibility = View.GONE
                        Toast.makeText(requireActivity(), movies.message, Toast.LENGTH_SHORT).show()
                    }
                    is Resource.Loading -> {
                        binding.rvMovies.visibility = View.GONE
                        binding.emptyLy.mainEmpty.visibility = View.GONE
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        binding.rvMovies.visibility = View.VISIBLE
                        binding.emptyLy.mainEmpty.visibility = View.GONE
                        binding.progressBar.visibility = View.GONE
                        adapter.submitList(movies.data)
                        if (movies.data?.isEmpty() == true){
                            binding.emptyLy.mainEmpty.visibility = View.VISIBLE
                        }
                    }

                }
            }
        }

    }
}