package com.example.projektzpo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListAdapter(val measurements: List<ListElement>) : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var dateTextView: TextView = itemView.findViewById(R.id.dateTextView)
        val weightTextView: TextView = itemView.findViewById(R.id.weightTextView)
        val bloodPressureTextView: TextView = itemView.findViewById(R.id.bloodPressureTextView)
        val pulseTextView: TextView = itemView.findViewById(R.id.pulseTextView)
        val saturationTextView: TextView = itemView.findViewById(R.id.saturationTextView)
        val glucoseTextView: TextView = itemView.findViewById(R.id.glucoseTextView)
        val cholesteroleTextView: TextView = itemView.findViewById(R.id.cholesteroleTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)

        return ListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val currentMeasurement = measurements[position]

        holder.dateTextView.text =
            "Data: " + if (currentMeasurement.date != null) currentMeasurement.date else "Brak danych"
        holder.weightTextView.text = "Waga: " + if(currentMeasurement.weight != null) currentMeasurement.weight.toString() else "Brak danych"
        holder.bloodPressureTextView.text = "Ciśnienie krwi: " + if (currentMeasurement.bloodPressure != null) currentMeasurement.bloodPressure else "Brak danych"
        holder.pulseTextView.text = "Tętno: " + if (currentMeasurement.pulse != null) currentMeasurement.pulse else "Brak danych"
        holder.saturationTextView.text = "Saturacja: " + if (currentMeasurement.saturation != null) currentMeasurement.saturation else "Brak danych"
        holder.glucoseTextView.text = "Poziom glukozy: " + if (currentMeasurement.glucose != null) currentMeasurement.glucose else "Brak danych"
        holder.cholesteroleTextView.text = "Poziom cholesterolu: " + if (currentMeasurement.cholesterole != null) currentMeasurement.cholesterole else "Brak danych"

    }

    override fun getItemCount() = measurements.size
}