package com.movieapp.feature_movielisting.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.movieapp.feature_movielisting.R
import com.movieapp.feature_movielisting.databinding.LoadStateItemBinding

/**
 * Adapter for handling loading view in recycler review.
 */
class LoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<com.movieapp.feature_movielisting.ui.LoadStateAdapter.LoadStateViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ) = LoadStateViewHolder(parent, retry)

    override fun onBindViewHolder(
        holder: LoadStateViewHolder,
        loadState: LoadState
    ) = holder.bind(loadState)

    class LoadStateViewHolder(
        parent: ViewGroup,
        retry: () -> Unit
    ) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.load_state_item, parent, false)
    ) {
        private val binding = LoadStateItemBinding.bind(itemView)
        private val progressBar: ProgressBar = binding.progressBar
        private val tvError: TextView = binding.tvError.also {
            it.setOnClickListener { retry() }
        }

        fun bind(loadState: LoadState) {
            tvError.isVisible = loadState is LoadState.Error
            progressBar.isVisible = loadState is LoadState.Loading
        }
    }
}