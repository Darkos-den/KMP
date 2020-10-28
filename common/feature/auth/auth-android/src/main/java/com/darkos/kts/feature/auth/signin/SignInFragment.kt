package com.darkos.kts.feature.auth.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Text
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.platform.ComposeView
import com.darkos.core.presentation.fragment.base.BaseFragment
import com.darkos.core.presentation.viewModel.BaseViewModel
import org.kodein.di.Kodein

class SignInFragment: BaseFragment() {

    override val viewModelModule: Kodein.Module?
        get() = super.viewModelModule
    override val viewModel: BaseViewModel
        get() = TODO("Not yet implemented")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                MaterialTheme {
                    Text(text = "Hello world")
                }
            }
        }
    }

    override fun viewCreated(savedInstanceState: Bundle?) {
    }
}