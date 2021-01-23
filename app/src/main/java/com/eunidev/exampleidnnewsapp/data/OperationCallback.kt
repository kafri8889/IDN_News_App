package com.eunidev.exampleidnnewsapp.data

interface OperationCallback<T> {

    fun onSucces(data: T?)
    fun onError(error: String?)
}