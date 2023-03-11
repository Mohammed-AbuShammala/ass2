package com.example.home_work

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.home_work.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("message")
        binding.button.setOnClickListener {

            val person = hashMapOf(
                "name" to binding.editTextTextPersonName.text.toString(),
                "id" to binding.editTextTextPersonName2.text.toString(),
                "age" to binding.editTextTextPersonName3.text.toString(),
            )

            myRef.setValue(person);

        }
        binding.button2.setOnClickListener {
            myRef.addValueEventListener(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    val value = snapshot.getValue()
                    binding.textView.text = value.toString()
                }

                override fun onCancelled(error: DatabaseError) {}
            })
        }
    }
}


