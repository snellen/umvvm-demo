package ch.silvannellen.githubbrowser.view.user

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import ch.silvannellen.githubbrowser.R
import ch.silvannellen.githubbrowser.databinding.FragmentUserBinding
import ch.silvannellen.githubbrowser.di.GithubBrowserApplicationComponent
import ch.silvannellen.githubbrowser.viewmodel.common.EventObserver
import ch.silvannellen.githubbrowser.view.framework.GithubBrowserFragment
import ch.silvannellen.githubbrowser.view.user.di.DaggerUserComponent
import ch.silvannellen.githubbrowser.view.user.di.UserComponent
import ch.silvannellen.githubbrowser.viewmodel.user.UserViewModel
import ch.silvannellen.umvvm.viewmodel.BaseViewModel
import com.squareup.picasso.Picasso

class UserFragment : GithubBrowserFragment<UserComponent, FragmentUserBinding>() {

    private lateinit var viewModel: UserViewModel

    override val layoutResourceId = R.layout.fragment_user

    private val args: UserFragmentArgs by navArgs()

    override fun component(applicationComponent: GithubBrowserApplicationComponent): UserComponent =
        DaggerUserComponent.builder()
            .githubBrowserApplicationComponent(applicationComponent)
            .build()

    override fun inject(component: UserComponent) = component.inject(this)

    override fun createViewModels() {
        viewModel = createViewModel(component::getViewModel) { it.loadProfile(args.userName) }
    }

    override fun initialiseView(view: View, savedInstanceState: Bundle?) {
        binding.fragment = this
    }

    override fun observeViewModel(viewModel: BaseViewModel) {
        super.observeViewModel(viewModel)
        if (viewModel is UserViewModel) {
            binding.viewModel = viewModel // This will bind the views to the view model

            viewModel.userAvatarUrl.observe(viewLifecycleOwner, Observer {
                Picasso.get().load(it).into(binding.userAvatar)
            })

        }
    }
}