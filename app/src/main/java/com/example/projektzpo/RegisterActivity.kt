package com.example.projektzpo

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Log.*
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.Spinner
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.Calendar
import kotlin.math.log

class RegisterActivity : BaseActivity(), AdapterView.OnItemSelectedListener {

    private var registerButton: Button? = null
    private var inputName: EditText? = null
    private var dropdownSex: Spinner? = null
    private var inputHeight: EditText? = null
    private var inputBirthYear: NumberPicker? = null
    private var inputEmail: EditText? = null
    private var inputPassword: EditText? = null
    private var inputRepPassword: EditText? = null

    val db = Firebase.firestore

    private var selectedYear: Int = Calendar.getInstance().get(Calendar.YEAR)

    private val dbOperations = FireStoreHandler(db)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_view)
        registerButton = findViewById(R.id.create_account)
        inputName = findViewById(R.id.inputName)
        dropdownSex = findViewById(R.id.sexDropdown)
        ArrayAdapter.createFromResource(
            this, R.array.sex_array, android.R.layout.simple_spinner_item
        ).also {
            adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            dropdownSex?.adapter = adapter
        }
        dropdownSex?.onItemSelectedListener = this
        inputHeight = findViewById(R.id.inputHeight)
        inputBirthYear = findViewById(R.id.yearPicker)
        inputBirthYear?.minValue = 1900
        inputBirthYear?.maxValue = Calendar.getInstance().get(Calendar.YEAR)
        inputBirthYear?.value = Calendar.getInstance().get(Calendar.YEAR)
        inputEmail = findViewById(R.id.inputREmail)
        inputPassword = findViewById(R.id.inputRRPassword)
        inputRepPassword = findViewById(R.id.inputRRepPassword)
        registerButton?.setOnClickListener{
            registerUser()
        }
        inputBirthYear?.setOnValueChangedListener {
            _, _, newVal -> selectedYear = newVal
        }

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
        parent?.getItemAtPosition(pos)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        showErrorSnackBar(resources.getString(R.string.err_msg_invalid_sex), true)
    }

    private fun validateRegisterDetails(): Boolean {
        return when {
            TextUtils.isEmpty(inputName?.text.toString().trim { it <= ' '}) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_name), true)
                false
            }
            TextUtils.isEmpty(inputHeight?.text.toString().trim { it <= ' '}) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_height), true)
                false
            }
            TextUtils.isEmpty(inputEmail?.text.toString().trim { it <= ' '}) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_email), true)
                false
            }
            TextUtils.isEmpty(inputPassword?.text.toString().trim { it <= ' '}) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_password), true)
                false
            }
            TextUtils.isEmpty(inputRepPassword?.text.toString().trim { it <= ' '}) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_reppassword), true)
                false
            }
            inputHeight?.text.toString().trim {it <= ' '}.toInt() <= 0 -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_invalid_height), true)
                false
            }
            !inputEmail?.text.toString().trim {it <= ' '}.contains('@') -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_invalid_email), true)
                false
            }
            !inputPassword?.text.toString().trim { it <= ' '}.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\W).{8,}$".toRegex()) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_invalid_password), true)
                false
            }
            inputPassword?.text.toString().trim { it <= ' '} != inputRepPassword?.text.toString().trim {it <= ' '} -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_password_mismatch), true)
                false
            }
            else -> {
                showErrorSnackBar("Your details are valid", false)
                true
            }
        }
    }

    private fun goToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun registerUser() {
        if (validateRegisterDetails()) {
            val email: String = inputEmail?.text.toString().trim { it <= ' ' }
            val password: String = inputPassword?.text.toString().trim { it <= ' ' }
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val firebaseUser: FirebaseUser = task.result!!.user!!
                        showErrorSnackBar(
                            "You are registered successfully. Your user id is ${firebaseUser.uid}",
                            false
                        )
                        val name: String = inputName?.text.toString().trim { it <= ' ' }
                        val sex: String = dropdownSex?.selectedItem.toString()
                        val height: Int = inputHeight?.text.toString().toInt()
                        val birthYear: Int = selectedYear
                        val data = FireStoreData(name, sex, height, birthYear)
                        GlobalScope.launch(Dispatchers.Main) {
                            dbOperations.addData(email, data)
                        }
                        FirebaseAuth.getInstance().signOut()
                        goToLogin()
                    } else {
                        showErrorSnackBar(task.exception!!.message.toString(), true)
                    }
                }
        }
    }
}