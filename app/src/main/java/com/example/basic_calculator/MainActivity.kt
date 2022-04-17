package com.example.basic_calculator

import android.graphics.Color
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import java.lang.invoke.ConstantCallSite
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import java.lang.Double.parseDouble
import java.lang.Math.pow

class MainActivity : AppCompatActivity() {
    var operation = arrayOfNulls<String>(3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun addition(val_1 : Int, val_2 : Int) : Int {
        return val_1 + val_2
    }
    private fun substraction(val_1 : Int, val_2 : Int) : Int {
        return val_1 - val_2
    }
    private fun multiplication(val_1 : Int, val_2 : Int) : Int {
        return val_1 * val_2
    }
    private fun division(val_1 : Int, val_2 : Int) : Int {
        return val_1 / val_2
    }
    private fun powerto(val_1 : Double, val_2 : Double) : Double {
        return pow(val_1, val_2)
    }
    private fun root(val_1 : Double, val_2 : Double) : Double {
        return pow(val_2, 1/val_1)
    }

    fun enter_digit(view: View) {
        val id = view.id
        val expression = findViewById<TextView>(R.id.text_expression)
        var digit : Int

        when (id) {
            R.id.button_0 -> digit = 0
            R.id.button_1 -> digit = 1
            R.id.button_2 -> digit = 2
            R.id.button_3 -> digit = 3
            R.id.button_4 -> digit = 4
            R.id.button_5 -> digit = 5
            R.id.button_6 -> digit = 6
            R.id.button_7 -> digit = 7
            R.id.button_8 -> digit = 8
            R.id.button_9 -> digit = 9
            else -> digit = 0
        }
        expression.text = expression.text.toString() + digit

        if (operation[0]?.isNotEmpty() == true) {
            operation[2] = digit.toString()
        }
    }

    fun enter_symbol(view: View) {
        val id = view.id
        var expression = findViewById<TextView>(R.id.text_expression).text.toString()
        var symbol : String

        if (      !("+" in expression ||
                    "-" in expression ||
                    "x" in expression ||
                    ":" in expression ||
                    "^" in expression ||
                    "√" in expression
        )) {
            when (id) {
                R.id.button_plus -> symbol = "+"
                R.id.button_minus -> symbol = "-"
                R.id.button_multiplication -> symbol = "x"
                R.id.button_divide -> symbol = ":"
                R.id.button_powerto -> symbol = "^"
                R.id.button_root -> symbol = "√"
                else -> symbol = "syntax"
            }
            operation[0] = expression
            operation[1] = symbol
            expression += symbol
        }
    }

    fun calc_result(view: View) {
        val expression = findViewById<TextView>(R.id.text_expression).text.toString()
        val result = findViewById<TextView>(R.id.text_result)
        var temp : Double = 0.0

        if (operation[1]?.isNotEmpty() == true) {
            when(operation[1]) {
                "+" -> temp = addition(Integer.parseInt(operation[0]!!), Integer.parseInt(operation[2]!!)).toDouble()
                "-" -> temp = substraction(Integer.parseInt(operation[0]!!), Integer.parseInt(operation[2]!!)).toDouble()
                "x" -> temp = multiplication(Integer.parseInt(operation[0]!!), Integer.parseInt(operation[2]!!)).toDouble()
                ":" -> temp = division(Integer.parseInt(operation[0]!!), Integer.parseInt(operation[2]!!)).toDouble()
                "^" -> temp = powerto((operation[0]!!.toDouble()), (operation[2]!!.toDouble()))
                "√" -> temp = root((operation[0]!!.toDouble()), (operation[2]!!.toDouble()))
            }
            result.text = temp.toString()
        }
        else {
            result.text = expression
        }
    }
}