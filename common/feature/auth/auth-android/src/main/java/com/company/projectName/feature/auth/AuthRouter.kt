package com.company.projectName.feature.auth

import com.company.projectName.auth.model.mvu.AuthEffect
import com.company.core.model.navigation.Navigation
import com.company.core.model.navigation.Navigator
import com.company.projectName.auth.model.AuthNavigation
import com.company.projectName.feature.auth.signin.SignInFragment
import com.company.projectName.feature.auth.signup.SignUpFragment
import com.darkos.core.presentation.router.ActivityRouter
import com.darkos.feature.auth.R

class AuthRouter(
    appNavigator: Navigator
): ActivityRouter(), Navigator {

    override val containerId = R.id.fragmentContainer
    override var subNavigators: List<Navigator> = emptyList()

    init {
        appNavigator.attach(this)
    }

    override fun navigate(navigation: Navigation): Boolean {
        if (navigation !is AuthNavigation) {
            return false
        }

        when (navigation) {
            is AuthNavigation.NavigateToSignIn -> createInstance<SignInFragment>().replace()
            is AuthNavigation.NavigateToSignUp -> createInstance<SignUpFragment>().replace()
        }

        return true
    }
}