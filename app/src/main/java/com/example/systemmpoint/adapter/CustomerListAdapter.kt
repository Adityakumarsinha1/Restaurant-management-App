package com.example.systemmpoint.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.systemmpoint.R
import com.example.systemmpoint.customerDB.CustmerTable

class CustomerListAdapter(val context: Context
                          , val nameClickInterface: NameClickInterface
                          , val nameDeleteInterface: NameDeleteInterface
                            ): RecyclerView.Adapter<CustomerListAdapter.CustomerViewHolder>(){


    private var allcustomers = ArrayList<CustmerTable>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {
        val itemView= LayoutInflater.from(parent.context).inflate(
            R.layout.customeritem,
            parent,
            false)

        return CustomerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {
        holder.name.text= allcustomers[position].name
        holder.date.text=allcustomers[position].time
        holder.total.text=allcustomers[position].total
        holder.delete.setOnClickListener {
            nameDeleteInterface.onDeleteIconClick(allcustomers[position])
        }

        holder.customerblock.setOnClickListener {
            nameClickInterface.onNameClick(allcustomers[position])
        }
    }

    override fun getItemCount(): Int {
        return (allcustomers.size)
    }

    class CustomerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        val name = itemView.findViewById<TextView>(R.id.customernameTV)
        val delete= itemView.findViewById<Button>(R.id.deleteBTN)
        val date = itemView.findViewById<TextView>(R.id.customerdateTV)
        val customerblock = itemView.findViewById<LinearLayout>(R.id.customerONCLICK)
        val total =itemView.findViewById<TextView>(R.id.customertotalTV)
    }

    fun updateList(newList: List<CustmerTable>){
        allcustomers.clear()
        allcustomers.addAll(newList)
        notifyDataSetChanged()
    }

}
interface NameClickInterface{
    fun onNameClick(custmer: CustmerTable)
}
interface NameDeleteInterface{
    fun onDeleteIconClick(custmer: CustmerTable)
}