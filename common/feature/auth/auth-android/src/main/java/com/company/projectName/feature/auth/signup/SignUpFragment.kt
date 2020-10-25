package com.company.projectName.feature.auth.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import com.darkos.core.presentation.fragment.base.BaseFragment
import com.darkos.core.presentation.viewModel.BaseViewModel
import org.kodein.di.Kodein

class SignUpFragment : BaseFragment() {

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
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
    }

    override fun viewCreated(savedInstanceState: Bundle?) {
        TODO("Not yet implemented")
    }
}