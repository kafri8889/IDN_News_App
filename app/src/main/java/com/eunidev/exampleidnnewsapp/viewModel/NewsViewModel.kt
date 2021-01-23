package com.eunidev.exampleidnnewsapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eunidev.exampleidnnewsapp.data.NewsResponse
import com.eunidev.exampleidnnewsapp.data.OperationCallback
import com.eunidev.exampleidnnewsapp.database.NewsArticles
import com.eunidev.exampleidnnewsapp.model.NewsRepository

class NewsViewModel(private val repository: NewsRepository): ViewModel() {

    private val _news = MutableLiveData<List<NewsArticles>>()
    val news: LiveData<List<NewsArticles>> = _news

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _onMsgError = MutableLiveData<Any>()
    val onMsgError: LiveData<Any> = _onMsgError

    private val _isListEmpty = MutableLiveData<Boolean>()
    val isListEmpty: LiveData<Boolean> = _isListEmpty

    init {
        loadData()
    }

    fun loadData() {
        _isLoading.value = true
        repository.fetchNewsData(object : OperationCallback<List<NewsArticles>> {

            override fun onError(error: String?) {
                _isLoading.value = false
                _onMsgError.value = error
            }

            override fun onSucces(data: List<NewsArticles>?) {
                _isLoading.value = false
                if (data.isNullOrEmpty()) {
                    _isListEmpty.value = true
                } else {
                    _news.value = data
                }
            }
        })
    }

}