package com.example.projektzpo

import android.content.Intent
import android.os.Bundle
import androidx.navigation.findNavController
import com.example.projektzpo.databinding.LoginViewBinding



open class LoginActivity : BaseActivity() {

    private lateinit var binding: LoginViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Przejście do MainViewActivity po kliknięciu przycisku "logIn"
        binding.logIn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Przejście do RegisterViewActivity po kliknięciu przycisku "signUp"
        binding.signUp.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }



//        private fun logInRegisteredUser() {
//            if (validateLoginDetails()) {
//                val login = binding.inputLogin?.text.toString().trim() { it <= ' ' }
//                val password = binding.inputPassword?.text.toString().trim() { it <= ' ' }
//
//                // Logowanie za pomocą FirebaseAuth
//                FirebaseAuth.getInstance().signInWithEmailAndPassword(login, password)
//                    .addOnCompleteListener { task ->
//                        if (task.isSuccessful) {
//                            showErrorSnackBar(resources.getString(R.string.login_successful), false)
//                            goToMainActivity()
//                            finish()
//                        } else {
//                            showErrorSnackBar(task.exception!!.message.toString(), true)
//                        }
//                    }
//            }
//        }
}
