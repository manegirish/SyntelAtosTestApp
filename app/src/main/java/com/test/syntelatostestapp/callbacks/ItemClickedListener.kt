package com.test.syntelatostestapp.callbacks

import android.view.View

/**
 * @author Girish D. Mane girishmane8692@gmail.com
 * Created on 9/14/21
 * Last modified on 9/14/21
 */
internal interface ItemClickedListener {

    fun itemClicked(view: View, position: Int, data: Any?)
}