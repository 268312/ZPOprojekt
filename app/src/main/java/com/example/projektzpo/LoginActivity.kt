package com.example.projektzpo

import android.os.Bundle
import androidx.navigation.findNavController
import com.example.projektzpo.databinding.LoginViewBinding



open class LoginActivity : BaseActivity() {

    private lateinit var binding: LoginViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.logIn.setOnClickListener {
                view ->
            view.findNavController().navigate(R.id.action_LogInView_to_MainView)
         //   logInRegisteredUser()
        }
        binding.signUp.setOnClickListener {view ->
            view.findNavController().navigate(R.id.action_LogInView_to_RegisterView)
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
