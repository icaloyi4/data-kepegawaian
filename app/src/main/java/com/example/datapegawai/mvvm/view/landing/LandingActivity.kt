package com.example.datapegawai.mvvm.view.landing

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.datapegawai.R
import com.example.datapegawai.mvvm.viewmodel.LandingViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LandingActivity : AppCompatActivity() {
    private val mv by viewModel<LandingViewModel>()

    private val mList: MutableList<Fragment> = ArrayList()
    private var selectedFragmentPosition = 0
    lateinit var ctx : Context


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)



        mv.getDataPerusahaan()

        mv.perusahaanData.observe(this, {
            mList.add(LandingWelcomeFragment())
            mList.add(LandingPerusahaanFragment())
            mList.add(LandingFormPerusahaanFragment())
            selectedFragmentPosition = if (it==0){
                0
            } else {
                1
            }
            commitFragment(selectedFragmentPosition)
        })
    }

    fun commitFragment(select: Int){
        var selectedFragment = mList[select]
        val transaction: FragmentTransaction =
            supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(R.animator.slide_in_left,
            R.animator.slide_out_right ,0, 0)
        transaction.replace(R.id.frameLanding, selectedFragment)
        transaction.commit()
    }
}
