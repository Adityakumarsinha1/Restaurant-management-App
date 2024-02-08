package com.example.systemmpoint

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.systemmpoint.adapter.MinusClickInterface
import com.example.systemmpoint.adapter.OrderAdapter
import com.example.systemmpoint.adapter.PlusClickInterface
import com.example.systemmpoint.customerDB.CustomerViewModel
import com.example.systemmpoint.databinding.ActivityUpdateCustomerBinding
import com.example.systemmpoint.itemDB.ItemTable
import com.example.systemmpoint.itemDB.ItemViewModel
import com.example.systemmpoint.orderDB.OrderTable
import com.example.systemmpoint.orderDB.OrderViewModel

class UpdateCustomer : AppCompatActivity() , PlusClickInterface, MinusClickInterface{

    private lateinit var binding: ActivityUpdateCustomerBinding

    private lateinit var menuRV1: RecyclerView
    private lateinit var orderAdapter: OrderAdapter
    private lateinit var saveBTN : Button



    lateinit var cname: TextView
    lateinit var viewModel: CustomerViewModel
    lateinit var viewModel2: ItemViewModel
    lateinit var viewModel3: OrderViewModel
    var customerid = -1


//    var edge : String? = null
//    var quantity: Int =0

    companion object{
        val CUID= "uid_extra"
        const val CNAME="name_extra"
        const val CTOTAL="total_extra"
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateCustomerBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_update_customer)

        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(CustomerViewModel::class.java)

        cname = findViewById(R.id.customersnameTV)
        saveBTN = findViewById(R.id.updateOrderBTN)
        menuRV1 =  findViewById(R.id.menuRV01)
        val tempo: ArrayList<OrderTable>

        val receiverID = intent.getStringExtra(UpdateCustomer.CUID)
        val receiverName = intent.getStringExtra(UpdateCustomer.CNAME)
        val receiverAmount = intent.getStringExtra(UpdateCustomer.CTOTAL)


        cname.text = receiverName


//        Log.d("@@@", "$receiverName")
//        Log.d("@@@", "opened AddCustomer")

        orderAdapter= OrderAdapter(this,this,this)
        menuRV1.layoutManager= LinearLayoutManager(this)
        menuRV1.adapter= this.orderAdapter

        Log.d("@@@", "opened AddCustomer")




        viewModel2 = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory
            .getInstance(application)).get(ItemViewModel::class.java)


        viewModel2.allItems.observe(this){list->
            list?.let {
                Log.d("@@@", "$it")
                orderAdapter.updateList(it)

            }
        }


        saveBTN.setOnClickListener {
//            val name = cname.text.toString()
//            if (name.isNotEmpty()){
//
////                Log.d("@@@", "update btn clicked")
//
//                val sdf = SimpleDateFormat("dd MMM , yyyy -  HH:mm")
//                val  currentDate:String = sdf.format(Date())
//                val updateCustomer = CustmerTable(name,currentDate,"0")
////                updateCustomer.id=customerid
//                viewModel.insertCustomer(updateCustomer)
//
                Toast.makeText(this, "Note Updated", Toast.LENGTH_LONG ).show()
//                updateui()
//
//            }
//            else
//                Toast.makeText(this,"Enter The Customers Name", Toast.LENGTH_LONG).show()
        }
    }
    private fun updateui() {
        val intent = Intent(this@UpdateCustomer,MainActivity::class.java)
        startActivity(intent)
        this.finish()
    }

    override fun onminuslicked(item: ItemTable) {
       val quantiry: Int
       val total : Int
    }

    override fun onplusclicked(item: ItemTable) {
        TODO("Not yet implemented")
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
}