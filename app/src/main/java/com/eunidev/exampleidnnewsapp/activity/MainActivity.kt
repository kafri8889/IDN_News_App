package com.eunidev.exampleidnnewsapp.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.eunidev.exampleidnnewsapp.R
import com.eunidev.exampleidnnewsapp.databinding.ActivityMainBinding
import com.eunidev.exampleidnnewsapp.fragment.MainFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerToggle: ActionBarDrawerToggle
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toolbar = binding.includeToolbarMainActivity.toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        init()

        changeFragment(MainFragment.newInstance("s", "s"))
    }

    private fun init() {
        drawerToggle = ActionBarDrawerToggle(this, binding.drawerLayout, toolbar, R.string.open, R.string.close)
        binding.drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        binding.navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.apiSource_MenuNavview -> {
                    val url = "http://newsapi.org"
                    startActivity(Intent(Intent.ACTION_VIEW).setData(Uri.parse(url)))
                }

                R.id.instagram_MenuNavview -> {
                    val url = "https://instagram.com/"
                    startActivity(Intent(Intent.ACTION_VIEW).setData(Uri.parse(url)))
                }

                R.id.github_MenuNavview -> {
                    val url = "https://github.com/kafri8889/"
                    startActivity(Intent(Intent.ACTION_VIEW).setData(Uri.parse(url)))
                }
            }

            return@setNavigationItemSelectedListener true
        }
    }

    fun changeFragment(f: Fragment) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.frameLayout_MainActivity, f)
        ft.commit()
    }
}