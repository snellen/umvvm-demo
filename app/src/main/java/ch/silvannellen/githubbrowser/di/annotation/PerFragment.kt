package ch.silvannellen.githubbrowser.di.annotation

import javax.inject.Scope

/**
 * Scope for components whose lifecycle is tied to that of a fragment.
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class PerFragment {
}