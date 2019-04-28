package com.myfinancialstory.irufino.myfinancialstory.feature

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import com.myfinancialstory.irufino.myfinancialstory.feature.dashboard.DashboardFragment
import com.myfinancialstory.irufino.myfinancialstory.feature.home.HomeFragment
import com.myfinancialstory.irufino.myfinancialstory.feature.mystory.MyStoryFragment
import com.myfinancialstory.irufino.myfinancialstory.feature.session.LoginActivity
import com.myfinancialstory.irufino.myfinancialstory.feature.session.SessionHandler
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var sessionHandler: SessionHandler

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
        sessionHandler = SessionHandler(this)

        if (sessionHandler.isUserLogged()) {
            setContentView(R.layout.activity_main)

            navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
            showAnimatedFragment(HomeFragment())
        } else {
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }

    fun showAnimatedFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .replace(R.id.container, fragment, "HOME_DISPLAY")
            .commit()
    }
}
