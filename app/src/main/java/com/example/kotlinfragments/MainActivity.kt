package com.example.kotlinfragments

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ClassCastException


class MainActivity : AppCompatActivity() {

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

        navigation_view.setNavigationItemSelectedListener {
            selectDrawerItem(it)
            true
        }
        drawer_layout.addDrawerListener(drawerToggle)

        val pagerAdapter = ImageFragmentPagerAdapter(supportFragmentManager)
        view_pager.adapter = pagerAdapter
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

    private fun replaceFragment(fragment: Fragment?) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment!!)
            .commit()
    }

    private fun selectDrawerItem(item: MenuItem) {
        when (item.itemId) {
            R.id.first_fragment_item -> view_pager.currentItem = 0
            R.id.second_fragment_item -> view_pager.currentItem = 1
            else -> view_pager.currentItem = 0
        }
        drawer_layout.closeDrawer(GravityCompat.START)
    }
}
