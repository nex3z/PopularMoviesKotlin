package com.nex3z.popularmovieskotlin.app

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        val navController = findNavController(R.id.fragment_nav_host)
        bnv_navi.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            Log.v(LOG_TAG, "addOnDestinationChangedListener(): destination = ${destination.label}")
            when (destination.label) {
                "movie_detail_fragment" -> {
                    bnv_navi.visibility = View.GONE
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window?.statusBarColor = Color.TRANSPARENT
                    }
                }
                else -> {
                    bnv_navi.visibility = View.VISIBLE
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window?.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimaryDark)
                    }
                }
            }

            if (destination.label == "movie_detail_fragment") {
                bnv_navi.visibility = View.GONE
            }

            else {
                bnv_navi.visibility = View.VISIBLE
            }
        }
    }

    companion object {
        private val LOG_TAG: String = MainActivity::class.java.simpleName
    }
}
