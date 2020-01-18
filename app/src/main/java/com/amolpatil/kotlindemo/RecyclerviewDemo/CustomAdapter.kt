package com.amolpatil.kotlindemo.RecyclerviewDemo

import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amolpatil.kotlindemo.R

import com.squareup.picasso.Picasso


class CustomAdapter(val userList: ArrayList<UserPojo>):RecyclerView.Adapter<CustomAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_layoutnew, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {

        return userList.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(userList[position])

        holder.delete_card.setOnClickListener(){



            val builder = AlertDialog.Builder(holder.itemView.context)

            builder.setTitle("Alert!!!")

            builder.setMessage("Do you want to delete the "+userList[position].name)
            builder.setIcon(android.R.drawable.ic_dialog_alert)



            builder.setPositiveButton("Yes"){dialogInterface, which ->
                userList.removeAt(position)
                notifyDataSetChanged()
            }



            builder.setNegativeButton("No"){dialogInterface, which ->

            }

            val alertDialog: AlertDialog = builder.create()

            alertDialog.setCancelable(false)
            alertDialog.show()


        }
        holder.itemView.setOnClickListener(){
//            userList.add(UserPojo("Amol","aaa","https://media-exp1.licdn.com/dms/image/C5103AQEjbaGvJ1vauw/profile-displayphoto-shrink_200_200/0?e=1584576000&v=beta&t=8nniy_xnoHBh7UvL0M8MNGSvOL__UP4cErnJRuWvXXU"))
//            notifyDataSetChanged()

           val intent = Intent(it.context, DetailActivity::class.java)

            intent.putExtra("username",userList[position].name)
            intent.putExtra("image",userList[position].img)
            it.context.startActivity(intent)


        }
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val delete_card = itemView.findViewById(R.id.delete_card) as LinearLayout
        fun bindItems(user: UserPojo) {

            val textViewName = itemView.findViewById(R.id.textViewUsername) as TextView
            val textViewType = itemView.findViewById(R.id.textViewAddress) as TextView
            val imageView = itemView.findViewById(R.id.imageView) as ImageView
            textViewName.text = user.name
            textViewType.text = user.address
            //  imageView.setImageResource(user.img)



            Picasso.get().load(user.img).into(imageView)


        }
    }
}


