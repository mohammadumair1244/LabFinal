package com.shoaibkakal.finallabtermial

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(val userList: ArrayList<User>) : RecyclerView.Adapter<UserAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return VH(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bindData(userList[position])
    }

    override fun getItemCount(): Int {
        return userList.size
    }


    class VH(view: View) : RecyclerView.ViewHolder(view) {

        val name = view.findViewById<TextView>(R.id.name)
        val dob = view.findViewById<TextView>(R.id.dob)
        val age = view.findViewById<TextView>(R.id.age)
        fun bindData(user: User) {
            name.text = user.full_name
            dob.text = user.date_of_birth
            age.text = user.age.toString()
        }
    }

}