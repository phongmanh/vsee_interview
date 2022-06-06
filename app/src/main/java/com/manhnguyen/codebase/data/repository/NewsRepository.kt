package com.manhnguyen.codebase.data.repository

import com.kwabenaberko.newsapilib.NewsApiClient
import com.kwabenaberko.newsapilib.models.request.EverythingRequest
import com.manhnguyen.codebase.data.api.Api
import com.manhnguyen.codebase.data.model.ArticleResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.suspendCoroutine

class NewsRepository constructor(
    private val api: Api,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun getEverything(pageSize: Int, page: Int) =
        suspendCoroutine<ArticleResponse> { continuation ->
            CoroutineScope(dispatcher).launch {
                try {
                    val response =
                        api.newsApi.getEverything("bbc-sport", page, pageSize, "publishedAt")
                    continuation.resumeWith(Result.success(response))
                } catch (ex: Exception) {
                    continuation.resumeWith(Result.failure(ex))
                }
            }
        }

}