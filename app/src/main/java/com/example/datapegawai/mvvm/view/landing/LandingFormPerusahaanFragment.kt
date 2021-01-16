package com.example.datapegawai.mvvm.view.landing

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.datapegawai.R
import com.example.datapegawai.adapter.JabatanAdapter
import com.example.datapegawai.database.LoadingState
import com.example.datapegawai.database.LoadingState.Companion.LOADED
import com.example.datapegawai.database.entity.JabatanEntity
import com.example.datapegawai.database.entity.PerusahaanEntity
import com.example.datapegawai.mvvm.viewmodel.LandingFormPerusahaanViewModel
import com.example.datapegawai.utils.Utils
import kotlinx.android.synthetic.main.fragment_f_perusahaan.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LandingFormPerusahaanFragment() : Fragment(), JabatanAdapter.onItemClick {

    private val vm by viewModel<LandingFormPerusahaanViewModel>()

    var adapter : JabatanAdapter? = null
    var listJabatan : ArrayList<JabatanEntity> = arrayListOf()
    lateinit var ctx : Context

    lateinit var txt_ket_jabatan : TextView
    lateinit var edt_perusahaan : TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_f_perusahaan, container, false)

        ctx = requireActivity()

        txt_ket_jabatan = v.txt_ket_jabata
        edt_perusahaan = v.edt_perusahaan

        //init Recycle
        val numberOfColumns = 4
        val layoutManager = GridLayoutManager(ctx, numberOfColumns)
        v.lv_jabatan.layoutManager = layoutManager

        adapter = JabatanAdapter(ctx, listJabatan, this)
        v.lv_jabatan.adapter = adapter

        v.btn_tambah.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                Utils.hideSoftKeyboard(activity!!)
                listJabatan.add(JabatanEntity(v.edt_jabatan.text.toString()))
                v.edt_jabatan.setText("")
                if (listJabatan.size==0){
                    txt_ket_jabatan.visibility = View.VISIBLE
                } else {
                    txt_ket_jabatan.visibility = View.GONE
                }
                adapter?.notifyDataSetChanged()
            }

        })

        v.btn_simpan.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                if (listJabatan.size>0&&edt_perusahaan.text.isNotEmpty()){
                    val id = vm.insertPerusahaan(PerusahaanEntity(edt_perusahaan.text.toString()))
                    id.observe(viewLifecycleOwner, {
                        for (i in 0 until listJabatan.size){
                            listJabatan[i].idPerusahaan = it.toInt()
                        }
                        vm.insertJabatan(listJabatan)
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

        vm.loadingState.observe(viewLifecycleOwner, {
            when(it.status){
                LoadingState.Status.SUCCESS -> {
                    val transaction = activity?.supportFragmentManager?.beginTransaction()
                    transaction?.setCustomAnimations(R.animator.slide_in_left,
                        R.animator.slide_out_right ,0, 0)
                    transaction?.replace(R.id.frameLanding, LandingPerusahaanFragment())
                    transaction?.commit()
                }
            }
        })

        return v
    }

    override fun itemClick(item: JabatanEntity, pos: Int) {
        listJabatan.removeAt(pos)
        if (listJabatan.size==0){
            txt_ket_jabatan.visibility = View.VISIBLE
        } else {
            txt_ket_jabatan.visibility = View.GONE
        }
        adapter?.notifyDataSetChanged()
    }
}