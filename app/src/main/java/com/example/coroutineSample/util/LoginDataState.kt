package com.example.coroutineSample.util

import com.example.coroutineSample.activities.main.data.LoginModel
import retrofit2.Response


sealed class LoginDataState {
    data class Error(val message: String?) : LoginDataState()
    object ValidCredentialsState : LoginDataState()
    object InValidEmailState : LoginDataState()
    object InValidPasswordState : LoginDataState()
    class Success(val body: Response<LoginModel>? = null) : LoginDataState()
}