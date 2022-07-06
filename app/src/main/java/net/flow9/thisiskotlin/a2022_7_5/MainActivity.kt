package net.flow9.thisiskotlin.a2022_7_5

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.NumberPicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.edit
import kotlin.math.max

class MainActivity : AppCompatActivity() {

	private val numberPicker1 : NumberPicker by lazy {
		findViewById<NumberPicker>(R.id.numberPicker1).apply {
			minValue = 0
			maxValue = 9
		}
	}

	private val numberPicker2 : NumberPicker by lazy {
		findViewById<NumberPicker>(R.id.numberPicker2).apply {
			minValue = 0
			maxValue = 9
		}
	}

	private val numberPicker3 : NumberPicker by lazy {
		findViewById<NumberPicker>(R.id.numberPicker3).apply {
			minValue = 0
			maxValue = 9
		}
	}

	private val openButton : AppCompatButton by lazy {
		findViewById<AppCompatButton>(R.id.openButton)
	}

	private val changePwButton : AppCompatButton by lazy {
		findViewById<AppCompatButton>(R.id.changePasswordButton)
	}

	private var changePassMode = false

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		numberPicker1
		numberPicker2
		numberPicker3

		openButton.setOnClickListener {
			if (changePassMode) {
				Toast.makeText(this, "비밀번호를 변경 중입니다.",Toast.LENGTH_SHORT).show()
			}

			val passPreferences = getSharedPreferences("password", MODE_PRIVATE)
		    val passwordUser = "${numberPicker1.value}${numberPicker2.value}${numberPicker3.value}"

			if (passPreferences.getString("password","000").equals(passwordUser)) {
				startActivity(Intent(this,SecondActivity::class.java))
			} else {
				showAlertDialog()
			}
		}

		changePwButton.setOnClickListener {
				val passPreferences = getSharedPreferences("password", MODE_PRIVATE)
				val passwordUser = "${numberPicker1.value}${numberPicker2.value}${numberPicker3.value}"

				if(changePassMode) {
					passPreferences.edit(true) {
						putString("password",passwordUser)
					}
					changePassMode = true
					changePwButton.setBackgroundColor(Color.BLACK)
				} else {
					if(passPreferences.getString("password", "000").equals(passwordUser)) {
						changePassMode = true
						Toast.makeText(this, "변경할 패스워드를 입력해 주세요", Toast.LENGTH_SHORT).show()
					} else {
						showAlertDialog()
					}
				}
			}
	}
	private fun showAlertDialog() {
		AlertDialog.Builder(this)
			.setTitle("실패")
			.setMessage("비밀번호가 잘못되었습니다.")
			.setPositiveButton("확인"){_,_ ->}
			.create()
			.show()
	}
}