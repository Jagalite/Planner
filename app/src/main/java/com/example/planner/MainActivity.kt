package com.example.planner

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {

    private val cashFlows: ArrayList<CashFlow> = ArrayList()

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: CashFlowAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //======================================================================================================

        ArrayAdapter.createFromResource(
            this,
            R.array.frequencies,
            android.R.layout.simple_spinner_item
        ).also { arrayAdapter ->
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            findViewById<Spinner>(R.id.inputFrequency).adapter = arrayAdapter
            findViewById<Spinner>(R.id.switchFrequency).adapter = arrayAdapter
        }

        findViewById<Spinner>(R.id.switchFrequency).onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                    setFrequencyConverter(Frequency.valueOf((parent.adapter.getItem(pos).toString().toUpperCase())))
                    updateCashFlowTable()
                }

                override fun onNothingSelected(parent: AdapterView<out Adapter>?) {

                }

            }

        //======================================================================================================

        viewManager = LinearLayoutManager(this)
        viewAdapter = CashFlowAdapter(cashFlows, CashFlowConversion(Frequency.BASE))
        recyclerView = findViewById<RecyclerView>(R.id.cashflowRecyclerView).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter
        }

    }

    fun add(view: View) {
        val frequencySpinner = findViewById<Spinner>(R.id.inputFrequency)
        val amountTextView = findViewById<TextView>(R.id.inputAmount)

        if (amountTextView.text.toString() == "") return

        cashFlows.add(
            CashFlow(
                "",
                Frequency.valueOf(frequencySpinner.selectedItem.toString().toUpperCase()),
                java.lang.Double.parseDouble(amountTextView.text.toString())
            )
        )
        totalCashFlows()
        updateCashFlowTable()
    }

    fun totalCashFlows() {
        var totalAmount = 0.0
        cashFlows.forEach { totalAmount += it.amount }
        findViewById<TextView>(R.id.cashFlowTotal).text = totalAmount.toString()
    }

    private fun setFrequencyConverter(desiredFrequency: Frequency) {
        viewAdapter.cashFlowConversion = CashFlowConversion(desiredFrequency)
    }

    private fun updateCashFlowTable() {
        viewAdapter.notifyDataSetChanged()
    }
}
