package com.example.e_comm_home_demo_app

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.e_comm_home_demo_app.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        /*todo setup bottom nav*/
        navController = findNavController(R.id.fragment)
        binding.bottomNavigationView.setupWithNavController(navController)

        /*todo set toolbar title*/
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.prescriptionsFragment,
                R.id.categoryFragment,
                R.id.homeFragment,
                R.id.offersFragment,
                R.id.profileFragment,
            )
        )
        setupActionBarWithNavController(navController,appBarConfiguration)

        navController.addOnDestinationChangedListener { _, destination, _ ->

//            Log.d(mTAG, "onDestinationChanged: "+destination.id)
            showHideHomeViews(destination.id)
        }
    }

    /*todo showHide Bottom navigation as per requirement*/
    private fun showHideHomeViews(id: Int) {

        if (id == R.id.viewAllCategoryFragment) {

            binding.bottomNavigationView.visibility = View.GONE
            slideDownBottomNav()
        } else {
            binding.bottomNavigationView.visibility = View.VISIBLE
            slideUpBottomNav()
        }
    }

    /*todo slide down and hide bottom navigation*/
    private fun slideDownBottomNav() {

        binding.bottomNavigationView.clearAnimation()
        binding.bottomNavigationView
            .animate()
            .translationY(binding.bottomNavigationView.height.toFloat()).duration = 500
    }

    /*todo slide up and show bottom navigation*/
    private fun slideUpBottomNav() {

        binding.bottomNavigationView.clearAnimation()
        binding.bottomNavigationView
            .animate()
            .translationY(0F).duration = 500
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return true
    }}