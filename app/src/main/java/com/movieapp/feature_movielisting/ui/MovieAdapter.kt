package com.movieapp.feature_movielisting.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.movieapp.R
import com.movieapp.databinding.MovieItemListBinding
import com.movieapp.core.data.model.RemoteMovie
import com.movieapp.core.model.Movie
import javax.inject.Inject

/**
 * Adapter for displaying movie list.
 */
class MovieAdapter @Inject constructor(): PagingDataAdapter<Movie, MovieAdapter.MovieViewHolder>(
    MoviesComparator
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {

        return MovieViewHolder(
            parent.context,
            MovieItemListBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bindMovie(it) }
    }

    inner class MovieViewHolder(
        private val context: Context,
        private val binding: MovieItemListBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindMovie(item: Movie) = with(binding) {
            binding.tvMovie.text = item.title
            binding.tvYear.text = context.resources.getString(R.string.year)+" " + item?.year
            Glide.with(context).load(item.poster)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .dontAnimate()
                .into(binding.ivMoviePoster);
        }
    }

    object MoviesComparator : DiffUtil.ItemCallback<Movie>() {

        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.imdbID == newItem.imdbID
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

}