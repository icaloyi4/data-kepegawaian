package com.example.datapegawai.mvvm.view.landing

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.datapegawai.R
import com.example.datapegawai.database.entity.PerusahaanEntity
import com.example.datapegawai.mvvm.view.MainActivity
import com.example.datapegawai.mvvm.viewmodel.LandingPerusahaanViewModel
import kotlinx.android.synthetic.main.fragment_perusahaan.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LandingPerusahaanFragment() : Fragment() {

    private val vm by viewModel<LandingPerusahaanViewModel>()
    var listPerusahaan : ArrayList<PerusahaanEntity> = arrayListOf()
    lateinit var adapter : ArrayAdapter<PerusahaanEntity>

    lateinit var ctx : Context

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_perusahaan, container, false)

        ctx = requireActivity()

        adapter = ArrayAdapter(ctx, android.R.layout.simple_list_item_1, listPerusahaan)
        v.spin_perusahaan.adapter = adapter

        vm.perusahaanData.observe(viewLifecycleOwner, Observer {
            listPerusahaan.clear()
            listPerusahaan.addAll(it)
            adapter.notifyDataSetChanged()
        })

        v.txt_tambah.setOnClickListener {
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.setCustomAnimations(
                R.animator.slide_in_left,
                R.animator.slide_out_right, 0, 0
            )
            transaction?.replace(R.id.frameLanding, LandingFormPerusahaanFragment())
            transaction?.commit()
        }

        v.btn_pilih.setOnClickListener {
            val intent = Intent(ctx, MainActivity::class.java)
            activity?.startActivity(intent)
            activity?.finish()
        }

        return v
    }
}