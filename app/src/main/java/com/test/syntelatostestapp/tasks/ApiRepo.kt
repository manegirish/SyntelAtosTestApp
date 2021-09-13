package com.test.syntelatostestapp.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.test.syntelatostestapp.models.People
import com.test.syntelatostestapp.models.Room
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
     * make API call to fetch fetch people
     * */
    fun fetchPeople(): LiveData<ArrayList<People>?> {
        val peopleData = MutableLiveData<ArrayList<People>?>()
        val apiService = ApiClient.getBasicClient().create(ApiCalls::class.java)
        val fetchPeopleCall = apiService.getPeople()
        fetchPeopleCall.enqueue(object : Callback<ArrayList<People>?> {
            override fun onResponse(call: Call<ArrayList<People>?>, response: Response<ArrayList<People>?>) {
                if (response.isSuccessful) {
                    peopleData.value = response.body()
                } else {
                    peopleData.value = null
                }
            }

            override fun onFailure(call: Call<ArrayList<People>?>, t: Throwable) {
                peopleData.value = null
            }
        })
        return peopleData
    }

    /**
     * make API call to fetch rooms
     * */
    fun fetchRooms(): LiveData<ArrayList<Room>?> {
        val roomsData = MutableLiveData<ArrayList<Room>?>()
        val apiService = ApiClient.getBasicClient().create(ApiCalls::class.java)
        val fetchRoomsCall = apiService.getRooms()
        fetchRoomsCall.enqueue(object : Callback<ArrayList<Room>?> {
            override fun onResponse(call: Call<ArrayList<Room>?>, response: Response<ArrayList<Room>?>) {
                if (response.isSuccessful) {
                    roomsData.value = response.body()
                } else {
                    roomsData.value = null
                }
            }

            override fun onFailure(call: Call<ArrayList<Room>?>, t: Throwable) {
                roomsData.value = null
            }
        })
        return roomsData
    }
}