package ch.silvannellen.githubbrowser.model.github

import java.util.*

data class Commit(val date: Date, val author: String, val message: String, val id: String)