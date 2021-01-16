package com.example.datapegawai.mvvm.view

import android.app.SearchManager
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.datapegawai.R
import com.example.datapegawai.adapter.DataAdapter
import com.example.datapegawai.database.entity.JabatanEntity
import com.example.datapegawai.database.entity.PegawaiEntity
import com.example.datapegawai.database.entity.PegawaiModel
import com.example.datapegawai.mvvm.view.landing.LandingActivity
import com.example.datapegawai.mvvm.viewmodel.MainViewModel
import com.example.datapegawai.utils.App
import com.example.datapegawai.utils.InterfaceUmum
import com.example.datapegawai.utils.Utils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_tambah_pegawai.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), DataAdapter.onItemClick {

    private val vm by viewModel<MainViewModel>()
    var listPegawai : ArrayList<PegawaiModel> = arrayListOf()
    var listJabatan : ArrayList<JabatanEntity> = arrayListOf()
    lateinit var adapter : DataAdapter

    lateinit var ctx : Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ctx = this

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.title = ""

//        ((MainActivity)getActivity()).getSupportActionBar().setTitle("Inbound");
        //        ((MainActivity)getActivity()).getSupportActionBar().setTitle("Inbound");
//        toolbar.option

        txt_nama_perusahaan.text = App.preff.getData(App.keyNamaPerusahaan)

        //init Recycle
        val numberOfColumns = 2
        val layoutManager = GridLayoutManager(ctx, numberOfColumns)
        lv_data.layoutManager = layoutManager

        adapter = DataAdapter(ctx, listPegawai, this)
        lv_data.adapter = adapter

        vm.perusahaanData.observe(this, Observer {
            listPegawai.clear()
            listPegawai.addAll(it)
            adapter.notifyDataSetChanged()
        })

        vm.jabatanData.observe(this, Observer {
            listJabatan.clear()
            listJabatan.addAll(it)
        })

        btn_tambah.setOnClickListener {
            dialogTambahAlamat()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_info, menu)

        var searchManager : SearchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager

        var searchView  = menu!!.findItem(R.id.action_search).actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()))
        searchView.maxWidth = Int.MAX_VALUE

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                /*if (mAdapter != null) mAdapter.getFilter().filter(query)*/
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                /*if (mAdapter != null) mAdapter.getFilter().filter(newText)*/
                return false
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.action_search) {
            return true
        } else if (id == R.id.action_home) {
            val menuItemView: View = findViewById<View>(R.id.action_home)
                showPopUpMenu(menuItemView, ctx)
        }
        return super.onOptionsItemSelected(item)
    }

    fun showPopUpMenu(view: View?, context: Context) {
        val popup = PopupMenu(
            context,
            view!!
        )
        val inflater = popup.menuInflater
        inflater.inflate(R.menu.menu_warehouse, popup.menu)

        popup.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.edt_perusahaan -> {
                    /*(context as Activity).startActivity(
                        Intent(
                            context,
                            SearchWarehouseActivity::class.java
                        )
                    )*/
                    return@OnMenuItemClickListener true
                }
                R.id.gnti_perusahaan -> {
                    startActivity(
                        Intent(
                            ctx,
                            LandingActivity::class.java
                        )
                    )
                    finish()
                    /*val url = "http://wms.sewagudang.id/utility/Mobile/dokumentasi"
                    val i = Intent(Intent.ACTION_VIEW)
                    i.data = Uri.parse(url)
                    (context as Activity).startActivity(i)*/
                    return@OnMenuItemClickListener true
                }
            }
            popup.dismiss()
            false
        })
        popup.show()
    }

    fun dialogTambahAlamat(item: PegawaiModel? = null, isupdate: Boolean = false) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(ctx)
        val inflater: LayoutInflater =
            (ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
        val view: View = inflater.inflate(R.layout.dialog_tambah_pegawai, null)

        builder.setTitle("Tambah Data Pegawai")
        builder.setCancelable(false)

        lateinit var adapter : ArrayAdapter<JabatanEntity>

        adapter = ArrayAdapter(ctx, android.R.layout.simple_list_item_1, listJabatan)

        view.spin_jabatan.adapter = adapter

        builder.setView(view)

        view.txt_ttl2.setOnClickListener {

            val getDatePicker: InterfaceUmum.getDatePicker = object : InterfaceUmum.getDatePicker {
                override fun getTanggal(tanggal: String?) {
                    view.txt_ttl2.setText(tanggal.toString())
                }
            }

            Utils.datePickerUtils(ctx, getDatePicker)
        }

        builder.setPositiveButton("OK", object : DialogInterface.OnClickListener {
            override fun onClick(p0: DialogInterface?, p1: Int) {
                if (p0 != null) {
                    listJabatan[view.spin_jabatan.selectedItemPosition].id?.let {
                        vm.insertPegawai(
                            PegawaiEntity(
                                view.txt_nama.text.toString(),
                                view.txt_ttl.text.toString(),
                                view.txt_ttl2.text.toString(),
                                view.txt_alamat.text.toString(),
                                vm.idPerusahaan,
                                it,
                                (if(item==null) null else item.id)
                            )
                        )
                        p0.dismiss()
                    }

                }
            }
        })

        builder.setNegativeButton("Cancel", object : DialogInterface.OnClickListener {
            override fun onClick(p0: DialogInterface?, p1: Int) {
                if (p0 != null) {

                    p0.dismiss()
                }
            }
        })

        if (isupdate){
            builder.setTitle("Update Data Pegawai")

        }

        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()
    }

    override fun itemClick(item: PegawaiModel, pos: Int) {

    }
}
