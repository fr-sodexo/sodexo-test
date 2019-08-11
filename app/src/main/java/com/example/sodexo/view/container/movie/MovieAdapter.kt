package com.example.sodexo.view.container.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sodexo.R
import com.example.sodexo.model.schema.Movie
import com.example.sodexo.view.container.main.MainActivity
import java.util.*

class MovieAdapter(private val mActivity: MainActivity?) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private val mMovies: MutableList<Movie> = ArrayList()

    @Synchronized
    fun setList(movies: List<Movie>?) {
        if (movies == null) return
        mMovies.clear()
        mMovies.addAll(movies)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val mContainer = itemView.findViewById(R.id.movie_list_item) as View?
        private val mTitleTextView = itemView.findViewById(R.id.movie_list_item_title) as TextView?
        private val mYearTextView = itemView.findViewById(R.id.movie_list_item_year) as TextView?

        fun bindMovie(item: Movie, activity: MainActivity?) = with(item) {
            mTitleTextView?.text = item.title
            mYearTextView?.text = item.year
            mContainer?.setOnClickListener { activity?.loadFragment(
                MovieDetailsFragment.getInstance(
                    item
                )
            ) }
            return@with
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.movie_list_item, parent, false)
        )
    override fun getItemCount(): Int = mMovies.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bindMovie(mMovies[position], mActivity)

}