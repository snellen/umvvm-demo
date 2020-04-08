package ch.silvannellen.githubbrowser.view.repository

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ch.silvannellen.githubbrowser.databinding.ItemCommitBinding
import ch.silvannellen.githubbrowser.model.github.Commit

class CommitAdapter: RecyclerView.Adapter<CommitAdapter.ViewHolder>() {

    init {
        setHasStableIds(true)
    }

    private val commits = mutableListOf<Commit>()

    fun setRepositories(repos: Collection<Commit>) {
        val diff = DiffUtil.calculateDiff(DiffUtilCallback(commits, repos))
        commits.clear()
        commits.addAll(repos)
        diff.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCommitBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root, binding)
    }

    override fun getItemCount() = commits.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.commit = commits[position]
    }

    override fun getItemId(position: Int): Long = commits[position].id.hashCode().toLong()

    class ViewHolder(view: View, val binding: ItemCommitBinding) : RecyclerView.ViewHolder(view)

    class DiffUtilCallback(private val oldList: Collection<Commit>, private val newList: Collection<Commit>): DiffUtil.Callback() {
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) = (oldList.elementAt(oldItemPosition).id == newList.elementAt(newItemPosition).id)

        override fun getOldListSize() = oldList.size

        override fun getNewListSize()= newList.size

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = (oldList.elementAt(oldItemPosition) == newList.elementAt(newItemPosition))
    }
}