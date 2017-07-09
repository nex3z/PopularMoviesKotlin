package com.nex3z.popularmovieskotlin.presentation.ui.base

import android.content.Context
import android.support.v4.app.Fragment
import android.widget.Toast

abstract class BaseFragment : Fragment(), BaseView {

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
        get() = context

    override fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    override fun showError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
    }

}