package ch.silvannellen.githubbrowser.view.login

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import ch.silvannellen.githubbrowser.R
import ch.silvannellen.githubbrowser.databinding.FragmentLoginBinding
import ch.silvannellen.githubbrowser.di.GithubBrowserApplicationComponent
import ch.silvannellen.githubbrowser.view.framework.GithubBrowserFragment
import ch.silvannellen.githubbrowser.view.framework.KeyboardUtils
import ch.silvannellen.githubbrowser.view.login.di.DaggerLoginComponent
import ch.silvannellen.githubbrowser.view.login.di.LoginComponent
import ch.silvannellen.githubbrowser.viewmodel.common.EventObserver
import ch.silvannellen.githubbrowser.viewmodel.login.LoginViewModel
import ch.silvannellen.umvvm.viewmodel.BaseViewModel

class LoginFragment : GithubBrowserFragment<LoginComponent, FragmentLoginBinding>() {

    private lateinit var viewModel: LoginViewModel

    override val layoutResourceId = R.layout.fragment_login

    override fun component(applicationComponent: GithubBrowserApplicationComponent): LoginComponent =
        DaggerLoginComponent.builder()
            .githubBrowserApplicationComponent(applicationComponent)
            .build()

    override fun inject(component: LoginComponent) = component.inject(this)

    override fun createViewModels() {
        viewModel = createViewModel(component::getViewModel)
    }

    override fun initialiseView(view: View, savedInstanceState: Bundle?) {
        binding.fragment = this
    }

    override fun observeViewModel(viewModel: BaseViewModel) {
        super.observeViewModel(viewModel)
        if (viewModel is LoginViewModel) {
            binding.viewModel = viewModel // This will bind the views to the view model

            viewModel.navigateToWelcomeFragment.observe(viewLifecycleOwner,
                EventObserver {
                    activity?.let { KeyboardUtils.hideKeyboard(it) }
                    findNavController().navigate(
                        LoginFragmentDirections.actionLoginFragmentToUserFragment(
                            it
                        )
                    )
                })
        }
    }

    fun onAfterCredentialsChanged() {
        viewModel.onCredentialsChanged(
            binding.username.text.toString(),
            binding.accessToken.text.toString()
        )
    }

    fun onLoginRequested(view: View) {
        viewModel.onLoginRequested(
            binding.username.text.toString(),
            binding.accessToken.text.toString()
        )
    }

}