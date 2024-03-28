package com.nmp.week4anmp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.nmp.week4anmp.databinding.FragmentCatListBinding
import com.nmp.week4anmp.viewmodel.CatViewModel


class CatListFragment : Fragment() {

    private lateinit var viewModel: CatViewModel
    private val catListAdapter = CatListAdapter(arrayListOf())
    private lateinit var binding: FragmentCatListBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCatListBinding.inflate(inflater,container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(CatViewModel::class.java)
        viewModel.refresh()

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = catListAdapter

        observeViewModel()

        binding.refreshLayout.setOnRefreshListener {
            binding.recyclerView.visibility = View.GONE
            binding.txterror.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
            viewModel.refresh()
            binding.refreshLayout.isRefreshing = false
        }
    }

    fun observeViewModel() {
        viewModel.catsLD.observe(viewLifecycleOwner, Observer {
            catListAdapter.updateCatList(it)
        })


        viewModel.catsLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                binding.txterror?.visibility = View.VISIBLE
            } else {
                binding.txterror?.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                binding.recyclerView.visibility = View.GONE
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.recyclerView.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE
            }
        })



    }


}