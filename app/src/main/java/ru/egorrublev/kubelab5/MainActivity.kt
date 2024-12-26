package ru.egorrublev.kubelab5

import android.graphics.Color
import android.os.Bundle
import android.widget.GridLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat

class MainActivity : AppCompatActivity() {

    private lateinit var gridLayout: GridLayout
    private val tileCount = 16 // 4x4 плитки
    private val baseColor = Color.rgb(100, 150, 200) // Базовый цвет

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Включение режима edge-to-edge
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContentView(R.layout.activity_main)

        gridLayout = findViewById(R.id.gridLayout)
        createTiles()
    }

    private fun createTiles() {
        for (i in 0 until tileCount) {
            val textView = TextView(this)
            textView.layoutParams = GridLayout.LayoutParams().apply {
                width = 0
                height = 0
                rowSpec = GridLayout.spec(i / 4, 1f)
                columnSpec = GridLayout.spec(i % 4, 1f)
            }
            textView.setBackgroundColor(generateColor(i)) // Изначально цвет с изменением альфа-канала
            textView.setOnClickListener {
                // Изменение цвета только нажатой плитки
                textView.setBackgroundColor(generateRandomColor())
            }
            gridLayout.addView(textView)
        }
    }

    private fun generateColor(index: Int): Int {
        // Генерация цвета с изменением альфа-канала, который связан с базовым цветом
        val alpha = (255 - (index * 15)).coerceAtLeast(0) // Альфа-канал изменяется в зависимости от индекса
        return Color.argb(alpha, Color.red(baseColor), Color.green(baseColor), Color.blue(baseColor))
    }

    private fun generateRandomColor(): Int {
        // Генерация случайного цвета
        return Color.rgb((0..255).random(), (0..255).random(), (0..255).random())
    }

    override fun onResume() {
        super.onResume()
        changeTileColors() // Изменение цвета плиток на случайный при возвращении в приложение
    }

    private fun changeTileColors() {
        for (i in 0 until gridLayout.childCount) {
            val textView = gridLayout.getChildAt(i) as TextView
            textView.setBackgroundColor(generateColorWithRandomAlpha()) // Генерация цвета для каждой плитки с разным альфа-каналом
        }
    }

    private fun generateColorWithRandomAlpha(): Int {
        // Генерация цвета с случайным альфа-каналом
        val alpha = (0..255).random() // Случайный альфа-канал
        return Color.argb(alpha, Color.red(baseColor), Color.green(baseColor), Color.blue(baseColor))
    }
}