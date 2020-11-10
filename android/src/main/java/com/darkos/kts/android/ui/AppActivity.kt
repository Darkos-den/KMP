package com.darkos.kts.android.ui

import android.os.Bundle
import androidx.navigation.findNavController
import com.darkos.core.presentation.activity.BaseActivity
import com.darkos.kts.R
import com.darkos.mvu.navigator.Navigator
import org.kodein.di.generic.instance

class AppActivity : BaseActivity() {

    private val navigator: Navigator by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        findNavController(R.id.nav_host_fragment).let {
            (navigator as AppNavigator).attachNavController(it)
        }
    }
}