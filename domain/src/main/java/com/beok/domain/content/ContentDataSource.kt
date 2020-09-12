package com.beok.domain.content

import com.beok.domain.content.entity.HomeResponse

interface ContentDataSource {

    suspend fun getHome(): Result<HomeResponse>
}
