package com.test.syntelatostestapp.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonObject
import com.test.syntelatostestapp.models.People
import com.test.syntelatostestapp.network.ApiCalls
import com.test.syntelatostestapp.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * @author Girish D. Mane girishmane8692@gmail.com
 * Created on 9/12/21
 * Last modified on 9/12/21
 */
internal object ApiRepo {

    /**
     * make API call to fetch and deserialize fetch people API
     * */
    fun fetchPeople(): LiveData<ArrayList<People>?> {
        val peopleData = MutableLiveData<ArrayList<People>?>()
        val apiService = ApiClient.getBasicClient().create(ApiCalls::class.java)
        val fetchPeopleCall = apiService.getPeople()
        fetchPeopleCall.enqueue(object : Callback<JsonObject?> {
            override fun onResponse(call: Call<JsonObject?>, response: Response<JsonObject?>) {

            }

            override fun onFailure(call: Call<JsonObject?>, t: Throwable) {

            }
        })
        return peopleData
    }
}