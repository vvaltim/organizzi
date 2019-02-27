package com.waltervrjunior.organizzi.service

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.waltervrjunior.organizzi.api.PurchaseAPI
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import retrofit.GsonConverterFactory
import retrofit.Retrofit

object PurchaseService {
    fun sendPurchase(
        onSuccess: (Pair<String, String>) -> Unit,
        onError: () -> Unit
    ){
        doAsync {
            //TODO: Pegar meu response Body no keep

            Log.d("PURCHASESERVICE", Gson().toJson(requestBody))

            val retrofit = Retrofit
                .Builder()
                .baseUrl("https://appws.picpay.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val purchaseAPI = retrofit.create(PurchaseAPI::class.java)

            val request = purchaseAPI.sendPurchase(requestBody)
            val requestResponse = request.execute()

            if (!requestResponse.isSuccess){
                uiThread {
                    onError()
                }
                return@doAsync
            }

            if (requestResponse.isSuccess){
                Log.d("RESPONSE", Gson().toJson(requestResponse.body()))
                if (requestResponse.body().has("paymentUrl")){
                    val returnUrl = requestResponse.body().get("paymentUrl").asString
                    val id = requestResponse.body().get("referenceId").asString

                    uiThread {
                        onSuccess(Pair(returnUrl, id))
                    }
                    return@doAsync
                }
            }
        }
    }
}