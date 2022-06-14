package com.example.myapp123

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.example.myapp123.model.UserData
import com.example.myapp123.view.UserAdapter
import kotlinx.android.synthetic.main.custom_dialog.*

class MainActivity : AppCompatActivity() {

    private lateinit var addsBtn:FloatingActionButton
    private lateinit var recv:RecyclerView
    private lateinit var userList:ArrayList<UserData>
    private lateinit var userAdapter:UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userList = ArrayList()

        addsBtn = findViewById(R.id.addingBtn)
        recv = findViewById(R.id.mRecycler)

        userAdapter = UserAdapter(this,userList)

        recv.layoutManager = LinearLayoutManager(this)
        recv.adapter = userAdapter

        addsBtn.setOnClickListener { addInfo() }
        showStartAdPopup()
    }

    private fun showStartAdPopup() {
        var dialog1 =  Dialog (this,android.R.style.Theme_Black_NoTitleBar_Fullscreen)
        dialog1.setContentView(R.layout.custom_dialog)
        dialog1.closeDialog.setOnClickListener(){
            dialog1.dismiss()
        }
        dialog1.show()
    }

    private fun addInfo() {
        val inflter = LayoutInflater.from(this)
        val v = inflter.inflate(R.layout.add_item,null)

        val userName = v.findViewById<EditText>(R.id.userName)

        val addDialog = AlertDialog.Builder(this)

        addDialog.setView(v)
        addDialog.setPositiveButton("Ok")

        {   dialog, _->
            val names = userName.text.toString()
            userList.add(UserData("$names"))
            userAdapter.notifyDataSetChanged()
            Toast.makeText(this,"Text added",Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        addDialog.setNegativeButton("Cancel"){
                dialog,_->
            dialog.dismiss()
            Toast.makeText(this,"Cancel",Toast.LENGTH_SHORT).show()
        }

        addDialog.create()
        addDialog.show()

    }
}

