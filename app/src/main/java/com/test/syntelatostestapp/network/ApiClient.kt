package com.test.syntelatostestapp.network

import com.test.syntelatostestapp.utils.LogPrint
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author Girish D. Mane girishmane8692@gmail.com
 * Created on 9/12/21
 * Last modified on 9/12/21
 */
internal object ApiClient {

    private const val TIMEOUT_DURATION = 30L
    private const val STATIC_ERROR = " {\"statusCode\":404,\"message\":\"No Response from server situation.\"}"
    private const val ACCEPT_FORMAT_VALUE = "application/json"

    private var basicClient: Retrofit? = null

    private val baseHttpClient = OkHttpClient.Builder()
        .addInterceptor(BasicInterceptor())
        .readTimeout(TIMEOUT_DURATION, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT_DURATION, TimeUnit.SECONDS)
        .build()

    fun getBasicClient(): Retrofit {
        if (basicClient == null) {
            basicClient = Retrofit.Builder()
                .baseUrl(AppUrls.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(baseHttpClient)
                .build()
        }
        return basicClient!!
    }


    private class BasicInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            val endPoint: String = request.url().toString()
            val response = chain.proceed(request)
            val body = response.body()
            var rawJson = STATIC_ERROR
            var contentType = MediaType.get(ACCEPT_FORMAT_VALUE)
            if (body != null) {
                contentType = body.contentType() ?: MediaType.get(ACCEPT_FORMAT_VALUE)
                rawJson = body.string()
            }
            LogPrint.i(ApiCalls::class.java.simpleName, "URL > $endPoint\nResponse > $rawJson")
            return response.newBuilder()
                .body(ResponseBody.create(contentType, rawJson)).build()
        }
    }
}