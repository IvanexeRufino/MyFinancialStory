package com.myfinancialstory.irufino.myfinancialstory.feature

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import com.myfinancialstory.irufino.myfinancialstory.feature.HomeFragment.HomeFragment
import com.myfinancialstory.irufino.myfinancialstory.feature.MyStoryFragment.DashboardFragment
import com.myfinancialstory.irufino.myfinancialstory.feature.MyStoryFragment.MyStoryFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        if (item.itemId == navigation.selectedItemId) return@OnNavigationItemSelectedListener false

        when (item.itemId) {
            R.id.navigation_home -> {
                showAnimatedFragment(HomeFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                showAnimatedFragment(DashboardFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_expenses -> {
                showAnimatedFragment(MyStoryFragment())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        showAnimatedFragment(HomeFragment())
    }

    fun showAnimatedFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .replace(R.id.container, fragment, "HOME_DISPLAY")
            .commit()
    }
}
