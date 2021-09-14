package com.test.syntelatostestapp.utils

import androidx.appcompat.widget.SearchView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.test.syntelatostestapp.variables.ConfigParams
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


/**
 * @author Girish D. Mane girishmane8692@gmail.com
 * Created on 9/14/21
 * Last modified on 9/14/21
 */
internal class LifecycleTextChangeListener(
    lifecycleOwner: LifecycleOwner,
    private val queryTextChange: (String?) -> Unit
) : SearchView.OnQueryTextListener {

    private val coroutineScope = lifecycleOwner.lifecycleScope
    private var searchJob: Job? = null

    override fun onQueryTextSubmit(p0: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(searchQuery: String?): Boolean {
        searchJob?.cancel()
        searchJob = coroutineScope.launch {
            searchQuery?.let {
                delay(ConfigParams.SEARCH_DELAY)
                queryTextChange(searchQuery)
            }
        }
        return false
    }

}