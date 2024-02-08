package com.example.systemmpoint.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.systemmpoint.R
import com.example.systemmpoint.itemDB.ItemTable

class MenuAdapter(val context: Context
                          , val menuDeleteInterface: MenuDeleteInterface
): RecyclerView.Adapter<MenuAdapter.MenuViewHolder>(){


    private var allitems = ArrayList<ItemTable>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val itemView= LayoutInflater.from(parent.context).inflate(
            R.layout.menu_list,
            parent,
            false)

        return MenuViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.name.text= allitems[position].name
        holder.price.text=allitems[position].price

        holder.delete.setOnClickListener {
            menuDeleteInterface.onDeleteIconClick(allitems[position])
        }

    }

    override fun getItemCount(): Int {
        return (allitems.size)
    }

    class MenuViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        val name = itemView.findViewById<TextView>(R.id.itemnameTV)
        val delete= itemView.findViewById<Button>(R.id.itemdeleteBTN)
        val price = itemView.findViewById<TextView>(R.id.itempriceTV)

    }

    fun updateList(newList: List<ItemTable>){
        allitems.clear()
        allitems.addAll(newList)
        notifyDataSetChanged()
    }

}
interface MenuDeleteInterface{
    fun onDeleteIconClick(item:ItemTable)
}