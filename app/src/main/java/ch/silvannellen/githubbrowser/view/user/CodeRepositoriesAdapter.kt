package ch.silvannellen.githubbrowser.view.user

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ch.silvannellen.githubbrowser.databinding.ItemCodeRepositoryBinding
import ch.silvannellen.githubbrowser.model.github.CodeRepository

class CodeRepositoriesAdapter: RecyclerView.Adapter<CodeRepositoriesAdapter.ViewHolder>() {

    init {
        setHasStableIds(true)
    }

    private val repositories = mutableListOf<CodeRepository>()

    fun setRepositories(repos: Collection<CodeRepository>) {
        val diff = DiffUtil.calculateDiff(DiffUtilCallback(repositories, repos))
        repositories.clear()
        repositories.addAll(repos)
        diff.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCodeRepositoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root, binding)
    }

    override fun getItemCount() = repositories.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.repository = repositories[position]
    }

    override fun getItemId(position: Int): Long = repositories[position].id

    class ViewHolder(view: View, val binding: ItemCodeRepositoryBinding) : RecyclerView.ViewHolder(view)

    class DiffUtilCallback(private val oldList: Collection<CodeRepository>, private val newList: Collection<CodeRepository>): DiffUtil.Callback() {
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) = (oldList.elementAt(oldItemPosition).id == newList.elementAt(newItemPosition).id)

        override fun getOldListSize() = oldList.size

        override fun getNewListSize()= newList.size

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = (oldList.elementAt(oldItemPosition) == newList.elementAt(newItemPosition))
    }
}