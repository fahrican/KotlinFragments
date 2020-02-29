package com.example.kotlinfragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ImageFragmentPagerAdapter(fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> FirstImageFragment.newInstance()
            1 -> SecondImageFragment.newInstance()
            else -> FirstImageFragment.newInstance()
        }
    }

    override fun getCount(): Int = 2
}