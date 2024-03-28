package com.example.retrofitcoroutinewithnetwrokhandle.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofitcoroutinewithnetwrokhandle.databinding.ItemCharacterBinding
import com.example.retrofitcoroutinewithnetwrokhandle.model.Character

class CharacterAdapter(private val context: Context,private val mList: ArrayList<Character>)
    :RecyclerView.Adapter<CharacterAdapter.ItemViewHolder>(){

    inner class ItemViewHolder(val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = mList[position]
        holder.binding.itemName.text = item.name
        Glide.with(context).load(item.image).into(holder.binding.itemImg)
    }

}