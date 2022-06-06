package com.manhnguyen.codebase.data.api

import com.manhnguyen.codebase.data.model.ArticleResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("everything")
    suspend fun getEverything(
        @Query("q") q: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("sortBy") sortBy: String
    ): ArticleResponse
}