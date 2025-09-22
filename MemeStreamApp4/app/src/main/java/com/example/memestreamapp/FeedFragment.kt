package com.example.memestreamapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.memestreamapp.databinding.FragmentFeedBinding

class FeedFragment : Fragment() {

    private var _binding: FragmentFeedBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FeedViewModel by viewModels() // Make sure you have FeedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFeedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup RecyclerView
        val adapter = FeedAdapter()
        binding.feedRecycler.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.feedRecycler.adapter = adapter

        // Observe LiveData from ViewModel
        viewModel.gifs.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
        }

        // Load trending GIFs
        viewModel.loadTrending()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
