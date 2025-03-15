package com.example.newcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.newcalculator.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding
    private var operand1: Double? = null
    private var operator: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListeners()
    }

    private fun setListeners() {
        // Number button listeners
        binding.one.setOnClickListener { append("1") }
        binding.two.setOnClickListener { append("2") }
        binding.three.setOnClickListener { append("3") }
        binding.four.setOnClickListener { append("4") }
        binding.five.setOnClickListener { append("5") }
        binding.six.setOnClickListener { append("6") }
        binding.seven.setOnClickListener { append("7") }
        binding.eight.setOnClickListener { append("8") }
        binding.nine.setOnClickListener { append("9") }
        binding.zero.setOnClickListener { append("0") }

        // Operator button listeners
        binding.plus.setOnClickListener { setOperator("+") }
        binding.minus.setOnClickListener { setOperator("-") }
        binding.multi.setOnClickListener { setOperator("*") }
        binding.divide.setOnClickListener { setOperator("/") }
        binding.mode.setOnClickListener { setOperator("%") }
        binding.comma.setOnClickListener { append(".") }

        // Other button listeners
        binding.AC.setOnClickListener { clear() }
        binding.cancle.setOnClickListener { cancel() }
        binding.equal.setOnClickListener { calculate() }
    }

    private fun setOperator(newOperator: String) {
        val result = binding.firstnum.text.toString()
        if (result.isNotEmpty()) {
            operand1 = result.toDoubleOrNull()
            operator = newOperator
            binding.firstnum.text = ""
        }
    }

    private fun calculate() {
        val result = binding.firstnum.text.toString()
        if (result.isNotEmpty() && operator != null && operand1 != null) {
            val operand2 = result.toDoubleOrNull()
            if (operand2 != null) {
                val ans = when (operator) {
                    "+" -> operand1!! + operand2
                    "-" -> operand1!! - operand2
                    "*" -> operand1!! * operand2
                    "/" -> operand1!! / operand2
                    "%" -> operand1!! % operand2
                    else -> 0.0
                }
                binding.firstnum.text = ans.toString()
                operand1 = ans // Store the result for further calculations
                operator = null
            }
        }
    }

    private fun clear() {
        binding.firstnum.text = ""
        operand1 = null
        operator = null
    }

    private fun cancel() {
        val result = binding.firstnum.text.toString()
        binding.firstnum.text = result.dropLast(1) // Removes the last character
    }

    private fun append(number: String) {
        val result = binding.firstnum.text.toString()
        binding.firstnum.text = result + number
    }
}
