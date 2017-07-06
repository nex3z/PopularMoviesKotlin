package com.nex3z.popularmovieskotlin.presentation.ui.discover

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.nex3z.popularmovieskotlin.R
import kotlinx.android.synthetic.main.activity_discover.*

class DiscoverActivity : AppCompatActivity() {

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

}
