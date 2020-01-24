package com.target.dealbrowserpoc.dealbrowser.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.target.businesslogic.model.Product
import com.target.dealbrowserpoc.dealbrowser.R

/**
 * Created by Pankaj Nimgade on 1/19/2020.
 */
class DealListItemAdapter(private val listener: DealListItemListener?) :
    RecyclerView.Adapter<DealListItemAdapter.DealItemViewHolder>() {

    private val list: MutableList<Product> = mutableListOf()

    class DealItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val dealImageView: ImageView = itemView.findViewById(R.id.item_imageView)
        private val titleTextView: TextView = itemView.findViewById(R.id.item_title_text_view)
        private val priceTextView: TextView = itemView.findViewById(R.id.item_price_text_view)
        private val aisleTextView: TextView = itemView.findViewById(R.id.item_ailse_text_view)

        fun bind(product: Product) {
            dealImageView.load(product.image ?: "http://lorempixel.com/400/400/") {
                placeholder(R.drawable.ic_loading)
            }
            titleTextView.text = product.title?.capitalize() ?: ""
            priceTextView.text = product.price?.toUpperCase() ?: ""
            aisleTextView.text = product.aisle?.toUpperCase() ?: ""
        }
    }

    fun updateList(newList: List<Product>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.deal_list_item, parent, false)
        val viewHolder = DealItemViewHolder(view)
        viewHolder.itemView.setOnClickListener {
            listener?.onDealListItemClicked(list[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: DealItemViewHolder, position: Int) {
        holder.bind(list[position])
    }


    interface DealListItemListener {
        fun onDealListItemClicked(product: Product)
    }
}