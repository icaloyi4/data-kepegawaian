package com.example.datapegawai.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.datapegawai.R
import com.example.datapegawai.database.entity.JabatanEntity
import kotlinx.android.synthetic.main.list_jabatan.view.*
import java.lang.Exception

class JabatanAdapter (private val mContext: Context, private val list: List<JabatanEntity>, private var onClickItem: JabatanAdapter.onItemClick) : RecyclerView.Adapter<JabatanAdapter.Holder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_jabatan,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list?.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        val itemListModel : JabatanEntity = list!![position]

        holder.view.txt_jabatan.text = itemListModel.namaJabatan

        holder.view.crd.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                onClickItem.itemClick(itemListModel, position)
            }

        })

        /*Picasso.get().load("https://image.tmdb.org/t/p/w500/${itemListModel.posterPath}")
            .into(holder.view.img_movie, object : Callback{
                override fun onSuccess() {
                    holder.view.img_movie.scaleType = ImageView.ScaleType.CENTER_CROP;
                }

                override fun onError(e: Exception?) {
                }

            })

        holder.view.txt_title.text = itemListModel.title
        holder.view.txt_release.text = Utils.changeDateFormat(itemListModel.releaseDate.toString())

        holder.view.cardView.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                onClickItem.itemClick(itemListModel)
            }

        })*/


    }

    class Holder(val view: View) : RecyclerView.ViewHolder(view)

    interface onItemClick{
        fun itemClick(item : JabatanEntity, pos : Int);
    }
}
