package com.manhnguyen.codebase.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.manhnguyen.codebase.common.Configs
import com.manhnguyen.codebase.data.model.News
import com.manhnguyen.codebase.data.repository.NewsRemoteMediator
import com.manhnguyen.codebase.data.repository.NewsRepository
import com.manhnguyen.codebase.data.room.databases.AppDatabase
import com.manhnguyen.codebase.ui.adapters.SimpleRecycleViewPagingAdapter
import com.manhnguyen.codebase.ui.adapters.SimpleRecyclerPagingItem
import com.manhnguyen.codebase.ui.adapters.news.NewsPagingItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import java.util.*


class NewsViewModel(private val database: AppDatabase, private val newsRepository: NewsRepository) :
    ViewModel() {

    @OptIn(ExperimentalPagingApi::class)
    fun loadNews(adapter: SimpleRecycleViewPagingAdapter, query: String): Flow<PagingData<SimpleRecyclerPagingItem>> =
        Pager(
            initialKey = 1,
            config = PagingConfig(Configs.PAGE_SIZE),
            remoteMediator = NewsRemoteMediator(database, newsRepository, query)
        ) {
            database.newsDao().getAll()
        }
            .flow
            .catch { cause: Throwable ->
                println(cause)
            }
            .flowOn(Dispatchers.IO)
            .cachedIn(viewModelScope)
            .map { pageData ->
                pageData.flatMap { news ->
                    val item = NewsPagingItem(news, adapter)
                    adapter.setItem(item)
                    arrayListOf(item)
                }
            }

    fun getNewsDetails(newsId: UUID): Flow<News> {
        return database.newsDao().getNewsById(newsId)
    }
}