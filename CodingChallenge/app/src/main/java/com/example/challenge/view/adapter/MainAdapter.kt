package com.example.challenge.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.challenge.R
import com.example.challenge.databinding.ItemViewRowBinding
import com.example.challenge.databinding.LayoutDescViewBinding
import com.example.challenge.databinding.LayoutTextViewBinding
import com.example.challenge.model.Card

class MainAdapter(
    private val cardList: ArrayList<Card>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_TEXT = 1
        const val VIEW_TYPE_DESC = 2
        const val VIEW_TYPE_IMAGE = 3
    }

    class DataViewHolder(private var textViewBinding: LayoutTextViewBinding) :
        RecyclerView.ViewHolder(
            textViewBinding.root
        ) {

        fun bind(card: Card) {
            textViewBinding.textViewTitle.text = card.card?.value ?: ""
        }
    }

    class ImageViewHolder(private var itemViewRowBinding: ItemViewRowBinding) :
        RecyclerView.ViewHolder(
            itemViewRowBinding.root
        ) {
        fun bind(card: Card) {
            itemViewRowBinding.txtTitle.text = card.card?.title?.value ?: ""
            itemViewRowBinding.txtDesc.text = card.card?.description?.value ?: ""
            Glide.with(itemViewRowBinding.imageViewAvatar)
                .load(card.card?.image?.url)
                .into(itemViewRowBinding.imageViewAvatar)
        }
    }

    class DescriptionViewHolder(private var descViewBinding: LayoutDescViewBinding) :
        RecyclerView.ViewHolder(
            descViewBinding.root
        ) {
        fun bind(card: Card) {
            descViewBinding.txtTitle.text = card.card?.title?.value ?: ""
            descViewBinding.txtDesc.text = card.card?.description?.value ?: ""
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == VIEW_TYPE_TEXT) {
            return DataViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.layout_text_view,
                    parent,
                    false
                )
            )
        } else if (viewType == VIEW_TYPE_DESC) {
            return DescriptionViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.layout_desc_view,
                    parent,
                    false
                )
            )
        }
        return ImageViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_view_row,
                parent,
                false
            )
        )
    }


    override fun getItemCount(): Int = cardList.size
    override fun getItemViewType(position: Int): Int {
        val cardType = cardList[position].card_type
        return when {
            cardType.equals("text") -> {
                VIEW_TYPE_TEXT
            }
            cardType.equals("title_description") -> {
                VIEW_TYPE_DESC
            }
            else -> {
                VIEW_TYPE_IMAGE
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == VIEW_TYPE_TEXT) {
            (holder as DataViewHolder).bind(cardList[holder.absoluteAdapterPosition])
        } else if (getItemViewType(position) == VIEW_TYPE_DESC) {
            (holder as DescriptionViewHolder).bind(cardList[holder.absoluteAdapterPosition])
        } else {
            (holder as ImageViewHolder).bind(cardList[holder.absoluteAdapterPosition])
        }
    }

    fun addData(list: List<Card>) {
        cardList.clear();
        cardList.addAll(list)
    }
}