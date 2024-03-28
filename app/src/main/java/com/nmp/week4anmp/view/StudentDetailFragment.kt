package com.nmp.week4anmp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.nmp.week4anmp.R
import com.nmp.week4anmp.databinding.FragmentStudentDetailBinding
import com.nmp.week4anmp.databinding.FragmentStudentListBinding
import com.nmp.week4anmp.viewmodel.DetailViewModel


class StudentDetailFragment : Fragment() {
    private lateinit var binding: FragmentStudentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStudentDetailBinding.inflate(inflater,container, false)
        return binding.root
    }





}