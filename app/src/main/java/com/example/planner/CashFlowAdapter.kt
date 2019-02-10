package com.example.planner

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.recyclerview_row.view.*

class CashFlowAdapter(private val cashFlowData: List<CashFlow>) : RecyclerView.Adapter<CashFlowAdapter.MyViewHolder>() {

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.cashflowRecyclerTextRow
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CashFlowAdapter.MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_row, parent, false))
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textView.text = cashFlowData[position].amount.toString() + " - " + cashFlowData[position].frequency
    }

    override fun getItemCount() = cashFlowData.size
}