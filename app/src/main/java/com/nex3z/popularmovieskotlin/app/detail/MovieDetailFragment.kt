package com.nex3z.popularmovieskotlin.app.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.google.android.material.appbar.AppBarLayout
import com.nex3z.popularmovieskotlin.app.R
import com.nex3z.popularmovieskotlin.app.detail.info.MovieInfoFragment
import com.nex3z.popularmovieskotlin.app.detail.review.MovieReviewFragment
import com.nex3z.popularmovieskotlin.app.detail.video.MovieVideoFragment
import com.nex3z.popularmovieskotlin.domain.model.movie.MovieModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_detail_fragment.*

class MovieDetailFragment : Fragment() {

    private lateinit var movie: MovieModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movie_detail_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movie = MovieDetailFragmentArgs.fromBundle(it).movie
            Log.v(LOG_TAG, "onViewCreated(): movie = $movie")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initView()
        renderMovie()
    }

    private fun initView() {
        vp_movie_detail_page_container.adapter = SectionPagerAdapter(childFragmentManager)
        tab_movie_detail_page.setupWithViewPager(vp_movie_detail_page_container)

        abl_movie_detail.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
            if (ctl_movie_detail.height + verticalOffset < ctl_movie_detail.height / 2) {
                tab_movie_detail_page.visibility = View.GONE
            } else {
                tab_movie_detail_page.visibility = View.VISIBLE
            }
        })
    }

    private fun renderMovie() {
        ctl_movie_detail.title = movie.title

        val backdropUrl = movie.getBackdropUrl(MovieModel.BackdropSize.W780)
        if (backdropUrl != null) {
            Picasso.get().load(backdropUrl).into(kbv_movie_detail_backdrop)
        }
    }

    private inner class SectionPagerAdapter internal constructor(fm: FragmentManager)
        : FragmentPagerAdapter(fm) {

        private val tabTitles = arrayOf(
            getString(R.string.caption_tab_movie_detail),
            getString(R.string.caption_tab_movie_video),
            getString(R.string.caption_tab_movie_review)
        )

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> MovieInfoFragment.newInstance(movie)
                1 -> MovieVideoFragment.newInstance(movie)
                2 -> MovieReviewFragment.newInstance(movie)
                else -> MovieInfoFragment.newInstance(movie)
            }
        }

        override fun getCount(): Int = tabTitles.size

        override fun getPageTitle(position: Int): CharSequence = tabTitles[position]

    }

    companion object {
        private val LOG_TAG: String = MovieDetailFragment::class.java.simpleName
    }

}
