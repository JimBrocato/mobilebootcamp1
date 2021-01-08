package com.wcg.mobilebootcamp1_jim

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wcg.mobilebootcamp1_jim.viewmodels.DemoVmViewModel

class DemoVmFragment : Fragment() {

    companion object {
        fun newInstance() = DemoVmFragment()
    }

    private lateinit var viewModel: DemoVmViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.demo_vm_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DemoVmViewModel::class.java)
        // TODO: Use the ViewModel
    }

}