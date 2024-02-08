package com.example.systemmpoint

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.systemmpoint.adapter.CustomerListAdapter
import com.example.systemmpoint.adapter.NameClickInterface
import com.example.systemmpoint.adapter.NameDeleteInterface
import com.example.systemmpoint.customerDB.CustmerTable
import com.example.systemmpoint.customerDB.CustomerViewModel
import com.example.systemmpoint.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() , NameClickInterface , NameDeleteInterface{

    private lateinit var binding: ActivityMainBinding
    private lateinit var customerRV: RecyclerView
    private lateinit var customerAdapter: CustomerListAdapter
    lateinit var viewModel: CustomerViewModel
    lateinit var fabadd: FloatingActionButton
    lateinit var menuBtn : Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        customerRV= findViewById(R.id.customerRV)
        customerAdapter= CustomerListAdapter(this,this,this)
        customerRV.layoutManager=LinearLayoutManager(this)
        customerRV.adapter= this.customerAdapter

        fabadd=findViewById(R.id.addcustomerFAB)
        menuBtn = findViewById(R.id.menuBtn)




        viewModel =ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory
            .getInstance(application)).get(CustomerViewModel::class.java)
        viewModel.allCustomer.observe(this){list->
            list?.let {
//                Log.d("@@@", "$it")
                customerAdapter.updateList(it)

            }
        }



        fabadd.setOnClickListener {
            val intent = Intent(this@MainActivity,AddCustomer::class.java)
            startActivity(intent)
            this.finish()
//            Toast.makeText(this ,"FAB clicked" , Toast.LENGTH_LONG).show()
        }

        menuBtn.setOnClickListener {
            val intent = Intent(this@MainActivity,MenuActivity::class.java)
            startActivity(intent)
            this.finish()
            Toast.makeText(this ," Clicked menu" , Toast.LENGTH_LONG).show()
        }
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onNameClick(custmer: CustmerTable) {
        val intent = Intent(this@MainActivity,UpdateCustomer::class.java)
            intent.putExtra(UpdateCustomer.CUID, custmer.id)
            intent.putExtra(UpdateCustomer.CNAME, custmer.name)
            intent.putExtra(UpdateCustomer.CTOTAL, custmer.total)
        startActivity(intent)
        this.finish()
    }

    override fun onDeleteIconClick(custmer: CustmerTable) {
        viewModel.deleteCustomer((custmer))
        Toast.makeText(this ," ${custmer.name} Deleted" , Toast.LENGTH_LONG).show()
    }
}