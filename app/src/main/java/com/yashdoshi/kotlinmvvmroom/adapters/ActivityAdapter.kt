package com.yashdoshi.kotlinmvvmroom.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.yashdoshi.kotlinmvvmroom.R
import com.yashdoshi.kotlinmvvmroom.db.entity.BoredActivity
import kotlinx.android.synthetic.main.bored_view_holder.view.*

class ActivityAdapter(private val interaction: Interaction? = null, private var activityList : List<BoredActivity> = listOf()) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.bored_view_holder,
            parent,
            false
        )
        view.layoutParams = ViewGroup.LayoutParams(
            (parent.width * 0.85).toInt(),
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        return BoredActivityViewHolder(
            view,
            interaction
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is BoredActivityViewHolder -> {
                holder.bind(activityList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return activityList.size
    }

    fun submitList(list: List<BoredActivity>) {
        activityList = list
        notifyDataSetChanged()
    }

    class BoredActivityViewHolder
    constructor(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(boredActivity: BoredActivity) = with(itemView) {
            itemView.setOnClickListener {
                interaction?.onItemSelected(adapterPosition, boredActivity)
            }
            itemView.tvActivity.text = boredActivity.activity
            tvType.text = boredActivity.type.capitalize()
            tvPrice.text = "$${boredActivity.price}"
            tvParticipants.text = "${boredActivity.participants} Participants"
            if (boredActivity.link.isBlank()) {
                tvLink.visibility = View.GONE
            } else {
                tvLink.visibility = View.VISIBLE
                tvLink.setOnClickListener {
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(boredActivity.link))
                    context.startActivity(browserIntent)
                }
            }
        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: BoredActivity)
    }
}
