package ch.silvannellen.githubbrowser.view.framework

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import ch.silvannellen.githubbrowser.GithubBrowserApplication
import ch.silvannellen.githubbrowser.di.GithubBrowserApplicationComponent
import ch.silvannellen.umvvm.view.BaseViewFragment

/**
 * Base class for all fragments of this app. It takes care of dependency injection, inflating the view (thereby initialising the binding)
 */
abstract class GithubBrowserFragment<Component : Any, ViewBinding : ViewDataBinding> :
    BaseViewFragment() {

    protected lateinit var component: Component

    protected lateinit var binding: ViewBinding

    protected abstract val layoutResourceId: Int

    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        component =
            activity?.let { component((it.applicationContext as GithubBrowserApplication).applicationComponent) }
                ?: throw IllegalStateException()
        inject(component)
    }

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            layoutResourceId,
            container,
            false
        )
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    /**
     * Setup Dagger component and inject this implementation of a GithubBrowserFragment.
     */
    protected abstract fun component(applicationComponent: GithubBrowserApplicationComponent): Component

    /**
     * Inject members into this GithubBrowserFragment using the given component.
     */
    protected abstract fun inject(component: Component)

}