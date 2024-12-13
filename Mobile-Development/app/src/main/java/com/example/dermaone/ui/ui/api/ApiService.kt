package com.example.dermaone.ui.ui.api

import com.example.dermaone.ui.ui.response.HealthInformationResponse
import com.example.dermaone.ui.ui.response.PredictResponse
import com.example.dermaone.ui.ui.response.PredictionHistory
import okhttp3.MultipartBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface ApiService {

    @Multipart
    @POST("predict")
    suspend fun predict(
        @Part file: MultipartBody.Part
    ): PredictResponse

    @GET("history")
    suspend fun getHistory():List<PredictionHistory>

    @GET("news/")
    suspend fun getHealthInformation(
        @Query("q") query: String
    ): HealthInformationResponse
}