package com.eunidev.exampleidnnewsapp.data

import android.util.Log
import com.eunidev.exampleidnnewsapp.api.APIClient
import com.eunidev.exampleidnnewsapp.database.NewsArticles
import com.eunidev.exampleidnnewsapp.model.NewsDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsRemoteDataSource(apiClient: APIClient): NewsDataSource {

    private var call: Call<NewsResponse>? = null
    private var service = apiClient.build()

    override fun retrieveNews(callback: OperationCallback<List<NewsArticles>>) {
        call = service?.getNews()
        call?.enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                response.body()?.let {
                    if (it.isSuccess() and response.isSuccessful) {
                        Log.i("Retrofit", "Response Successful")
                        Log.i("Retrofit", "Status: ${it.status}")
                        Log.i("Retrofit", "Total Result: ${it.totalResult}")
                        Log.i("Retrofit", "Total Data: ${it.articles?.size}")
                        callback.onSucces(it.articles)
                    } else {
                        Log.i("Retrofit", "Response Not Successful")
                        callback.onError("Response Not Successful")
                    }
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.i("Retrofit", "Response onFailure")
                callback.onError(t.message)
                t.printStackTrace()
            }

        })
    }

    override fun cancel() {
        call?.let {
            it.cancel()
        }
    }
}