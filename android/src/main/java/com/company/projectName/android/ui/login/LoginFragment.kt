package com.company.projectName.android.ui.login

import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import androidx.compose.foundation.Text
import androidx.compose.material.Scaffold
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Recomposer
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.setContent
import androidx.core.widget.addTextChangedListener
import com.company.projectName.domain.feature.login.LoginScreenState
import com.darkos.core.presentation.di.bindViewModel
import com.darkos.core.presentation.di.viewModel
import com.darkos.core.presentation.fragment.LayoutFragment
import io.dynamax.android.R
import io.dynamax.android.databinding.FragmentLoginBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

class LoginFragment : LayoutFragment(
    layoutId = R.layout.fragment_login
) {
    override val viewModelModule = Kodein.Module("HomeVM") {
        bindViewModel<LoginViewModel>() with provider {
            LoginViewModel(
                effectHandler = instance()
            )
        }
    }

    override val viewModel: LoginViewModel by viewModel()

    override fun viewCreated(savedInstanceState: Bundle?) {
        (view as ViewGroup).setContent(Recomposer.current()) {
            val state by viewModel.state.collectAsState(initial = LoginScreenState.Initial)

            TextField(
                value = state.email,
                label = {},
                onValueChange = viewModel::emailChanged)
        }

//        val binding = FragmentLoginBinding.bind(requireView())
//
//        binding.etEmail.addTextChangedListener {
//            it?.toString()?.let {
//                viewModel.emailChanged(it)
//            }
//        }
//        binding.etPassword.addTextChangedListener {
//            it?.toString()?.let {
//                viewModel.passwordChanged(it)
//            }
//        }
//
//        CoroutineScope(Dispatchers.Main).launch {
//            viewModel.state.collect {
//                if(binding.etEmail.text.toString() != it.email) {
//                    binding.etEmail.setText(it.email)
//                }
//                if(binding.etPassword.text.toString() != it.password) {
//                    binding.etPassword.setText(it.password)
//                }
//            }
//        }
    }
}