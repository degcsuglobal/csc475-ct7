package com.dangrover.unitconverter

import android.R
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dangrover.unitconverter.databinding.FragmentUnitConverterBinding
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class UnitConverterFragment : Fragment(), TextWatcher, AdapterView.OnItemSelectedListener {

    private var _binding: FragmentUnitConverterBinding? = null
    private var _unitConverter : UnitConverter = UnitConverter()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentUnitConverterBinding.inflate(inflater, container, false)

        // Set adapter for spinners
        // Make array of the formatted valeus in the enum
        var formatted : Array<String> = arrayOf<String>()
        for(v in UnitConverter.Unit.entries.iterator()) {
            formatted += _unitConverter.getUnitAbbreviation(v)
        }

        // set adapters
        binding.fromUnitSpinner.adapter = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, formatted)
        binding.toUnitSpinner.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, formatted)

        // call updateConversion() every time text is changed or spinners changed
        binding.fromQuantity.addTextChangedListener(this)
        binding.fromUnitSpinner.setOnItemSelectedListener(this)
        binding.toUnitSpinner.setOnItemSelectedListener(this)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateConversion()
    }

    private fun updateConversion(){

        // if either field empty or non-numeric, bail and show empty text in resultDisplayLabel
        if(binding.fromQuantity.text.toString().isEmpty() || binding.fromQuantity.text.toString().toDoubleOrNull() == null) {
            binding.resultDisplayLabel.text = ""
            return
        }else{
            var fromUnit = UnitConverter.Unit.values()[binding.fromUnitSpinner.selectedItemPosition]
            var toUnit = UnitConverter.Unit.values()[binding.toUnitSpinner.selectedItemPosition]
            var fromValue = binding.fromQuantity.text.toString().toDoubleOrNull()

            // perform conversion
            var toValue = _unitConverter.convert(fromValue!!, fromUnit, toUnit)

            // format and display (3 decimal places)
            var toValueFormatted = String.format("%.3f", toValue) + " " + _unitConverter.getUnitAbbreviation(toUnit)
            binding.resultDisplayLabel.text = toValueFormatted
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Text watcher (for input fields)
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        // Ignore
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        // Ignore
    }

    override fun afterTextChanged(s: Editable?) {
        updateConversion()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        updateConversion()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        updateConversion()
    }
}