package com.example.projektzpo

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import com.example.projektzpo.databinding.LoginViewBinding
import com.google.firebase.auth.FirebaseAuth
import org.mindrot.jbcrypt.BCrypt


open class LoginActivity : BaseActivity() {

    private lateinit var binding: LoginViewBinding

    private var inputEmail: EditText? = null

    private var inputPassword: EditText? = null

    private var loginButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        inputEmail = findViewById(R.id.inputEmail)
        inputPassword = findViewById(R.id.inputPassword)
        loginButton = findViewById(R.id.log_in)

        loginButton?.setOnClickListener{
            logInRegisteredUser()
        }

        // Przejście do RegisterViewActivity po kliknięciu przycisku "signUp"
        binding.signUp.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun validateLoginDetails(): Boolean {
        return when {
            TextUtils.isEmpty(inputEmail?.text.toString().trim { it <= ' '}) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_email), true)
                false
            }
            !inputEmail?.text.toString().trim {it <= ' '}.contains('@') -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_invalid_email), true)
                false
            }
            TextUtils.isEmpty(inputPassword?.text.toString().trim { it <= ' '}) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_password), true)
                false
            }
            else -> {
                showErrorSnackBar("Your details are valid", false)
                true
            }
        }
    }

    private fun logInRegisteredUser() {
        if (validateLoginDetails()) {
            val email = inputEmail?.text.toString().trim { it <= ' ' }
            val password = inputPassword?.text.toString().trim { it <= ' ' }

            // Logowanie za pomocą FirebaseAuth
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
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

    open fun goToMainActivity() {
        val user = FirebaseAuth.getInstance().currentUser
        val uid = user?.email.toString()

        // Przekazanie wartości uid
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("uID", uid)
        startActivity(intent)
    }
}
