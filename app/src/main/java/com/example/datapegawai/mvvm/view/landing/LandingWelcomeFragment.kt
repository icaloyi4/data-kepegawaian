package com.example.datapegawai.mvvm.view.landing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.datapegawai.R
import kotlinx.android.synthetic.main.fragment_welcome.view.*

class LandingWelcomeFragment() : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_welcome, container, false)

        v.btn_mulai.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                val transaction = activity?.supportFragmentManager?.beginTransaction()
                transaction?.setCustomAnimations(R.animator.slide_in_left,
                    R.animator.slide_out_right ,0, 0)
                transaction?.replace(R.id.frameLanding, LandingFormPerusahaanFragment())
                transaction?.commit()
            }

        })
        return v
    }
}