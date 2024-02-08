package com.example.systemmpoint.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.systemmpoint.R
import com.example.systemmpoint.itemDB.ItemTable

class OrderAdapter(val context: Context
                   , val plusClickInterface: PlusClickInterface
                   , val minusClickInterface: MinusClickInterface
): RecyclerView.Adapter<OrderAdapter.OrderViewHolder>()
    {
        private var allitems = ArrayList<ItemTable>()
        private var quant = Int


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
                OrderAdapter.OrderViewHolder {
            val itemView= LayoutInflater.from(parent.context).inflate(
                R.layout.menu_items,
                parent,
                false)

            return OrderViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: OrderAdapter.OrderViewHolder, position: Int) {
            holder.name.text= allitems[position].name
//            holder.quantitty.text= quant.toString()
            holder.minus.setOnClickListener {
                minusClickInterface.onminuslicked(allitems[position])
            }

            holder.plus.setOnClickListener {
                plusClickInterface.onplusclicked(allitems[position])
            }
        }

        override fun getItemCount(): Int {
            return allitems.size
        }



        class OrderViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
            val name = itemView.findViewById<TextView>(R.id.name)
            val plus= itemView.findViewById<ImageButton>(R.id.increase)
            val minus = itemView.findViewById<ImageButton>(R.id.decrease)
            val quantitty = itemView.findViewById<TextView>(R.id.quantity)
        }
        fun updateList(it: List<ItemTable>) {
            allitems.clear()
            allitems.addAll(it)
            notifyDataSetChanged()
        }
    }

interface  MinusClickInterface {
    fun onminuslicked(item:ItemTable)
}

interface PlusClickInterface {
    fun onplusclicked(item: ItemTable)
}