package com.amolpatil.kotlindemo.RecyclerviewDemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amolpatil.kotlindemo.R
import com.amolpatil.kotlindemo.Rest.ApiController
import com.google.gson.JsonArray
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    val users = ArrayList<UserPojo>()

    var adapter : CustomAdapter?=null

    var imgAdduser: ImageView? =null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        imgAdduser= findViewById(R.id.add__user_img) as ImageView

        imgAdduser!!.setOnClickListener {

            adduser()


        }

        getallusers()
    }

    private fun adduser() {
        val mDialogView = LayoutInflater.from(this).inflate(R.layout.lay_dialog, null)

        val mBuilder = AlertDialog.Builder(this)
            .setView(mDialogView)
            .setTitle("Add User here....")

        val ed_imgurl = mDialogView.findViewById(R.id.ed_imgurl) as EditText
        val ed_name = mDialogView.findViewById(R.id.ed_name) as EditText
        val btn_add = mDialogView.findViewById(R.id.btn_add) as Button

        var  mAlertDialog = mBuilder.show()

        btn_add.setOnClickListener {


            users.add(UserPojo(
                ed_name.text.toString(),
                ed_name.text.toString(),

                ed_imgurl.text.toString()
            ))

            adapter?.notifyDataSetChanged()

            mAlertDialog.dismiss()

            Toast.makeText(this@MainActivity,"User Added successfully Check at the bottom",Toast.LENGTH_LONG).show()
        }



    }

    private fun getallusers() {
        val recyclerView = findViewById(R.id.recyclerview) as RecyclerView

        //adding a layoutmanager
        recyclerView.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)





        ApiController.instance.getUser().enqueue(object :Callback<JsonArray>{
            override fun onFailure(call: Call<JsonArray>, t: Throwable) {



                Toast.makeText(this@MainActivity,t.message,Toast.LENGTH_LONG).show()

                 }

            override fun onResponse(call: Call<JsonArray>, response: Response<JsonArray>) {

                if(response.isSuccessful){


                    val jsonArray=JSONArray(response.body().toString())

                    var size=jsonArray.length()


                    users.clear()

                    for (i in 0.. size-1) {
                        var json_objectdetail: JSONObject =jsonArray.getJSONObject(i)


                        users.add(UserPojo(
                            json_objectdetail.optString("login"),
                            json_objectdetail.optString("type"),

                            json_objectdetail.optString("avatar_url")
                        ))

                         adapter = CustomAdapter(users)

                        //now adding the adapter to recyclerview
                        recyclerView.adapter = adapter

                    }



                       // Toast.makeText(this@MainActivity,response.body().toString(),Toast.LENGTH_LONG).show()
                }



             }

        })









    }
}
