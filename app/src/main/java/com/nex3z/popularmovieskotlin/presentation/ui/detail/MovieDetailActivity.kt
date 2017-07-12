package com.nex3z.popularmovieskotlin.presentation.ui.detail

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.MenuItemCompat
import android.support.v7.widget.ShareActionProvider
import android.util.Log
import android.view.Menu
import android.view.View
import com.nex3z.popularmovieskotlin.R
import com.nex3z.popularmovieskotlin.domain.model.movie.MovieModel
import com.nex3z.popularmovieskotlin.presentation.ui.base.BaseActivity
import com.nex3z.popularmovieskotlin.presentation.ui.detail.info.MovieInfoFragment
import com.nex3z.popularmovieskotlin.presentation.ui.detail.review.MovieReviewFragment
import com.nex3z.popularmovieskotlin.presentation.ui.detail.video.MovieVideoFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : BaseActivity() {
    lateinit var movie: MovieModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        movie = intent.getParcelableExtra(MOVIE_INFO)

        supportPostponeEnterTransition()
        init()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_movie_detail, menu)

        val item = menu?.findItem(R.id.action_share)

        val shareActionProvider = MenuItemCompat.getActionProvider(item) as ShareActionProvider
        shareActionProvider.setShareIntent(createShareMovieIntent())

        return super.onCreateOptionsMenu(menu)
    }

    private fun init() {
        initPager()
        renderMovie()
    }

    private fun initPager() {
        pager_detail.adapter = SectionPagerAdapter(supportFragmentManager)
        tab_detail.setupWithViewPager(pager_detail)

        app_bar.addOnOffsetChangedListener({ _, verticalOffset ->
            if (ctl_toolbar_container.height + verticalOffset < ctl_toolbar_container.height / 2) {
                tab_detail.visibility = View.GONE
            } else {
                tab_detail.visibility = View.VISIBLE
            }
        })
    }

    private fun renderMovie() {
        ctl_toolbar_container.setExpandedTitleTextAppearance(R.style.MovieDetailTitleExpanded)
        ctl_toolbar_container.title = movie.title
        Picasso.with(this)
                .load(movie.getBackdropImageUrl())
                .error(R.drawable.placeholder_poster_white)
                .placeholder(R.drawable.placeholder_poster_white)
                .into(iv_detail_backdrop)
    }

    private fun createShareMovieIntent(): Intent {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.addFlags(
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP)
                    Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET
                else
                    Intent.FLAG_ACTIVITY_NEW_DOCUMENT
        )
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, movie.title + ": " + movie.overview
                + getString(R.string.share_hash_tag))
        return shareIntent
    }

    private inner class SectionPagerAdapter constructor(
            fm: FragmentManager
    ) : FragmentPagerAdapter(fm) {
        internal val PAGE_COUNT = 3

        private val tabTitles = arrayOf(
                getString(R.string.tab_movie_detail),
                getString(R.string.tab_movie_video),
                getString(R.string.tab_movie_review)
        )

        override fun getItem(position: Int): Fragment {
            Log.v(LOG_TAG, "getItem(): position = " + position)
            when (position) {
                0 -> return MovieInfoFragment.newInstance(movie)
                1 -> return MovieVideoFragment.newInstance(movie)
                2 -> return MovieReviewFragment.newInstance(movie)
                else -> return MovieInfoFragment.newInstance(movie)
            }
        }

        override fun getCount(): Int {
            return PAGE_COUNT
        }

        override fun getPageTitle(position: Int): CharSequence {
            return tabTitles[position]
        }
    }

    companion object {
        private val LOG_TAG = "MovieDetailActivity"
        private val SAVE_TAG_MOVIE = "movie"

        val MOVIE_INFO = "movie_info"
    }

}
