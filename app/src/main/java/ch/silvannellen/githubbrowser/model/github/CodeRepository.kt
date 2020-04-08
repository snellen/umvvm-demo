package ch.silvannellen.githubbrowser.model.github

import java.util.*

/**
 * Represents a repository on GitHub
 */
data class CodeRepository(val id: Long, val name: String, val language: String?, val updatedAt: Date)