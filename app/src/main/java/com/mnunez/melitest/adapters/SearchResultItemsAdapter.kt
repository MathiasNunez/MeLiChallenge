package com.mnunez.melitest.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mnunez.core.extensions.toCurrencyString
import com.mnunez.melitest.R
import com.mnunez.melitest.exensions.loadImage
import com.mnunez.melitest.models.Item
import kotlinx.android.synthetic.main.layout_search_result_item.view.*

class SearchResultItemsAdapter(private var items: ArrayList<Item> = arrayListOf(), private val listener: SearchResultListener?) :
    RecyclerView.Adapter<SearchResultItemsAdapter.SearchResultItemsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultItemsHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_search_result_item, parent, false)
        return SearchResultItemsHolder(view as ViewGroup)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: SearchResultItemsHolder, position: Int) = holder.bind(items[position])

    fun addItems(_items: ArrayList<Item>) {
        items.addAll(_items)
        notifyDataSetChanged()
    }

    inner class SearchResultItemsHolder(itemView: ViewGroup) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Item) {
            itemView.item_name.text = item.title
            itemView.item_thumb.loadImage(item.thumbnail)
            itemView.item_price.text = item.price?.toCurrencyString()
            itemView.item_insttallments.text = itemView.context.getString(
                R.string.installments_amount,
                item.installments?.quantity,
                item.installments?.amount?.toCurrencyString()
            )
            itemView.setOnClickListener {
                listener?.onItemSelected(item)
            }
        }
    }

    interface SearchResultListener{
        fun onItemSelected(item: Item)
    }
}

