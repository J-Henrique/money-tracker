package com.jhbb.tracker_data.remote

import com.jhbb.tracker_data.remote.dto.RegisterDto
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterService {

    @POST("register")
    suspend fun postRegister(@Body register: RegisterDto): RegisterDto


    companion object {
        const val BASE_URL = "http://10.0.2.2:3001/moneytracker/"
    }
}