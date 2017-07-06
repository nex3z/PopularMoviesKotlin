package com.nex3z.popularmovieskotlin.data.net.service

import com.nex3z.popularmovieskotlin.data.entity.movie.DiscoverMovieResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("/3/discover/movie")
    fun discoverMovies(@Query("page") page: Int, @Query("sort_by") sortBy: String)
            : Observable<DiscoverMovieResponse>

}