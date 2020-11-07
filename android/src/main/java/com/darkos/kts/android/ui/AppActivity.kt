package com.darkos.kts.android.ui

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.darkos.core.navigation.Navigator
import com.darkos.core.presentation.activity.BaseActivity
import com.darkos.kts.R
import org.kodein.di.generic.instance

class AppActivity : BaseActivity() {

    private val navigator: Navigator by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        supportFragmentManager.findFragmentById(R.id.nav_host_fragment)?.takeIf {
            it is NavHostFragment
        }?.let {
            it as NavHostFragment
            it.navController
        }?.let {
            (navigator as AppNavigator).attachNavController(it)
        }
    }
}