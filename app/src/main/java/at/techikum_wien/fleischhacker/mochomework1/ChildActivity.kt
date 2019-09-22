package at.techikum_wien.fleischhacker.mochomework1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ChildActivity : AppCompatActivity() {

    private var mDisplayText: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_child)

        mDisplayText = findViewById(R.id.tv_result) as TextView
        val intent = intent

        if (intent.hasExtra(Intent.EXTRA_TEXT)) {
            val textEntered = intent.getStringExtra(Intent.EXTRA_TEXT)
            mDisplayText!!.setText(textEntered)
        }
    }
}
