package com.example.investinyourdream

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.investinyourdream.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalc.setOnClickListener {
            val valueContributionStr = binding.edtValor.text.toString()
            val valueMonthStr = binding.edtMeses.text.toString()
            val valueFeesStr = binding.edtJuros.text.toString()

            if (valueContributionStr.isEmpty() || valueMonthStr.isEmpty() || valueFeesStr.isEmpty()) {
                Snackbar.make(
                    binding.edtValor,
                    "Please fill in all fields",
                    Snackbar.LENGTH_LONG
                ).show()
            } else {

                val valueContribution = valueContributionStr.toFloat()
                val valueMonth = valueMonthStr.toFloat()
                val valueFees = valueFeesStr.toFloat()

                val valueFessConvert = valueFees / 100
                val calculateIncome =
                    valueContribution * ((1 + valueFessConvert).pow(valueMonth) - 1) / (valueFessConvert)
                val valueIncome = calculateIncome - (valueContribution * valueMonth)
                val calculateIncomeFormat = String.format("%.2f", calculateIncome)
                val valueIncomeFormat = String.format("%.2f", valueIncome)
                binding.tvValueFinal.text = "R$ $calculateIncomeFormat"
                binding.tvValueRendimentos.text = "R$ $valueIncomeFormat"
            }
        }
        binding.btnReset.setOnClickListener {
            binding.edtValor.setText("")
            binding.edtMeses.setText("")
            binding.edtJuros.setText("")
            binding.tvValueFinal.setText("")
            binding.tvValueRendimentos.setText("")

            Snackbar.make(binding.root, "reset fields", Snackbar.LENGTH_LONG).show()

        }
    }
}
