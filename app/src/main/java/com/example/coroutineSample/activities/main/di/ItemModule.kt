package com.example.coroutineSample.activities.main.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import com.example.coroutineSample.activities.main.viewModel.ItemViewModel
import com.example.coroutineSample.activities.main.viewModel.LoginViewModel
import com.example.coroutineSample.util.Constants

/**
 * author Niharika Arora
 */

val itemModule = module {
    viewModel {
        ItemViewModel(
            get()
        )

    }


    viewModel {
        LoginViewModel(
            get()
        )
    }

}