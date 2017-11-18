package com.nex3z.popularmoviekotlin.base

import android.support.v7.app.AppCompatActivity
import android.widget.Toast

abstract class BaseActivity : AppCompatActivity(), BaseView {

    override fun onResume() {
        super.onResume()
        if (this is HasPresenter<*> ) {
            this.getPresenter().resume()
        }
    }

    override fun onPause() {
        super.onPause()
        if (this is HasPresenter<*> ) {
            this.getPresenter().pause()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (this is HasPresenter<*> ) {
            this.getPresenter().pause()
        }
    }

    override fun showMessage(message: String?) {
        if (message != null) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    override fun showError(message: String?) {
        if (message != null) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

}