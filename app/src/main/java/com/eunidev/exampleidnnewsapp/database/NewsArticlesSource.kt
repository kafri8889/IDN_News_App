package com.eunidev.exampleidnnewsapp.database

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class NewsArticlesSource(@SerializedName("id") val sourceID: Int?,
                              @SerializedName("name") val sourceName: String): Serializable
