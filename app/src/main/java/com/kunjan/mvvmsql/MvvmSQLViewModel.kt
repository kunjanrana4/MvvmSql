package com.kunjan.mvvmsql

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.kunjan.sqllitedemo.DatabaseHandler
import com.kunjan.sqllitedemo.EmpModelClass

class MvvmSQLViewModel:ViewModel() {
    fun insert(context: Context,id:Int,name:String,email:String):Long {
        val databaseHandler = DatabaseHandler(context)
        var status = (-1).toLong()
        if (id != 0 && name.trim() != "" && email.trim() != "") {
            status = databaseHandler.addEmployee(EmpModelClass(id, name, email))
        }
        return status
    }

    fun view(context: Context):List<EmpModelClass>{
        val databaseHandler = DatabaseHandler(context)
        return databaseHandler.viewEmployee()
    }

    fun update(context: Context,updateId:String,updateName:String,updateEmail:String):Int{
        var  status = -1
        val databaseHandler = DatabaseHandler(context)
        if(updateId.trim()!="" && updateName.trim()!="" && updateEmail.trim()!=""){
            status = databaseHandler.updateEmployee(EmpModelClass(Integer.parseInt(updateId),updateName, updateEmail))
        }
        else{
            Toast.makeText(context,"id or name or email cannot be blank", Toast.LENGTH_LONG).show()
        }
        return status
    }

    fun delete(context: Context,deleteId:String):Int{
        val databaseHandler = DatabaseHandler(context)
        return if(deleteId.trim()!=""){
            databaseHandler.deleteEmployee(EmpModelClass(Integer.parseInt(deleteId),"",""))
        }else{
            Toast.makeText(context,"id cannot be blank",Toast.LENGTH_LONG).show()
            -1
        }
    }
}