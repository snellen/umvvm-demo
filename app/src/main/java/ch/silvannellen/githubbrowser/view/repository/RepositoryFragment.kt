package ch.silvannellen.githubbrowser.view.repository

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import ch.silvannellen.githubbrowser.R
import ch.silvannellen.githubbrowser.databinding.FragmentRepositoryBinding
import ch.silvannellen.githubbrowser.di.GithubBrowserApplicationComponent
import ch.silvannellen.githubbrowser.view.framework.GithubBrowserFragment
import ch.silvannellen.githubbrowser.view.repository.di.DaggerRepositoryComponent
import ch.silvannellen.githubbrowser.view.repository.di.RepositoryComponent
import ch.silvannellen.githubbrowser.viewmodel.repo.RepositoryViewModel
import ch.silvannellen.umvvm.viewmodel.BaseViewModel

class RepositoryFragment : GithubBrowserFragment<RepositoryComponent, FragmentRepositoryBinding>() {

    private lateinit var viewModel: RepositoryViewModel

    override val layoutResourceId = R.layout.fragment_repository

    private val args: RepositoryFragmentArgs by navArgs()

    override fun component(applicationComponent: GithubBrowserApplicationComponent): RepositoryComponent =
        DaggerRepositoryComponent.builder()
            .githubBrowserApplicationComponent(applicationComponent)
            .build()

    override fun inject(component: RepositoryComponent) = component.inject(this)

    override fun createViewModels() {
        viewModel = createViewModel(component::getViewModel) {
            it.initialise(args.ownerName, args.repoName)
            it.loadCommits()
        }
    }

    override fun initialiseView(view: View, savedInstanceState: Bundle?) {
        binding.fragment = this
        val linearLayoutManager = LinearLayoutManager(context).apply { orientation = LinearLayoutManager.VERTICAL }
        binding.commitList.apply {
            layoutManager = linearLayoutManager
            adapter = CommitAdapter()
            addItemDecoration(DividerItemDecoration(context, linearLayoutManager.orientation))
        }
        binding.commitListRefresh.setOnRefreshListener {
            viewModel.refreshCommitsList()
        }
    }

    override fun observeViewModel(viewModel: BaseViewModel) {
        super.observeViewModel(viewModel)
        if (viewModel is RepositoryViewModel) {
            binding.viewModel = viewModel // This will bind the views to the view model


            viewModel.commits.observe(viewLifecycleOwner, Observer {
                (binding.commitList.adapter as CommitAdapter).setRepositories(it)
            })

            viewModel.refreshingCommits.observe(viewLifecycleOwner, Observer {
                binding.commitListRefresh.isRefreshing = it
            })
        }
    }
}