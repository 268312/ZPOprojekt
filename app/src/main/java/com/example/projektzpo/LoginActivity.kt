package com.example.projektzpo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.projektzpo.databinding.LoginViewBinding
import com.google.firebase.auth.FirebaseAuth


open class LoginActivity : AppCompatActivity() {

    private lateinit var binding: LoginViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.logIn.setOnClickListener {
            logInRegisteredUser()
        }
        binding.signUp.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_RegisterFragment)
        }
    }


        private fun logInRegisteredUser() {
            if (validateLoginDetails()) {
                val login = binding.inputLogin?.text.toString().trim() { it <= ' ' }
                val password = binding.inputPassword?.text.toString().trim() { it <= ' ' }

                // Logowanie za pomocą FirebaseAuth
                FirebaseAuth.getInstance().signInWithEmailAndPassword(login, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            showErrorSnackBar(resources.getString(R.string.login_successful), false)
                            goToMainActivity()
                            finish()
                        } else {
                            showErrorSnackBar(task.exception!!.message.toString(), true)
                        }
                    }
            }
        }
}
