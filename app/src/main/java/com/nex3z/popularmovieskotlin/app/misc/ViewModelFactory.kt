package com.nex3z.popularmovieskotlin.app.misc

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nex3z.popularmovieskotlin.app.App
import com.nex3z.popularmovieskotlin.domain.PopMovieService
import java.lang.reflect.InvocationTargetException

object ViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        try {
            return modelClass.getDeclaredConstructor(Application::class.java, PopMovieService::class.java)
                .newInstance(App.appContext, App.service)
        } catch (e: NoSuchMethodException) {
            throw CreateViewModelFailedException(
                modelClass.name + " has no constructor with Application and CoreService", e)
        } catch (e: InstantiationException) {
            throw CreateViewModelFailedException(
                "Failed to instantiation " + modelClass.name, e)
        } catch (e: InvocationTargetException) {
            throw CreateViewModelFailedException(
                "Failed to invoke constructor of " + modelClass.name, e)
        } catch (e: IllegalAccessException) {
            throw CreateViewModelFailedException(
                "Failed to access " + modelClass.name, e)
        }
    }

}