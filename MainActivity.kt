package com.example.multiplayertest

import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    val db = FirebaseFirestore.getInstance()
    val settings = FirebaseFirestoreSettings.Builder()
        .setTimestampsInSnapshotsEnabled(true)
        .build()


    val TAG = "Firestore_TAG"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db.firestoreSettings = settings

        val docRef = db.collection("game_data").document("name_and_rolls")
        docRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                return@addSnapshotListener
            }

            if (snapshot != null && snapshot.exists()) {
                Log.d(TAG, "Current data: ${snapshot.data}")
                Toast.makeText(this, "Data Changed", Toast.LENGTH_SHORT).show()
            } else {
                Log.d(TAG, "Current data: null")
            }
        }
    }

    fun rollDice():Int {
        return Random.nextInt(5) + 2
    }

    fun btnRollDice(view: View) {
        val gameData = hashMapOf<String, Any>(
            "name" to etName.text.toString(),
            "dice_roll" to rollDice()
        )

        db.collection("game_data").document("name_and_rolls")
            .set(gameData)
            .addOnSuccessListener { Log.d(TAG, "Data succesfully writter to database") }
            .addOnFailureListener { e -> Log.w(TAG, "Error writing data", e) }
    }
}
