package com.test.syntelatostestapp.callbacks

import android.view.View

/**
 * @author Girish D. Mane girishmane8692@gmail.com
 * Created on 9/14/21
 * Last modified on 9/14/21
 */
internal interface ItemClickedListener {

    /**
     * triggered when called on click of view
     * @param view which is clicked
     * @param position row item which is clicked or view from it
     * @param data can be any sort of data which need to passed on click/action triggered
     * */
    fun itemClicked(view: View, position: Int, data: Any?)
}