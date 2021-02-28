package com.example.drurepair.datasource

import com.example.drurepair.request.LoginRequest
import com.example.drurepair.response.LoginResponse

interface DataSource {
    fun login(req: LoginRequest): LoginResponse
}