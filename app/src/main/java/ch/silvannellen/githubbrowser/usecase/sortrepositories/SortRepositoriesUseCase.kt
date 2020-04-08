package ch.silvannellen.githubbrowser.usecase.sortrepositories

import ch.silvannellen.githubbrowser.model.github.CodeRepository
import ch.silvannellen.umvvm.usecase.UseCase

interface SortRepositoriesUseCase : UseCase<Collection<CodeRepository>, Collection<CodeRepository>>