package com.darkos.projectName.feature.auth

import com.darkos.core.model.navigation.Navigation
import com.darkos.core.model.navigation.Navigator
import com.darkos.projectName.auth.model.AuthNavigation
import com.darkos.projectName.feature.auth.signin.SignInFragment
import com.darkos.projectName.feature.auth.signup.SignUpFragment
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