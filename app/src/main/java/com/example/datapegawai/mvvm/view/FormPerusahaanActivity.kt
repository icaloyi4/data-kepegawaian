package com.example.datapegawai.mvvm.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.datapegawai.R
import com.example.datapegawai.adapter.JabatanAdapter
import com.example.datapegawai.database.LoadingState
import com.example.datapegawai.database.entity.JabatanEntity
import com.example.datapegawai.database.entity.PerusahaanEntity
import com.example.datapegawai.mvvm.viewmodel.FormPerusahaanViewModel
import com.example.datapegawai.utils.App
import com.example.datapegawai.utils.InterfaceUmum
import com.example.datapegawai.utils.Utils
import kotlinx.android.synthetic.main.fragment_f_perusahaan.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class FormPerusahaanActivity : AppCompatActivity(),JabatanAdapter.onItemClick {

    private val vm by viewModel<FormPerusahaanViewModel>()

    var adapter : JabatanAdapter? = null
    var listJabatan : ArrayList<JabatanEntity> = arrayListOf()
    var perusahaan : PerusahaanEntity? = null
    lateinit var ctx : Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_f_perusahaan)

        ctx = this


        //init Recycle
        val numberOfColumns = 4
        val layoutManager = GridLayoutManager(ctx, numberOfColumns)
        lv_jabatan.layoutManager = layoutManager

        adapter = JabatanAdapter(ctx, listJabatan, this)
        lv_jabatan.adapter = adapter

        vm.perusahaanData.observe(this, Observer {
            perusahaan=it[0]
            edt_perusahaan.setText(perusahaan?.namaPerusahaan)
            edt_perusahaan.setSelection(perusahaan?.namaPerusahaan.toString().length)
        })

        vm.jabatanData.observe(this, Observer {
            listJabatan.clear()
            listJabatan.addAll(it)
            if (listJabatan.size>0)txt_ket_jabata.visibility = View.GONE else txt_ket_jabata.visibility = View.VISIBLE
            adapter?.notifyDataSetChanged()
        })

        vm.loadingState.observe(this, Observer {
            when(it.status){
                LoadingState.Status.FAILED-> run{
                    Utils.errorToast(ctx, it.msg.toString())
                }
            }
        })

        btn_tambah.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                Utils.hideSoftKeyboard(this@FormPerusahaanActivity)
                listJabatan.add(JabatanEntity(edt_jabatan.text.toString()))
                edt_jabatan.setText("")
                if (listJabatan.size==0){
                    txt_ket_jabata.visibility = View.VISIBLE
                } else {
                    txt_ket_jabata.visibility = View.GONE
                }
                adapter?.notifyDataSetChanged()
            }

        })

        btn_simpan.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                if (listJabatan.size>0&&edt_perusahaan.text.isNotEmpty()){
                    perusahaan?.namaPerusahaan = edt_perusahaan.text.toString()
                    val id = perusahaan?.let { vm.insertPerusahaan(it) }
                    id?.observe(this@FormPerusahaanActivity, {
                        App.preff.setDataString(App.keyIdPerusahaan, perusahaan?.id.toString())
                        App.preff.setDataString(App.keyNamaPerusahaan, perusahaan?.namaPerusahaan)
                        for (i in 0 until listJabatan.size){
                            listJabatan[i].idPerusahaan = it.toInt()
                        }
                        vm.insertJabatan(ctx, listJabatan)
                    })

                } else {

                    var msg = ""
                    if (edt_perusahaan.text.isEmpty()){
                        msg += "Nama Perusahaan Tidak Boleh Kosong \n"
                    }
                    if (listJabatan.size==0){
                        msg += "Jabatan Tidak Boleh Kosong"
                    }

                    Utils.errorToast(ctx, msg)

                }
            }

        })

    }

    override fun itemClick(item: JabatanEntity, pos: Int) {
        var inter = object : InterfaceUmum.konfirmasi{
            override fun yes() {
                item.id?.let { vm.deleteJabatan(it) }
            }
        }

        Utils.dialogkonfirmasi(ctx, inter, "Apakah anda yakin akan menghapus jabatan ${item.namaJabatan} ?")
    }
}
