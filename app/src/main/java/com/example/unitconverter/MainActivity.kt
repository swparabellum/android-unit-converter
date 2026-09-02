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

    @SuppressLint("SetTextI18n")
    private fun appinit() {
        //TODO("Not yet implemented")
        mainBinding.textView1.text = ""
        mainBinding.textView2.text = ""
        mainBinding.BtnUnitSelecter.text = "kg to lb"
    }

    @SuppressLint("SetTextI18n")
    fun selector(){
        // 1. PopupMenu 생성 (Context, 기준 버튼)
        val popup = PopupMenu(this, mainBinding.BtnUnitSelecter)

        // 2. popup.xml 불러오기 (R.menu.popup)
        popup.menuInflater.inflate(R.menu.popup, popup.menu)

//        // 3. 메뉴 아이템 클릭 이벤트 설정
        popup.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                // popup.xml 내부의 <item android:id="@+id/..." /> ID에 맞춰 처리
                R.id.itmKgtolb -> {
                    mainBinding.BtnUnitSelecter.text = "kg to lb"
                    true
                }
                R.id.itmLbtokg -> {
                    mainBinding.BtnUnitSelecter.text = "lb to kg"
                    true
                }
                R.id.itmKmtokt -> {
                    mainBinding.BtnUnitSelecter.text = "km/h to kt"
                    true
                }
                R.id.itmKmtonm -> {
                    mainBinding.BtnUnitSelecter.text = "km to NM"
                    true
                }
                R.id.itmNmtokm -> {
                    mainBinding.BtnUnitSelecter.text = "NM to km"
                    true
                }
                else -> false
            }
        }
        popup.show()
    }
}