package com.example.kotlinfragments

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), ViewPager.OnPageChangeListener {

    val drawerToggle by lazy {
        ActionBarDrawerToggle(
            this,
            drawer_layout,
            toolbar,
            R.string.drawer_open,
            R.string.drawer_close
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        navigation_view.setNavigationItemSelectedListener { selectItem(it) }
        drawer_layout.addDrawerListener(drawerToggle)

        bottom_navigation_view.setOnNavigationItemSelectedListener { selectItem(it) }

        val pagerAdapter = ImageFragmentPagerAdapter(supportFragmentManager)
        view_pager.adapter = pagerAdapter
        view_pager.addOnPageChangeListener(this)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        drawerToggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        drawerToggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (drawerToggle.onOptionsItemSelected(item)) true else super.onOptionsItemSelected(
            item
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.fragment_menu, menu)
        return true
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        val currentMenuItem = bottom_navigation_view.menu.getItem(position).itemId
        if (currentMenuItem != bottom_navigation_view.selectedItemId) {
            bottom_navigation_view.menu.getItem(position).isChecked = true
            bottom_navigation_view.menu.findItem(bottom_navigation_view.selectedItemId).isChecked =
                false
        }
    }

    private fun replaceFragment(fragment: Fragment?) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment!!)
            .commit()
    }

    private fun selectItem(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.first_fragment_item -> view_pager.currentItem = 0
            R.id.second_fragment_item -> view_pager.currentItem = 1
            R.id.third_fragment_item -> view_pager.currentItem = 2
            else -> view_pager.currentItem = 0
        }
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        }
        return true
    }
}