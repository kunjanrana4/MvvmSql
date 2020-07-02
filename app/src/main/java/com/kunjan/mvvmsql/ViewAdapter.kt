package com.kunjan.sqllitedemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kunjan.mvvmsql.R

class ViewAdapter(private val emp:List<EmpModelClass>):RecyclerView.Adapter<ViewAdapter.ViewHolder>(){
    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val idText = itemView.findViewById(R.id.textViewId) as TextView
        val nameText = itemView.findViewById(R.id.textViewName) as TextView
        val emailText = itemView.findViewById(R.id.textViewEmail) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.view_layout,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return emp.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val e = emp[position]
        val id = "ID : " + e.userId.toString()
        val name = "Name : " + e.userName
        val email = "Email : " + e.userEmail
        holder.idText.text = id
        holder.nameText.text = name
        holder.emailText.text = email
    }
}