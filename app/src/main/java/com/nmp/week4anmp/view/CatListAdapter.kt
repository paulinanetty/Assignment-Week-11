package com.nmp.week4anmp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.nmp.week4anmp.databinding.CatListItemBinding
import com.nmp.week4anmp.model.Cat

class CatListAdapter(val catList:ArrayList<Cat>)
    :RecyclerView.Adapter<CatListAdapter.CatViewHolder>() {

    class CatViewHolder(var binding: CatListItemBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val binding = CatListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return CatViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return catList.size
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        holder.binding.txtID.text = catList[position].id
        holder.binding.txtname.text = catList[position].name
        holder.binding.txtAge.text = catList[position].age
        holder.binding.txtbreed.text = catList[position].breed
        holder.binding.txtcolor.text = catList[position].color





    }

    fun updateCatList(newCatList: ArrayList<Cat>) {
        catList.clear()
        catList.addAll(newCatList)
        notifyDataSetChanged()
    }
}