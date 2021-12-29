package com.shoaibkakal.finallabtermial

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue


class MainActivity : AppCompatActivity() {
    val database = FirebaseDatabase.getInstance()
    val ref = database.getReference("users")

    private lateinit var adapter: UserAdapter
    private lateinit var recyclerView: RecyclerView

    private val userList = ArrayList<User>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //init
        recyclerView = findViewById(R.id.recyclerView)

        val users: MutableMap<String, User> = HashMap()
        users["shoaib"] = User(0, "June 23, 1912", "Shoaib Khalid", 20)
        users["ali"] = User(1, "December 19, 1906", "Ali Ahmad", 23)
        users["abdullah"] = User(2, "January 9, 1906", "Abdullah Aslam", 18)
        users["zara"] = User(3, "March 9, 1906", "Zara Ehsan", 34)
        users["farman"] = User(4, "December 9, 1906", "Farman Ali", 40)
        users["akhzar"] = User(5, "December 9, 2020", "Akhzar Ali", 40)
        users["lisan"] = User(6, "July 9, 2020", "Ali Lisan", 70)

        ref.setValue(users).addOnSuccessListener {

        }
            .addOnFailureListener {


            }


//        getFirstTwo()
//        nameStartWith()

        getUserByAge()
    }


    private fun users() {
        userList.forEach {
            Log.d("testTag", "users: ${it.full_name}\n${it.date_of_birth}")
        }
    }


    private fun getAllUsers() {
        ref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                snapshot.children.forEach {

                    val data = it.getValue<User>()
                    val dob = data?.date_of_birth
                    val name = data?.full_name
                    val age = data?.age
                    val id = data?.id
                    userList.add(User(id!!, dob!!, name!!, age!!))
                }

                adapter = UserAdapter(userList)
                recyclerView.adapter = adapter
                adapter.notifyDataSetChanged()

            }


            override fun onCancelled(error: DatabaseError) {
                Log.w("testTag", "Failed to read value.", error.toException())
            }


        })

    }

    private fun getFirstTwo() {
        userList.clear()
        val query: Query = FirebaseDatabase.getInstance().getReference("users")
            .limitToFirst(2)



        query.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                snapshot.children.forEach {

                    val data = it.getValue<User>()
                    val dob = data?.date_of_birth
                    val name = data?.full_name
                    val age = data?.age
                    val id = data?.id
                    userList.add(User(id!!, dob!!, name!!, age!!))
                }

                adapter = UserAdapter(userList)
                recyclerView.adapter = adapter
                adapter.notifyDataSetChanged()
                users()

            }


            override fun onCancelled(error: DatabaseError) {
                Log.w("testTag", "Failed to read value.", error.toException())
            }


        })
    }


    private fun nameStartWith() {
        val query = FirebaseDatabase.getInstance().getReference("users")
            .orderByChild("full_name")
            .startAt("A")
            .endAt("A\uf8ff")

        query.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                snapshot.children.forEach {

                    val data = it.getValue<User>()
                    val dob = data?.date_of_birth
                    val name = data?.full_name
                    val age = data?.age
                    val id = data?.id
                    userList.add(User(id!!, dob!!, name!!, age!!))
                }

                adapter = UserAdapter(userList)
                recyclerView.adapter = adapter
                adapter.notifyDataSetChanged()
                users()

            }


            override fun onCancelled(error: DatabaseError) {
                Log.w("testTag", "Failed to read value.", error.toException())
            }


        })
    }

    private fun getUserByAge() {
        val query = FirebaseDatabase.getInstance().getReference("users")
            .orderByChild("age")
            .endAt(29.0)


        query.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                snapshot.children.forEach {

                    val data = it.getValue<User>()
                    val dob = data?.date_of_birth
                    val name = data?.full_name
                    val age = data?.age
                    val id = data?.id
                    userList.add(User(id!!, dob!!, name!!, age!!))
                }

                adapter = UserAdapter(userList)
                recyclerView.adapter = adapter
                adapter.notifyDataSetChanged()
                users()

            }


            override fun onCancelled(error: DatabaseError) {
                Log.w("testTag", "Failed to read value.", error.toException())
            }


        })
    }
}