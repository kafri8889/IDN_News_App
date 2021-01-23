package com.eunidev.exampleidnnewsapp.data

import com.eunidev.exampleidnnewsapp.database.NewsArticles
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NewsResponse(@SerializedName("status") @Expose val status: String?,
                        @SerializedName("totalResult") @Expose val totalResult: Int?,
                        @SerializedName("articles") @Expose val articles: List<NewsArticles>?) {
    fun isSuccess(): Boolean = (status == "ok")
}
