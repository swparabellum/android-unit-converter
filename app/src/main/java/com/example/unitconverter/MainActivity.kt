package com.example.unitconverter

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.unitconverter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    private var currentMode: ConversionMode = ConversionMode.KG_TO_LB
    private var inputString: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()

        val mainView = mainBinding.root
        setContentView(mainView)

        ViewCompat.setOnApplyWindowInsetsListener(mainView) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        appinit()
        mainBinding.BtnUnitSelecter.setOnClickListener { selector() }
    }

    private fun appinit() {
        setupNumberButtons()
        updateMode(ConversionMode.KG_TO_LB)
    }

    private fun setupNumberButtons() {
        val numberButtons = listOf(
            mainBinding.buttonNo0, mainBinding.buttonNo1, mainBinding.buttonNo2,
            mainBinding.buttonNo3, mainBinding.buttonNo4, mainBinding.buttonNo5,
            mainBinding.buttonNo6, mainBinding.buttonNo7, mainBinding.buttonNo8,
            mainBinding.buttonNo9
        )

        for (button in numberButtons) {
            button.setOnClickListener {
                appendNumber(button.text.toString())
            }
        }

        mainBinding.buttonDot.setOnClickListener { appendDot() }
        mainBinding.buttonDel.setOnClickListener { deleteLastDigit() }
    }

    private fun appendNumber(digit: String) {
        if (inputString == "0") {
            inputString = digit
        } else {
            inputString += digit
        }
        calculate()
    }

    private fun appendDot() {
        if (!inputString.contains(".")) {
            inputString = if (inputString.isEmpty()) "0." else "$inputString."
            calculate()
        }
    }

    private fun deleteLastDigit() {
        if (inputString.isNotEmpty()) {
            inputString = inputString.dropLast(1)
            calculate()
        }
    }

    private fun updateMode(mode: ConversionMode) {
        currentMode = mode
        mainBinding.BtnUnitSelecter.text = mode.displayName
        calculate()
    }

    @SuppressLint("DefaultLocale", "SetTextI18n")
    private fun calculate() {
        if (inputString.isEmpty()) {
            mainBinding.textView1.text = "0 ${currentMode.fromUnit}"
            mainBinding.textView2.text = "0 ${currentMode.toUnit}"
            return
        }

        val inputValue = inputString.toDoubleOrNull() ?: 0.0
        val result = currentMode.convert(inputValue)

        mainBinding.textView1.text = "$inputString ${currentMode.fromUnit}"

        val formattedResult = if (result % 1.0 == 0.0) {
            result.toLong().toString()
        } else {
            String.format("%.4f", result).trimEnd('0').trimEnd('.')
        }
        mainBinding.textView2.text = "$formattedResult ${currentMode.toUnit}"
    }

    //popup메뉴 표출하여 원하는 단위변환 모드 선택
    fun selector() {
        val popup = PopupMenu(this, mainBinding.BtnUnitSelecter)
        popup.menuInflater.inflate(R.menu.popup, popup.menu)

        popup.setOnMenuItemClickListener { item ->
            val selectedMode = when (item.itemId) {
                R.id.itmKgtolb -> ConversionMode.KG_TO_LB
                R.id.itmLbtokg -> ConversionMode.LB_TO_KG
                R.id.itmKmtokt -> ConversionMode.KMH_TO_KT
                R.id.itmKttokm -> ConversionMode.KT_TO_KMH
                R.id.itmKmtonm -> ConversionMode.KM_TO_NM
                R.id.itmNmtokm -> ConversionMode.NM_TO_KM
                R.id.itmGaltol -> ConversionMode.GAL_TO_L
                R.id.itmLtogal -> ConversionMode.L_TO_GAL
                R.id.itmFttom -> ConversionMode.FT_TO_M
                R.id.itmMtoft -> ConversionMode.M_TO_FT
                else -> null
            }

            selectedMode?.let {
                updateMode(it)
                true
            } ?: false
        }
        popup.show()
    }
}