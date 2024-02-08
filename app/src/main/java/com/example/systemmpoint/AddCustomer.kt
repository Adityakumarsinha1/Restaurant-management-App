package com.example.systemmpoint

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.systemmpoint.adapter.MinusClickInterface
import com.example.systemmpoint.adapter.OrderAdapter
import com.example.systemmpoint.adapter.PlusClickInterface
import com.example.systemmpoint.customerDB.CustmerTable
import com.example.systemmpoint.customerDB.CustomerViewModel
import com.example.systemmpoint.databinding.ActivityAddCustomerBinding
import com.example.systemmpoint.itemDB.ItemTable
import com.example.systemmpoint.itemDB.ItemViewModel
import com.example.systemmpoint.orderDB.OrderViewModel
import java.text.SimpleDateFormat
import java.util.Date

class AddCustomer : AppCompatActivity() , PlusClickInterface, MinusClickInterface {


    private lateinit var binding: ActivityAddCustomerBinding

    private lateinit var menuRV1: RecyclerView
    private lateinit var orderAdapter: OrderAdapter
    private lateinit var saveBTN : Button



    lateinit var cname: EditText
    lateinit var viewModel: CustomerViewModel
    lateinit var viewModel2: ItemViewModel
    lateinit var viewModel3: OrderViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(this,"Minus", Toast.LENGTH_LONG).show()

        binding = ActivityAddCustomerBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_add_customer)

        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(CustomerViewModel::class.java)

        cname = findViewById(R.id.customersnameET)
        saveBTN = findViewById(R.id.NewOrderBTN)
        menuRV1 =  findViewById(R.id.menuRV1)


        orderAdapter= OrderAdapter(this,this,this)
        menuRV1.layoutManager=LinearLayoutManager(this)
        menuRV1.adapter= this.orderAdapter

        Log.d("@@@", "opened AddCustomer")




        viewModel2 = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory
            .getInstance(application)).get(ItemViewModel::class.java)
        viewModel3= ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application))
            .get(OrderViewModel::class.java)


        Log.d("@@@@@", viewModel.customerById.toString())
        viewModel2.allItems.observe(this){list->
            list?.let {
                Log.d("@@@", "$it")
                orderAdapter.updateList(it)

            }
        }


        saveBTN.setOnClickListener {
            val name = cname.text.toString()
            if (name.isNotEmpty()){

//                Log.d("@@@", "update btn clicked")

                val sdf = SimpleDateFormat("dd MMM , yyyy -  HH:mm")
                val  currentDate:String = sdf.format(Date())
                val updateCustomer = CustmerTable(name,currentDate,"0")
//                updateCustomer.id=customerid
                viewModel.insertCustomer(updateCustomer)
                viewModel.customerById


                Toast.makeText(this, "Note Updated",Toast.LENGTH_LONG ).show()
                updateui()

            }
            else
                Toast.makeText(this,"Enter The Customers Name", Toast.LENGTH_LONG).show()
        }
    }

    private fun updateui() {
        val intent = Intent(this@AddCustomer,MainActivity::class.java)
        startActivity(intent)
        this.finish()
    }

    override fun onminuslicked(item: ItemTable) {
        Toast.makeText(this,"Minus", Toast.LENGTH_LONG).show()

    }

    override fun onplusclicked(item: ItemTable) {
        Toast.makeText(this,"plus", Toast.LENGTH_LONG).show()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
}