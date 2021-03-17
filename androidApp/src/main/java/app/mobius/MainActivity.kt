package app.mobius

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.mobius.model.TestDomain

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val testDomain = TestDomain("")
        testDomain.test
    }
}
