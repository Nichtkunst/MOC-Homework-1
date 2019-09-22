package at.techikum_wien.fleischhacker.mochomework1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private val TAG = "MyActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnHandleSum: Button = findViewById(R.id.btn_handleSum)
        val btnHandleNavigate: Button = findViewById(R.id.btn_handleNavigate)
        val editFirst: EditText = findViewById(R.id.ed_num_entry_1)
        val editSecond: EditText = findViewById(R.id.ed_num_entry_2)
        val resultText: TextView = findViewById(R.id.tv_sum_output)

        val simpleSeekBar: SeekBar = findViewById(R.id.sb_seekbar)
        val resultSeekBar: TextView = findViewById(R.id.tv_seekbar_output)

        btnHandleSum.setOnClickListener {
            val firstNumText: String = editFirst.getText().toString()
            val secondNumText: String = editSecond.getText().toString()

            if (editFirst.text.isEmpty() && editSecond.text.isEmpty())
                Toast.makeText(this, "Nummer eingeben", Toast.LENGTH_SHORT).show()
            else
                resultText.setText(
                    addNumbers(
                        checkNumber(firstNumText),
                        checkNumber(secondNumText)
                    )
                )
//            resultText.text = addNumbers(checkNumber(firstNumText), checkNumber(secondNumText))

        }

        btnHandleNavigate.setOnClickListener {
            val firstNumText: String = editFirst.getText().toString()
            val secondNumText: String = editSecond.getText().toString()

            val result: String = addNumbers(checkNumber(firstNumText), checkNumber(secondNumText))

            val context = this@MainActivity
            val destinationActivity = ChildActivity::class.java
            val startChildActivityIntent = Intent(context, destinationActivity)
            startChildActivityIntent.putExtra(Intent.EXTRA_TEXT, result)
            startActivity(startChildActivityIntent)
        }

        simpleSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            var progressChangedValue = 0

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                progressChangedValue = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // TODO Auto-generated method stub
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
//                Toast.makeText(
//                    this@MainActivity, "Progress: $progressChangedValue",
//                    Toast.LENGTH_SHORT
//                ).show()
                resultSeekBar.setText(progressChangedValue.toString())
            }
        })


    }

    private fun addNumbers(n1: String, n2: String): String {
        val a = n1.toInt()
        Log.v(TAG, "n1: $a")
        val b = n2.toInt()
        Log.v(TAG, "n2: $b")
        val sum = a + b
        Log.v(TAG, "sum: $sum")
        return sum.toString()
    }

    private fun checkNumber(n: String): String {
        var numeric = true
        val nonNumericValue: Int = 0
        numeric = n.matches("-?\\d+(\\.\\d+)?".toRegex())

        when {
            numeric -> {
                Log.v(TAG, "$n ist eine Nummer")
                return n
            }
            else -> {
                Log.v(TAG, "$n ist keine Nummer")
                return nonNumericValue.toString()
            }
        }

        return nonNumericValue.toString()
    }

}
