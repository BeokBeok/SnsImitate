package com.beok.domain.content

import com.beok.domain.content.entity.HomeResponse
import retrofit2.http.GET

interface ContentService {

    @GET("home")
    suspend fun getHome(): HomeResponse
}
