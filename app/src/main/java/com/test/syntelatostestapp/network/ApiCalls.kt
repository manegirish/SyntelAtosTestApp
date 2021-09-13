package com.test.syntelatostestapp.network

import com.test.syntelatostestapp.models.People
import com.test.syntelatostestapp.models.Room
import retrofit2.Call
import retrofit2.http.GET

/**
 * @author Girish D. Mane girishmane8692@gmail.com
 * Created on 9/12/21
 * Last modified on 9/12/21
 */
internal interface ApiCalls {

    @GET(AppUrls.GET_PEOPLE)
    fun getPeople(): Call<ArrayList<People>?>

    @GET(AppUrls.GET_ROOMS)
    fun getRooms(): Call<ArrayList<Room>?>
}