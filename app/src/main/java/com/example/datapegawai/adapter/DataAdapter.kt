package com.example.datapegawai.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.datapegawai.R
import com.example.datapegawai.database.entity.PegawaiModel
import com.example.datapegawai.utils.Utils
import kotlinx.android.synthetic.main.list_data.view.*
import java.util.*

class DataAdapter(
    private val mContext: Context,
    private val list: List<PegawaiModel>,
    private var onClickItem: DataAdapter.onItemClick,
    private var onDeleteItem: DataAdapter.onItemDelete
) : RecyclerView.Adapter<DataAdapter.Holder>(), Filterable{

    private var listFilter: List<PegawaiModel> ? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_data,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return if (listFilter == null) {
            if (list == null) 0 else list.size
        } else listFilter!!.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        val itemListModel : PegawaiModel

        if (listFilter==null){
            itemListModel = list[position]
        } else {
            itemListModel = listFilter!![position]
        }

        holder.view.txt_nama.text = itemListModel.nama
        holder.view.txt_jabatan.text = itemListModel.jabatan
        holder.view.txt_ttl.text = "${itemListModel.tempatLahir}, ${Utils.changeDateFormat(
            itemListModel.tanggalLahir
        )}"
        holder.view.txt_alamat.text = itemListModel.alamat

        holder.view.btn_edit.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                onClickItem.itemClick(itemListModel, position)
            }

        })

        holder.view.btn_delete.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                onDeleteItem.itemDelete(itemListModel, position)
            }

        })

    }

    class Holder(val view: View) : RecyclerView.ViewHolder(view)

    interface onItemClick{
        fun itemClick(item: PegawaiModel, pos: Int);
    }

    interface onItemDelete{
        fun itemDelete(item: PegawaiModel, pos: Int);
    }

    override fun getFilter(): Filter? {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence): FilterResults {
                val charString = constraint.toString()
                val filterResults = FilterResults()

                if (charString.isEmpty()) {
                    listFilter = null
                } else {
                    val filteredList: MutableList<PegawaiModel> = ArrayList<PegawaiModel>()
                    for (row in list) {
                        if (row.alamat?.toLowerCase().toString()
                                .contains(charString.toLowerCase()) ||
                            row.nama.toLowerCase().toString().contains(charString.toLowerCase()) ||
                            row.tempatLahir.toLowerCase().toString().contains(charString.toLowerCase())||
                            row.tanggalLahir.toLowerCase().toString().contains(charString.toLowerCase()) ||
                            row.jabatan.toLowerCase().toString().contains(charString.toLowerCase())
                        ) {
                            filteredList.add(row)
                        }
                    }
                    listFilter = filteredList
                    filterResults.values = listFilter
                }

                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                if(listFilter!=null){
                    listFilter = filterResults.values as ArrayList<PegawaiModel>
                }

                notifyDataSetChanged()
            }
        }
    }
}
