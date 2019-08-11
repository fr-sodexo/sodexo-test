package com.example.sodexo.view.container.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sodexo.R
import com.example.sodexo.controller.MovieRepository
import com.example.sodexo.view.component.LoadingView
import com.example.sodexo.view.container.main.MainActivity

class MovieListFragment : Fragment() {

    private var mLoadingView: LoadingView? = null
    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: MovieAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.movie_list_fragment, container, false)
        loadViews(root)
        fetchContent()
        return root
    }

    fun loadViews(root: View) {
        mLoadingView = root.findViewById(R.id.fragment_movie_list_loading_view)
        mRecyclerView = root.findViewById(R.id.fragment_movie_list_recycler_view)
        mAdapter = MovieAdapter(activity as MainActivity?)
        mRecyclerView?.layoutManager = LinearLayoutManager(activity)
        mRecyclerView?.adapter = mAdapter
    }

    fun fetchContent() {
        mLoadingView?.show()
        MovieRepository.fetchMovies().observe(this, Observer {
            mLoadingView?.hide()
            mAdapter?.setList(it)
        })
    }

}