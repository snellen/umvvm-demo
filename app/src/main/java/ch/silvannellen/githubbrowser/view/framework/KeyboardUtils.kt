package ch.silvannellen.githubbrowser.view.framework

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager

object KeyboardUtils {
    fun hideKeyboard(activity: Activity) {
        activity.currentFocus?.let { view ->
            (activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                .hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }
}