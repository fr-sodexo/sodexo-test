package com.example.sodexo.view.container.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.sodexo.R
import com.example.sodexo.controller.MovieRepository
import com.example.sodexo.model.schema.Movie
import android.content.Intent
import android.net.Uri
import com.example.sodexo.view.component.LoadingView

class MovieDetailsFragment : Fragment() {

    private var mMovie: Movie? = null

    private var mContentView: View? = null
    private var mLoadingView: LoadingView? = null
    private var mTitleTextView: TextView? = null
    private var mYearTextView: TextView? = null
    private var mRatingTextView: TextView? = null
    private var mOverviewTextView: TextView? = null
    private var mImdbButton: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (arguments?.getSerializable(Movie::class.java.simpleName) as? Movie?)?.let {
            mMovie = it
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.movie_details_fragment, container, false)
        loadViews(root)
        fetchContent()
        return root
    }

    fun loadViews(root: View) {
        mContentView = root.findViewById(R.id.movie_details_content_view)
        mLoadingView = root.findViewById(R.id.fragment_movie_details_loading_view)
        mTitleTextView = root.findViewById(R.id.movie_details_title)
        mYearTextView = root.findViewById(R.id.movie_details_year)
        mRatingTextView = root.findViewById(R.id.movie_details_rating)
        mOverviewTextView = root.findViewById(R.id.movie_details_overview)
        mImdbButton = root.findViewById(R.id.movie_details_imdb_button)
    }

    fun fetchContent() {
        mContentView?.visibility = View.GONE
        mLoadingView?.show()
        MovieRepository.fetchDetails(mMovie).observe(this, Observer {
            mMovie = it
            mLoadingView?.hide()
            mContentView?.visibility = View.VISIBLE
            loadContent()
        })
    }

    fun loadContent() {
        mTitleTextView?.text = mMovie?.title
        mYearTextView?.text = mMovie?.year
        mRatingTextView?.text = getString(R.string.movie_details_rating, String.format("%.2f", mMovie?.rating))
        mOverviewTextView?.text = mMovie?.overview
        mImdbButton?.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.imdb.com/title/" + mMovie?.ids?.imdb))
            startActivity(browserIntent)
        }
    }

    companion object {
        fun getInstance(movie: Movie): MovieDetailsFragment {
            val fragment = MovieDetailsFragment()
            val arguments = Bundle()
            arguments.putSerializable(Movie::class.java.simpleName, movie)
            fragment.arguments = arguments
            return fragment
        }
    }

}
