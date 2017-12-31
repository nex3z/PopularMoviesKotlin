package com.nex3z.popularmoviekotlin.detail

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.NavUtils
import android.support.v4.view.MenuItemCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.ShareActionProvider
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.nex3z.popularmoviekotlin.MainActivity
import com.nex3z.popularmoviekotlin.R
import com.nex3z.popularmoviekotlin.detail.info.MovieInfoFragment
import com.nex3z.popularmoviekotlin.detail.review.MovieReviewFragment
import com.nex3z.popularmoviekotlin.detail.video.MovieVideoFragment
import com.nex3z.popularmoviekotlin.domain.model.movie.MovieModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var movie: MovieModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        setSupportActionBar(tb_movie_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (savedInstanceState == null) {
            movie = intent.getParcelableExtra<MovieModel>(ARG_MOVIE)
        } else {
            movie = savedInstanceState.getParcelable<MovieModel>(ARG_MOVIE)
        }

        supportPostponeEnterTransition()
        init()
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        outState?.putParcelable(ARG_MOVIE, movie)
        super.onSaveInstanceState(outState, outPersistentState)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_movie_detail, menu)

        val item = menu?.findItem(R.id.action_share)
        val shareActionProvider = MenuItemCompat.getActionProvider(item) as ShareActionProvider
        shareActionProvider.setShareIntent(createShareMovieIntent())

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        if (id == android.R.id.home) {
            NavUtils.navigateUpTo(this, Intent(this, MainActivity::class.java))
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun init() {
        initPager()
        renderMovie()
    }

    private fun initPager() {
        vp_movie_detail_page_container.setAdapter(SectionPagerAdapter(supportFragmentManager))
        tab_movie_detail_page.setupWithViewPager(vp_movie_detail_page_container)

        abl_movie_detail.addOnOffsetChangedListener({ appBarLayout, verticalOffset ->
            if (ctl_movie_detail.height + verticalOffset < ctl_movie_detail.height / 2) {
                tb_movie_detail.setVisibility(View.GONE)
            } else {
                tb_movie_detail.setVisibility(View.VISIBLE)
            }
        })
    }

    private fun renderMovie() {
        ctl_movie_detail.setTitle(movie.title)
        val backdropUrl = movie.getBackdropUrl(MovieModel.BackdropSize.W780)
        if (backdropUrl != null) {
            Picasso.with(this)
                    .load(backdropUrl)
                    .into(kbv_movie_detail_backdrop)
        }
    }

    private fun createShareMovieIntent(): Intent {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, movie.title + ": " + movie.overview
                + getString(R.string.caption_share_hash_tag))
        return shareIntent
    }

    private inner class SectionPagerAdapter internal constructor(fm: FragmentManager)
        : FragmentPagerAdapter(fm) {

        private val tabTitles = arrayOf(
                getString(R.string.caption_tab_movie_detail),
                getString(R.string.caption_tab_movie_video),
                getString(R.string.caption_tab_movie_review))

        override fun getItem(position: Int): Fragment = when (position) {
            0 -> MovieInfoFragment.newInstance(movie)
            1 -> MovieVideoFragment.newInstance(movie)
            2 -> MovieReviewFragment.newInstance(movie)
            else -> MovieInfoFragment.newInstance(movie)
        }

        override fun getCount(): Int = 3

        override fun getPageTitle(position: Int): CharSequence = tabTitles[position]

    }

    companion object {
        val ARG_MOVIE = "arg_movie"
    }

}
