package com.mnunez.melitest.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mnunez.melitest.R
import kotlinx.android.synthetic.main.layout_recent_search_item.view.*


class RecentSearchAdapter(private val listener: RecentSearchListener) :
    RecyclerView.Adapter<RecentSearchAdapter.RecentSearchHolder>() {

    private var items: ArrayList<String> = arrayListOf()
    private var auxItems: ArrayList<String> = arrayListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentSearchHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_recent_search_item, parent, false)
        return RecentSearchHolder(view as ViewGroup)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecentSearchHolder, position: Int) = holder.bind(items[position])

    fun loadData(_items: ArrayList<String>){
        items = _items
        auxItems.clear()
        auxItems.addAll(items)
    }

    fun filter(query: String) {
        val searchQuery = query.toLowerCase()
        items.clear()
        if (query.isEmpty()) {
            items.addAll(auxItems)
        } else {
            auxItems.forEach {
                if (it.toLowerCase().contains(searchQuery)) {
                    items.add(it)
                }
            }
        }
        notifyDataSetChanged()
    }


    inner class RecentSearchHolder(itemView: ViewGroup) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: String) {
            itemView.search_name.text = item
            itemView.setOnClickListener {
                listener.onRecentSearchClicked(item)
            }

        }
    }

    interface RecentSearchListener{
        fun onRecentSearchClicked(query: String)
    }

}