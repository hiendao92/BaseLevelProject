package com.android.myapplication.data

import kotlinx.serialization.json.Json

object JsonObj {
    internal val JsonConvert by lazy {
        Json {
            ignoreUnknownKeys = true
        }
    }
}
