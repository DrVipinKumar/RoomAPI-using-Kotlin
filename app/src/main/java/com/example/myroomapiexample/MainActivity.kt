package com.example.myroomapiexample

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       btnDelete.setOnClickListener(View.OnClickListener {
            val userid = txtUID.text.toString().toInt()
            val user = User(userid,"","")

            try {
                MyRoomDatabase.getInstance(this).userDao().deleteRecord(user)
                displayMessage("Information", "Record Deleted")
            } catch (ex: SQLiteConstraintException) {
                displayMessage("Error", ex.message)
            }
        })
        btnUpdate.setOnClickListener(View.OnClickListener {

            val userid = txtUID.text.toString().toInt()
            val name=txtName.text.toString()
            val pwd= txtPwd.text.toString()
            val user = User(userid,name,pwd)
            try {
                MyRoomDatabase.getInstance(this).userDao().updateRecord(user)
                displayMessage("Information", "Record Updated")
            } catch (ex: SQLiteConstraintException) {
                displayMessage("Error", ex.message)
            }
        })
        btnDisplay.setOnClickListener(View.OnClickListener {
            val usersinfo = MyRoomDatabase.getInstance(this).userDao().userInfo()
            val data = StringBuffer()
            for (user in usersinfo!!) {
                data.append("User Id=${user?.uid}".trimIndent())
                data.append("User name=${user?.name}".trimIndent())
                data.append("User password=${user?.pwd}".trimIndent())
            }
            displayMessage("User Information", data.toString())
        })
        btnDelete.setOnClickListener(View.OnClickListener {
            val userid = txtUID.getText().toString().toInt()
            val name=txtName.getText().toString()
            val pwd=txtPwd.getText().toString()
            val user = User(userid,name,pwd)
            try {
                MyRoomDatabase.getInstance(this).userDao().insertRecord(user)
                Toast.makeText(applicationContext, "Record Inserted", Toast.LENGTH_SHORT).show()
            } catch (ex: SQLiteConstraintException) {
                Toast.makeText(applicationContext, ex.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun displayMessage(title: String?, data: String?) {
        val builder = AlertDialog.Builder(this@MainActivity)
        builder.setTitle(title)
        builder.setMessage(data)
        builder.setCancelable(true)
        builder.show()
    }
}