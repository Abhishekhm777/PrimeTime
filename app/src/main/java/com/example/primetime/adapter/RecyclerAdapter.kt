package com.example.androiddata.ui.main

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.primetime.R
import com.example.primetime.model.Content


class RecyclerAdapter(val context: Context,
                      val contents:List<Content>,
                      val itemClickListner: ItemClickListner):
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(),Filterable {

    var searchableList: ArrayList<Content> = contents as ArrayList<Content>
    private var onNothingFound: (() -> Unit)? = null
    override fun getItemCount() = searchableList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.movies_card, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val content = searchableList[position]
        with(holder) {
            nameText.text = content.name
            val drawableName =
                content.posterimage.substring(0, content.posterimage.lastIndexOf('.'))
            Log.e("TAG", "onBindViewHolder: $drawableName")
            val resID = context.resources.getIdentifier(
                drawableName,
                "drawable",
                context.packageName
            )
            Glide.with(context)
                .load(resID)
                .placeholder(R.drawable.placeholder_for_missing_posters)
                .into(image)
        }

    }


    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val nameText = itemView.findViewById<TextView>(R.id.title)
        val image = itemView.findViewById<ImageView>(R.id.imageView)

    }

    interface ItemClickListner {

        fun onItemClick(monster: Content)

    }

    override fun getFilter(): Filter {
        return object : Filter() {
            private val filterResults = FilterResults()
            override fun performFiltering(constraint: CharSequence?): FilterResults {

                if (constraint.isNullOrBlank()) {
                  searchableList = contents as ArrayList<Content>
                } else {
                    val filtered =ArrayList<Content>()
                    val filterPattern = constraint.toString().toLowerCase().trim { it <= ' ' }
                    for (item in contents) {
                        if(item.name.contains(filterPattern,true)){
                            filtered.add(item)
                        }
                    }
                   searchableList  = filtered
                }
                return filterResults.also {
                    it.values = searchableList
                }
            }
            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
               if(results?.values!=null) {
                   searchableList = results?.values as ArrayList<Content>
                   notifyDataSetChanged()
               }


            }
        }

    }
}