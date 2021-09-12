package com.test.syntelatostestapp.tasks

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.test.syntelatostestapp.models.People


/**
 * @author Girish D. Mane girishmane8692@gmail.com
 * Created on 9/12/21
 * Last modified on 9/12/21
 */
internal class ApiVM(application: Application) : AndroidViewModel(application) {

    private lateinit var peopleObserver: LiveData<ArrayList<People>?>

    fun fetchPeople() {
        peopleObserver = ApiRepo.fetchPeople()
    }

    fun getPeopleObserver(): LiveData<ArrayList<People>?> {
        return peopleObserver
    }

}