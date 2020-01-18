package com.amolpatil.kotlindemo.RecyclerviewDemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.amolpatil.kotlindemo.R
import com.github.chrisbanes.photoview.PhotoView
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val back_img = findViewById(R.id.back_img) as ImageView
        val username = findViewById(R.id.username) as TextView
        val user_image = findViewById(R.id.user_image) as PhotoView


        var uname= intent.getStringExtra("username")
        var uimage= intent.getStringExtra("image")

        username.text=uname
        Picasso.get().load(uimage).into(user_image)

        back_img.setOnClickListener {

            finish()
        }




    }
}
