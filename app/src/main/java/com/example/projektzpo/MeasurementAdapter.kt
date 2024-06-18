package com.example.projektzpo

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView

class MeasurementAdapter(public val measurements: List<Measurement>) : RecyclerView.Adapter<MeasurementAdapter.MeasurementViewHolder>() {

    class MeasurementViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val weightInput: EditText = itemView.findViewById(R.id.inputWeight)
        val bloodPressureInput: EditText = itemView.findViewById(R.id.inputBloodPressure)
        val pulseInput: EditText = itemView.findViewById(R.id.inputPulse)
        val saturationInput: EditText = itemView.findViewById(R.id.inputSaturation)
        val glucoseInput: EditText = itemView.findViewById(R.id.inputGlucose)
        val cholesteroleInput: EditText = itemView.findViewById(R.id.inputCholesterole)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeasurementViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.measurement_item, parent, false)
        return MeasurementViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MeasurementViewHolder, position: Int) {
        val currentMeasurement = measurements[position]

        holder.weightInput.setText(currentMeasurement.weight.toString())
        holder.bloodPressureInput.setText(currentMeasurement.bloodPressure)
        holder.pulseInput.setText(currentMeasurement.pulse.toString())
        holder.saturationInput.setText(currentMeasurement.saturation.toString())
        holder.glucoseInput.setText(currentMeasurement.glucose.toString())
        holder.cholesteroleInput.setText(currentMeasurement.cholesterole.toString())

        holder.weightInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                currentMeasurement.weight = s.toString().toDoubleOrNull() ?: 0.0
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        holder.bloodPressureInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                currentMeasurement.bloodPressure = s.toString()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        holder.pulseInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                currentMeasurement.pulse = s.toString().toIntOrNull() ?: 0
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        holder.saturationInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                currentMeasurement.saturation = s.toString().toIntOrNull() ?: 0
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        holder.glucoseInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                currentMeasurement.glucose = s.toString().toDoubleOrNull() ?: 0.0
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        holder.cholesteroleInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                currentMeasurement.cholesterole = s.toString().toDoubleOrNull() ?: 0.0
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    override fun getItemCount() = measurements.size
}