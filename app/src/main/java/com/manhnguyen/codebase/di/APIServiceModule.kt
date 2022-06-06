package com.manhnguyen.codebase.di

import com.manhnguyen.codebase.data.api.Api
import org.koin.dsl.module

class APIServiceModule {

    companion object {
        val apiModule = module {
            single { Api(get()) }
        }
    }

}