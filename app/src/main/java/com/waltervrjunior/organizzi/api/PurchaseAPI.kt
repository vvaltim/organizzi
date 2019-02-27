package com.waltervrjunior.organizzi.api

import com.google.gson.JsonObject
import retrofit.Call
import retrofit.http.Body
import retrofit.http.Headers
import retrofit.http.POST

interface PurchaseAPI {
    //https://appws.picpay.com/ecommerce/public/payments
    //TODO: Pegar token do picpay no keep
    @POST("ecommerce/public/payments")
    fun sendPurchase(@Body requestBody: JsonObject): Call<JsonObject>
}