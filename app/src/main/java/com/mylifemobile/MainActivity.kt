package com.mylifemobile

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mylifemobile.dashboard.DashboardFragment
import com.mylifemobile.home.HomeFragment
import com.mylifemobile.mystory.MyExpensesFragment
import com.mylifemobile.session.LoginActivity
import com.mylifemobile.session.SessionHandler
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
                showAnimatedFragment(MyExpensesFragment())
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

    private fun showAnimatedFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .replace(R.id.container, fragment, "HOME_DISPLAY")
            .commit()
    }
}
