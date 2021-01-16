package com.example.datapegawai.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.datapegawai.R
import com.example.datapegawai.database.entity.JabatanEntity
import com.example.datapegawai.database.entity.PegawaiModel
import com.example.datapegawai.utils.Utils
import kotlinx.android.synthetic.main.list_data.view.*
import java.lang.Exception

class DataAdapter (private val mContext: Context, private val list: List<PegawaiModel>, private var onClickItem: DataAdapter.onItemClick) : RecyclerView.Adapter<DataAdapter.Holder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_data,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list?.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        val itemListModel : PegawaiModel = list!![position]

        holder.view.txt_nama.text = itemListModel.nama
        holder.view.txt_jabatan.text = itemListModel.jabatan
        holder.view.txt_ttl.text = "${itemListModel.tempatLahir}, ${Utils.changeDateFormat(itemListModel.tanggalLahir)}"
        holder.view.txt_alamat.text = itemListModel.alamat

        holder.view.crd.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                onClickItem.itemClick(itemListModel, position)
            }

        })

    }

    class Holder(val view: View) : RecyclerView.ViewHolder(view)

    interface onItemClick{
        fun itemClick(item : PegawaiModel, pos : Int);
    }
}
