package com.example.projectubereats.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projectubereats.R
import com.example.projectubereats.models.Commerce
import com.example.projectubereats.models.Order
import com.squareup.picasso.Picasso
import mx.edu.itm.link.dadm_u3proyb.adapters.CommerceAdapter

class OrdersAdapter(val context: Context, val res: Int, val list: ArrayList<Order>) :
    RecyclerView.Adapter<OrdersAdapter.OrdersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersViewHolder {
        return OrdersViewHolder(
            LayoutInflater.from(context).inflate(res, null)
        )
    }

    override fun onBindViewHolder(holder: OrdersViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class OrdersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(order: Order){
            val imgPhoto = itemView.findViewById<ImageView>(R.id.imgPhotoOrder)
            val textName = itemView.findViewById<TextView>(R.id.textNameOrder)
            val textAddress = itemView.findViewById<TextView>(R.id.textAddressOrder)
            val textTotal = itemView.findViewById<TextView>(R.id.textTotalOrder)

            order.photo?.let {
                var urlPhoto = itemView.resources.getString(R.string.api)
                urlPhoto += "assets/images/$it"

                Picasso.get().load(urlPhoto).into(imgPhoto);
            }

            textName.text = order.name
            textAddress.text = "Dirección: "+order.address
            textTotal.text = "$ ${order.total}"

        }
    }


}