package com.mnunez.melitest.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mnunez.melitest.R
import com.mnunez.melitest.models.Attributes
import kotlinx.android.synthetic.main.layout_item_description.view.*


class ItemDescriptionAdapter(private var items: ArrayList<Attributes> = arrayListOf()) :
    RecyclerView.Adapter<ItemDescriptionAdapter.AttributesHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttributesHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_item_description, parent, false)
        return AttributesHolder(view as ViewGroup)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: AttributesHolder, position: Int) = holder.bind(items[position])

    inner class AttributesHolder(itemView: ViewGroup) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Attributes) {
            itemView.description_name.text =
                itemView.context.getString(R.string.item_details_description_name, item.name)
            itemView.description_value.text = item.value
        }
    }

}