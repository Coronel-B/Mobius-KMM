package app.mobius.android.app.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.mobius.R

class AndroidAppNavHostActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
