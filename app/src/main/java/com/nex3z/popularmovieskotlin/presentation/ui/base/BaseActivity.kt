package com.nex3z.popularmovieskotlin.presentation.ui.base

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

abstract class BaseActivity : AppCompatActivity(), BaseView {

    override fun onResume() {
        super.onResume()
        if (this is HasPresenter<Presenter>) {
            this.getPresenter().resume()
        }
    }

    override fun onPause() {
        super.onPause()
        if (this is HasPresenter<Presenter>) {
            this.getPresenter().pause()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (this is HasPresenter<Presenter>) {
            this.getPresenter().destroy()
        }
    }

    override val viewContext: Context
        get() = this

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

}
