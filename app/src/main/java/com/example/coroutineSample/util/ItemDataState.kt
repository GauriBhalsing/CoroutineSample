package com.example.coroutineSample.util

import com.example.coroutineSample.activities.main.data.Item


sealed class ItemDataState {
    object ShowProgress : ItemDataState()
    data class Error(val message: String?) : ItemDataState()
    data class Success(val body: List<Item>? = null) : ItemDataState()
}