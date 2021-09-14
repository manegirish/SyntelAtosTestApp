package com.test.syntelatostestapp.callbacks


/**
 * @author Girish D. Mane girishmane8692@gmail.com
 * Created on 9/14/21
 * Last modified on 9/14/21
 * Called/used to return data after local search
 */
interface SearchListener {
    /**
     * @param searchedData result data of search query
     * */
    fun searchResult(searchedData: ArrayList<*>?)
}