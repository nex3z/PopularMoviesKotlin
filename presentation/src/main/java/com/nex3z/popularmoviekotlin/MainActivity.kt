package com.nex3z.popularmoviekotlin

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import com.nex3z.popularmoviekotlin.detail.MovieDetailActivity
import com.nex3z.popularmoviekotlin.discover.DiscoverMovieFragment
import com.nex3z.popularmoviekotlin.discover.MovieDetailNavigator
import com.nex3z.popularmoviekotlin.domain.model.movie.MovieModel
import com.nex3z.popularmoviekotlin.favourite.FavouriteMovieFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
        MovieDetailNavigator {

    private var discoverMovieFragment: DiscoverMovieFragment? = null
    private var favouriteMovieFragment: FavouriteMovieFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        setupDrawer()

        if (savedInstanceState == null) {
            showDiscoverMovies()
        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_discover -> {
                showDiscoverMovies()
            }
            R.id.nav_favourite -> {
                showFavouriteMovies()
            }
            R.id.nav_settings -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun navigateToDetail(movie: MovieModel, poster: View) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra(MovieDetailActivity.ARG_MOVIE, movie)
        val activityOptions = ActivityOptionsCompat
                .makeSceneTransitionAnimation(this, android.support.v4.util.Pair<View, String>(
                        poster, getString(R.string.name_poster_transition)))
        ActivityCompat.startActivity(this, intent, activityOptions.toBundle())
    }

    private fun setupDrawer() {
        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        nav_view.menu.getItem(0).isChecked = true
    }

    private fun showDiscoverMovies() {
        if (discoverMovieFragment == null) {
            discoverMovieFragment = DiscoverMovieFragment.newInstance()
        }
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.vg_main_container, discoverMovieFragment)
        transaction.commit()
    }

    private fun showFavouriteMovies() {
        if (favouriteMovieFragment == null) {
            favouriteMovieFragment = FavouriteMovieFragment.newInstance()
        }
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.vg_main_container, favouriteMovieFragment)
        transaction.commit()
    }
}
