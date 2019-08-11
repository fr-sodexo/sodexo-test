package com.example.sodexo.view.container.main

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.sodexo.R
import com.example.sodexo.view.container.movie.MovieListFragment

class MainActivity : AppCompatActivity() {

    private var mMainContainer: FrameLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        loadViews()
        loadFragment(MovieListFragment())
    }

    private fun loadViews() {
        mMainContainer = findViewById(R.id.main_container)
    }

    fun loadFragment(fragment: Fragment) {

        val manager = supportFragmentManager

        val transaction = manager.beginTransaction()
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
        transaction.replace(R.id.main_container, fragment, fragment.javaClass.simpleName)
        transaction.addToBackStack(fragment.javaClass.simpleName)
        transaction.commit()

        manager.executePendingTransactions()

    }

    override fun onBackPressed() {
        try {

            if (getCurrentFragment() is MovieListFragment) {
                finish()
                return
            }

            super.onBackPressed()

        } catch (e: Exception) {

        }

    }

    fun getCurrentFragment(): Fragment? {
        return try {
            supportFragmentManager.findFragmentById(R.id.main_container)
        } catch (e: Exception) {
            null
        }

    }

}