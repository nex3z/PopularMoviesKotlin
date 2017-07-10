package com.nex3z.popularmovieskotlin.presentation.ui.discover

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.nex3z.popularmovieskotlin.R
import com.nex3z.popularmovieskotlin.domain.model.movie.MovieModel
import com.nex3z.popularmovieskotlin.presentation.ui.detail.MovieDetailActivity
import kotlinx.android.synthetic.main.activity_discover.*

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

    override fun onMovieSelect(movie: MovieModel, poster: View) {
        Log.v(LOG_TAG, "onMovieSelect(): movie = " + movie.title)
        val intent = Intent(this, MovieDetailActivity::class.java)
                .putExtra(MovieDetailActivity.MOVIE_INFO, movie)
        val activityOptions = ActivityOptionsCompat
                .makeSceneTransitionAnimation(this, android.support.v4.util.Pair<View, String>(
                        poster, getString(R.string.detail_poster_transition_name)))

        ActivityCompat.startActivity(this, intent, activityOptions.toBundle())
    }

    companion object {
        private val LOG_TAG = "DiscoverActivity"
    }
}
