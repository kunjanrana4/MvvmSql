package com.kunjan.mvvmsql

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.kunjan.sqllitedemo.DatabaseHandler
import com.kunjan.sqllitedemo.EmpModelClass
import com.kunjan.sqllitedemo.ViewAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var viewModel:MvvmSQLViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MvvmSQLViewModel::class.java)
    }

    fun saveRecord(view: View){
        val id = u_id.text.toString()
        val name = u_name.text.toString()
        val email = u_email.text.toString()
        val status = viewModel.insert(this, Integer.parseInt(id), name, email)
        if(status>-1){
            Toast.makeText(this,"Record Saved Successfully",Toast.LENGTH_SHORT).show()
            u_id.text.clear()
            u_name.text.clear()
            u_email.text.clear()
        }
        else{
            Toast.makeText(this,"Error saving record",Toast.LENGTH_SHORT).show()
        }
    }

    fun viewRecord(view: View){
        val emp = viewModel.view(this)
        val infoAdapter = ViewAdapter(emp)
        val layoutManager = LinearLayoutManager(this)
        recyclerInfoView.layoutManager = layoutManager
        recyclerInfoView.adapter = infoAdapter
        val mDivider = ContextCompat.getDrawable(this, R.drawable.divider)
        val dividerItemDecoration = DividerItemDecoration(recyclerInfoView.context,
            DividerItemDecoration.VERTICAL)
        dividerItemDecoration.setDrawable(mDivider!!)
        recyclerInfoView.addItemDecoration(dividerItemDecoration)
    }

    fun updateRecord(view: View){
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.update_dialog, null)
        dialogBuilder.setView(dialogView)

        val edtId = dialogView.findViewById(R.id.updateId) as EditText
        val edtName = dialogView.findViewById(R.id.updateName) as EditText
        val edtEmail = dialogView.findViewById(R.id.updateEmail) as EditText

        dialogBuilder.setTitle("Update Record")
        dialogBuilder.setMessage("Enter data below")
        dialogBuilder.setPositiveButton("Update", DialogInterface.OnClickListener { _, _ ->

            val updateId = edtId.text.toString()
            val updateName = edtName.text.toString()
            val updateEmail = edtEmail.text.toString()
            val status = viewModel.update(this,updateId,updateName,updateEmail)
            if(status > -1){
                Toast.makeText(applicationContext,"record update", Toast.LENGTH_SHORT).show()
            }
            else
                Toast.makeText(applicationContext,"Record could not be updated", Toast.LENGTH_SHORT).show()
        })
        dialogBuilder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
            //pass
        })
        val b = dialogBuilder.create()
        b.show()
    }

    fun deleteRecord(view: View){
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.delete_dialog, null)
        dialogBuilder.setView(dialogView)

        val dltId = dialogView.findViewById(R.id.deleteId) as EditText
        dialogBuilder.setTitle("Delete Record")
        dialogBuilder.setMessage("Enter id below")
        dialogBuilder.setPositiveButton("Delete", DialogInterface.OnClickListener { _, _ ->
            val deleteId = dltId.text.toString()
            val status = viewModel.delete(this,deleteId)
            if(status > -1){
                Toast.makeText(applicationContext,"Record Deleted Successfully", Toast.LENGTH_SHORT).show()
            }
            else
                Toast.makeText(applicationContext,"Record could not be deleted", Toast.LENGTH_SHORT).show()
        })
        dialogBuilder.setNegativeButton("Cancel", DialogInterface.OnClickListener { _, _ ->
            //pass
        })
        val b = dialogBuilder.create()
        b.show()
    }
}