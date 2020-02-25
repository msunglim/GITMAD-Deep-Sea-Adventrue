package club.gitmad.deepseaadventure

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



    }

    fun createRoom(view: View) {
        Toast.makeText(this, "TExt", Toast.LENGTH_LONG).show()
        val intent = Intent(this, JoinGame::class.java)
        startActivity(intent)
    }


}

