package me.ctossato.businesscard.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import me.ctossato.businesscard.data.BusinessCard
import me.ctossato.businesscard.databinding.CardItemBinding
import java.lang.IllegalArgumentException

class BusinessCardAdapter
            : ListAdapter<BusinessCard, BusinessCardAdapter.ViewHolder>(DiffCallBack()) {

    var listenerShare: (View) -> Unit = {}
    var listenerDelete: (BusinessCard) -> Unit = {}

    inner class ViewHolder(private val binding: CardItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BusinessCard) {
            binding.tvNameContent.text = item.name
            binding.tvPhoneContent.text = item.phone
            binding.tvEMailContent.text = item.email
            binding.tvCompanyNameContent.text = item.company
            try {
                binding.cvCard.setCardBackgroundColor(Color.parseColor(item.backgroud_color.uppercase()))
            } catch (e: Exception){
                binding.cvCard.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
                Toast.makeText(binding.root.context, binding.root.context.getString(me.ctossato.businesscard.R.string.msg_error_illegal_color), Toast.LENGTH_SHORT).show()
            }

            binding.cvCard.setOnClickListener {
                listenerShare(it)  // passa o cardView para listenerShare
            }
            binding.cvCard.setOnLongClickListener {
                listenerDelete(item)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemCardBinding = CardItemBinding.inflate(layoutInflater, parent, false)

        return ViewHolder(itemCardBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class DiffCallBack: DiffUtil.ItemCallback<BusinessCard>() {
    override fun areItemsTheSame(oldItem: BusinessCard, newItem: BusinessCard): Boolean {
       return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: BusinessCard, newItem: BusinessCard): Boolean {
        return oldItem.id == newItem.id
    }

}
