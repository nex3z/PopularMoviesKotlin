package com.nex3z.popularmovieskotlin.data.net

import com.nex3z.popularmovieskotlin.BuildConfig
import com.nex3z.popularmovieskotlin.data.net.service.MovieService
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class RestClient {
    private val BASE_URL = "http://api.themoviedb.org"
    private val movieService: MovieService

    init {
        val httpClient: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(
                        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
                .addInterceptor { chain ->
                    val original: Request = chain.request()
                    val originalHttpUrl: HttpUrl = original.url()
                    val builder: HttpUrl.Builder = originalHttpUrl.newBuilder()
                            .addQueryParameter("api_key", BuildConfig.API_KEY)
                    val requestBuilder: Request.Builder = original.newBuilder()
                            .url(builder.build())
                            .method(original.method(), original.body())
                    val request: Request = requestBuilder.build()
                    chain.proceed(request)
                }
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        movieService = retrofit.create(MovieService::class.java)
    }

    fun getMovieService(): MovieService {
        return movieService
    }

}