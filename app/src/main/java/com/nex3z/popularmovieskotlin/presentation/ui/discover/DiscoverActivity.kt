package com.nex3z.popularmovieskotlin.presentation.ui.discover

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.nex3z.popularmovieskotlin.R
import com.nex3z.popularmovieskotlin.domain.model.movie.MovieModel
import kotlinx.android.synthetic.main.activity_discover.*
import android.content.Intent
import com.nex3z.popularmovieskotlin.presentation.ui.detail.MovieDetailActivity


class DiscoverActivity : AppCompatActivity(), DiscoverFragment.OnMovieSelectListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_discover)
        setSupportActionBar(toolbar)

        navigateToDiscoveryList()
    }

    private fun navigateToDiscoveryList() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.movie_list_container, DiscoverFragment.newDiscoverInstance())
                .commit()
    }

    override fun onMovieSelect(movie: MovieModel) {
        Log.v(LOG_TAG, "onMovieSelect(): movie = " + movie.title)
        val intent = Intent(this, MovieDetailActivity::class.java)
                .putExtra(MovieDetailActivity.MOVIE_INFO, movie)
        startActivity(intent)
    }

    companion object {
        private val LOG_TAG = "DiscoverActivity"
    }
}
