package com.example.signaling1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.signaling1.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var database:DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registerBtn.setOnClickListener {
            val firstName=binding.userNameTextEt.text.toString()
            val lastname=binding.lastNameTextEt.text.toString()
            val age=binding.ageTextEt.text.toString()
            val username=binding.userNameTextEt.text.toString()

            database=FirebaseDatabase.getInstance().getReference("Users")
            val user= User(firstName, lastname, age, username)
            database.child(username).setValue(user).addOnSuccessListener {
                binding.nameTextEt.text.clear()
                binding.lastNameTextEt.text.clear()
                binding.ageTextEt.text.clear()
                binding.userNameTextEt.text.clear()

                Toast.makeText(this, "Successfully Saved", Toast.LENGTH_SHORT).show()



            }


        }

        supportFragmentManager.beginTransaction()
            .add(R.id.container,LoginFragment())
            .commit()
    }
}