package com.example.systemmpoint

import android.app.AlertDialog
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
import com.example.systemmpoint.adapter.MenuAdapter
import com.example.systemmpoint.adapter.MenuDeleteInterface
import com.example.systemmpoint.databinding.ActivityMenuBinding
import com.example.systemmpoint.itemDB.ItemTable
import com.example.systemmpoint.itemDB.ItemViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MenuActivity : AppCompatActivity(), MenuDeleteInterface {

    private lateinit var binding: ActivityMenuBinding
    private lateinit var menuRV: RecyclerView
    private lateinit var menuAdapter: MenuAdapter
    lateinit var viewModel: ItemViewModel
    lateinit var fabadd: FloatingActionButton

    private lateinit var name : String
    private lateinit var price : String
    var itemid = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_menu)

        fabadd = findViewById(R.id.addmenuitemFAB)

        menuRV = findViewById(R.id.menuRV)
        menuAdapter=MenuAdapter(this,this)
        menuRV.layoutManager= LinearLayoutManager(this)
        menuRV.adapter= this.menuAdapter




        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory
            .getInstance(application)).get(ItemViewModel::class.java)

//        Log.d("@@@", " view model $viewModel")

        viewModel.allItems.observe(this){list->
            list?.let {
                Log.d("@@@", "$it")
                menuAdapter.updateList(it)

            }
        }



        fabadd.setOnClickListener {
            launchDialog()
        }
    }

    private fun launchDialog() {
        val builder = AlertDialog.Builder(this)
            .create()
        val view = layoutInflater.inflate(R.layout.additem_dialog,null)
        val  addbutton = view.findViewById<Button>(R.id.btn_okay)
        val  canclebutton = view.findViewById<Button>(R.id.btn_cancel)
        val  iname = view.findViewById<EditText>(R.id.itemnameET)
        val iprice  = view.findViewById<EditText>(R.id.itempriceET)

        builder.setView(view)
        canclebutton.setOnClickListener {
            builder.dismiss()
        }
        addbutton.setOnClickListener {
            name= iname.text.toString()
            price = iprice.text.toString()

            if (name.isNotEmpty() && price.isNotEmpty()){

                Log.d("@@@", " menu activity update btn clicked")

                val updateMenu = ItemTable(name,price)
                Log.d("@@@", " 1")
//                updateMenu.id=itemid
                Log.d("@@@", " 2")
                viewModel.insertItem(updateMenu)
                Toast.makeText(this, "Menu Updated", Toast.LENGTH_LONG ).show()

            }
            else
                Toast.makeText(this,"Details Can't be empty", Toast.LENGTH_LONG).show()

            builder.dismiss()
        }
        builder.setCanceledOnTouchOutside(false)
        builder.show()

    }

    override fun onDeleteIconClick(item: ItemTable) {
        val builder = AlertDialog.Builder(this)

        // Set the message show for the Alert time
        builder.setMessage(item.name)

        // Set Alert Title
        builder.setTitle("Confirm Delete")

        // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
        builder.setCancelable(false)

        // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
        builder.setPositiveButton("Yes") {
            // When the user click yes button then app will close
                dialog, which -> dialog.cancel()

            viewModel.deleteItem((item))

        }

        // Set the Negative button with No name Lambda OnClickListener method is use of DialogInterface interface.
        builder.setNegativeButton("No") {
                dialog, which -> dialog.cancel()
        }

        // Create the Alert dialog
        val alertDialog = builder.create()
        // Show the Alert Dialog box
        alertDialog.show()
    }

    override fun onBackPressed() {
        super.onBackPressed()
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
    }
}