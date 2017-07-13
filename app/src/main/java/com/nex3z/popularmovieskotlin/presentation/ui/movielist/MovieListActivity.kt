package com.nex3z.popularmovieskotlin.presentation.ui.movielist

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import android.view.View
import com.nex3z.popularmovieskotlin.R
import com.nex3z.popularmovieskotlin.domain.model.movie.MovieModel
import com.nex3z.popularmovieskotlin.presentation.ui.detail.MovieDetailActivity
import com.nex3z.popularmovieskotlin.presentation.ui.movielist.discover.DiscoverFragment
import com.nex3z.popularmovieskotlin.presentation.ui.movielist.favourite.FavouriteFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_list.*
import kotlinx.android.synthetic.main.app_bar_movie_list.*
import kotlinx.android.synthetic.main.nav_header_discover.*

class MovieListActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
        OnMovieSelectListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
        setSupportActionBar(toolbar)

        setupDrawer()
        navigateToDiscoveryList()
    }

    private fun navigateToDiscoveryList() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.movie_list_container, DiscoverFragment.newInstance())
                .commit()
    }

    private fun navigateToFavouriteList() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.movie_list_container, FavouriteFragment.newInstance())
                .commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        Log.v(LOG_TAG, "onNavigationItemSelected(): id = " + id)

        if (id == R.id.nav_discover) {
            navigateToDiscoveryList()
        } else if (id == R.id.nav_favourite) {
            navigateToFavouriteList()
        } else if (id == R.id.nav_settings) {

        }

        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onMovieSelect(movie: MovieModel, poster: View) {
        Log.v(LOG_TAG, "onMovieSelect(): movie = " + movie.title)

        Picasso.with(this)
                .load(movie.getBackdropImageUrl())
                .error(R.drawable.side_nav_bar)
                .placeholder(R.drawable.side_nav_bar)
                .into(iv_drawer_header)
        tv_drawer_title.text = movie.title

        val intent = Intent(this, MovieDetailActivity::class.java)
                .putExtra(MovieDetailActivity.MOVIE_INFO, movie)
        val activityOptions = ActivityOptionsCompat
                .makeSceneTransitionAnimation(this, android.support.v4.util.Pair<View, String>(
                        poster, getString(R.string.detail_poster_transition_name)))

        ActivityCompat.startActivity(this, intent, activityOptions.toBundle())
    }

    private fun setupDrawer() {
        val toggle = ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        nav.setNavigationItemSelectedListener(this)
        nav.menu.getItem(0).isChecked = true
    }

    companion object {
        private val LOG_TAG = "MovieListActivity"
    }
}
