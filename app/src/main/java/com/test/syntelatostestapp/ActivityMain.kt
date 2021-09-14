package com.test.syntelatostestapp

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import com.test.syntelatostestapp.base.BaseActivity
import com.test.syntelatostestapp.databinding.ActivityMainBinding
import com.test.syntelatostestapp.fragments.FragmentPeople
import com.test.syntelatostestapp.fragments.FragmentRooms

/**
 * @author Girish D. Mane girishmane8692@gmail.com
 * Created on 9/11/21
 * Last modified on 9/11/21
 */

class ActivityMain : BaseActivity(), View.OnClickListener {

    private lateinit var activityMainBinding: ActivityMainBinding
    private var currentMenu: Int = R.string.rooms

    /**
     * all view/s listeners will be assigned here
     * */
    private fun applyListeners() {
        activityMainBinding.onClick = this
    }

    /**
     * open drop down menu items for main menu button
     * */
    private fun openMenu() {
        val menuList = arrayOf(R.string.people, R.string.rooms)
        val popupMenu = PopupMenu(applicationContext, activityMainBinding.ivMainMenu, Gravity.BOTTOM)
        for (menu in menuList) {
            if (menu != currentMenu) {
                popupMenu.menu.add(menu)
            }
        }
        popupMenu.setOnMenuItemClickListener {
            onMenuSelected(menuName = (it.title ?: "").toString())
            return@setOnMenuItemClickListener true
        }
        popupMenu.show()
    }

    /**
     * handle click action of menu to open appropriate screen
     * */
    private fun onMenuSelected(menuName: String) {
        if (resources.getString(currentMenu) != menuName) {
            if (menuName == resources.getString(R.string.people)) {
                currentMenu = R.string.people
                setFragment(FragmentPeople())
            } else if (menuName == resources.getString(R.string.rooms)) {
                currentMenu = R.string.rooms
                setFragment(FragmentRooms())
            }
            activityMainBinding.tvMainTitle.contentDescription = menuName
            activityMainBinding.tvMainTitle.setText(currentMenu)
        }
    }

    /**
     * add new fragment entry to replace the current showing fragment from container
     * */
    private fun setFragment(fragment: Fragment) {
        val tag = fragment.javaClass.simpleName
        supportFragmentManager.beginTransaction().replace(R.id.fl_main_container, fragment, tag).commit()
    }

    /**
     * all initial actions to populate view
     * */
    private fun initView() {
        onMenuSelected(resources.getString(R.string.people))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        initView()

        applyListeners()
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.iv_main_back -> {
            }
            R.id.iv_main_menu -> {
                openMenu()
            }
        }
    }
}