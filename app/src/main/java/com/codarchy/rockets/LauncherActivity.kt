package com.codarchy.rockets

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LauncherActivity : AppCompatActivity() {

    lateinit var navController: NavController
    lateinit var appBarConfiguration: AppBarConfiguration
    val firstLaunchPrefKey = "first_launch"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher_host)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_content) as NavHostFragment
        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
        showWelcomeDialogIfNeeded()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
                || super.onSupportNavigateUp()
    }

    private fun showWelcomeDialogIfNeeded() {
        val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return
        val isFirstLaunch = sharedPref.getBoolean(firstLaunchPrefKey, true)
        if (isFirstLaunch) {
            findViewById<View>(R.id.main_content).visibility = View.INVISIBLE
            findViewById<View>(R.id.welcome_content).visibility = View.VISIBLE
            findViewById<View>(R.id.consent_button).setOnClickListener {
                findViewById<View>(R.id.main_content).apply {
                    alpha = 0f
                    visibility = View.VISIBLE
                    animate()
                        .alpha(1f)
                        .setDuration(500)
                        .setListener(null)
                }

                findViewById<View>(R.id.welcome_content).animate()
                    .alpha(0f)
                    .setDuration(500)
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            findViewById<View>(R.id.welcome_content).visibility = View.GONE
                        }
                    })
                with (sharedPref.edit()) {
                    putBoolean(firstLaunchPrefKey, false)
                    apply()
                }
            }
        }
    }
}