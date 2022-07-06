package net.flow9.thisiskotlin.a2022_7_5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.EditText
import androidx.core.content.edit
import androidx.core.widget.addTextChangedListener

class SecondActivity : AppCompatActivity() {

	private val handler = Handler(Looper.getMainLooper())

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_second)

		val diaryEditText = findViewById<EditText>(R.id.diaryEditText)

		val detailPreferences = getSharedPreferences("diary", MODE_PRIVATE)
		diaryEditText.setText(detailPreferences.getString("detail", ""))

		val runnable = Runnable {
			getSharedPreferences("diary", MODE_PRIVATE).edit {
				putString("detail", diaryEditText.text.toString())
			}
		}

		diaryEditText.addTextChangedListener {
			handler.removeCallbacks(runnable)
			handler.postDelayed(runnable, 500)
		}
	}
}