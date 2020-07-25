package ru.iandreyshev.navwithkoinscopestest.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_movie.view.*
import ru.iandreyshev.navwithkoinscopestest.R
import ru.iandreyshev.navwithkoinscopestest.data.Movie

class Adapter(
    private val onItemClick: (Movie) -> Unit
) : ListAdapter<Movie, ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
            .let { view ->
                ViewHolder(view).also { holder ->
                    holder.itemView.backgroundView.setOnClickListener {
                        val position = holder.adapterPosition
                        if (holder.adapterPosition != RecyclerView.NO_POSITION) {
                            onItemClick.invoke(getItem(position))
                        }
                    }
                }
            }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.movieTitle.text = getItem(position).title
    }

}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

object DiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean = false
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean = false
}