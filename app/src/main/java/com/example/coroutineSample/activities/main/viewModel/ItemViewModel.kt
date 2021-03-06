package com.example.coroutineSample.activities.main.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutineSample.activities.main.data.Headers
import com.example.coroutineSample.activities.main.data.Item
import com.example.coroutineSample.backend.ServiceUtil
import com.example.coroutineSample.util.Constants
import com.example.coroutineSample.util.ItemDataState
import kotlinx.coroutines.launch

/**
 * author Niharika Arora
 */

class ItemViewModel(
    private val serviceUtil: ServiceUtil
) :
    ViewModel() {

    val uiState = MutableLiveData<ItemDataState>()

    fun showList(headers: Headers) {
        retrieveItems(headers)
    }

    private fun retrieveItems(headers: Headers) {
        if (headers.clientId.isNotEmpty() && headers.userId.isNotEmpty() && headers.accessToken.isNotEmpty()) {
            viewModelScope.launch {
                runCatching {
                    uiState.postValue(ItemDataState.ShowProgress)
                    fetchItems(headers)
                }.onSuccess {
                    uiState.postValue(ItemDataState.Success(it))
                }.onFailure {
                    uiState.postValue(ItemDataState.Error(it.message))
                }
            }
        } else {
            uiState.postValue(ItemDataState.Error("Issues in Headers,please check"))
        }
    }

    private suspend fun fetchItems(headers: Headers): List<Item> {
        val map = HashMap<String, String>()
        map[Constants.CLIENT] = headers.clientId
        map[Constants.USER_ID] = headers.userId
        map[Constants.ACCESS_TOKEN] = headers.accessToken
        return serviceUtil.getList(map)
    }

    fun getObserverState() = uiState

}