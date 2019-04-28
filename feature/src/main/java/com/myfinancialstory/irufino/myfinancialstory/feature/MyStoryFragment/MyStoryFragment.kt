package com.myfinancialstory.irufino.myfinancialstory.feature.MyStoryFragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.myfinancialstory.irufino.myfinancialstory.feature.R

class MyStoryFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.my_story_tab, container, false)

        return view
    }

}