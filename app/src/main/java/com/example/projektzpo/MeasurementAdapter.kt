package com.example.projektzpo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView

class MeasurementAdapter(private val measurements: MutableList<Measurement>) : RecyclerView.Adapter<MeasurementAdapter.MeasurementViewHolder>() {

    class MeasurementViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val measurementInput: EditText = itemView.findViewById(R.id.inputMeasurement)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeasurementViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.measurement_item, parent, false)
        return MeasurementViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MeasurementViewHolder, position: Int) {
        val currentMeasurement = measurements[position]
        holder.measurementInput.hint = currentMeasurement.type
    }

    override fun getItemCount() = measurements.size

}