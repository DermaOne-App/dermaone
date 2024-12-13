package com.example.dermaone.ui.ui

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.dermaone.databinding.ActivityLoginBinding
import com.example.dermaone.ui.MainActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.example.dermaone.R

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var sharedPref: SharedPreferences

    private val googleSignInLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        handleSignInResult(task)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPref = getSharedPreferences("user_prefs", MODE_PRIVATE)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        if (isUserLoggedIn()) {
            navigateToMainActivity()
            return
        }

        val spannable = SpannableString("DermaOne")

        spannable.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(this, R.color.myprimary)),
            0, 5,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        spannable.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(this, R.color.merah)),
            5, 8,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        binding.dermaOneTextView.text = spannable

        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("289266191535-h1eeeihqj9e4tabv7umj46mpjajs5eqd.apps.googleusercontent.com")
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions)

        binding.googleSignInButton.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            val signInIntent = googleSignInClient.signInIntent
            googleSignInLauncher.launch(signInIntent)
        }

        binding.signUpText.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

        binding.togglePasswordVisibility.setOnClickListener {
            val inputType = binding.passwordEditText.inputType
            if (inputType == (android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD)) {
                binding.passwordEditText.inputType = android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                binding.togglePasswordVisibility.setImageResource(R.drawable.ic_eye)
            } else {
                binding.passwordEditText.inputType = android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
                binding.togglePasswordVisibility.setImageResource(R.drawable.ic_eye_off)
            }
            binding.passwordEditText.text?.let {
                binding.passwordEditText.setSelection(it.length)
            }
        }
    }

    private fun handleSignInResult(task: com.google.android.gms.tasks.Task<GoogleSignInAccount>) {
        try {
            val account = task.getResult(ApiException::class.java)
            val idToken = account?.idToken
            val displayName = account?.displayName
            val photoUrl = account?.photoUrl?.toString()
            Log.d("ID_TOKEN", "Token: $idToken")
            Log.d("DISPLAY_NAME", "Name: $displayName")

            if (idToken != null) {
                saveLoginState(idToken, displayName, photoUrl)
                navigateToMainActivity()
            } else {
                Toast.makeText(this, "Failed to retrieve ID Token.", Toast.LENGTH_SHORT).show()
            }
        } catch (e: ApiException) {
            Log.w("SignInFailed", "Sign-In Failed with code: ${e.statusCode}")
            Toast.makeText(this, "Sign-In Failed: ${e.message}", Toast.LENGTH_SHORT).show()
        } finally {
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun saveLoginState(idToken: String, displayName: String?, photoUrl: String?) {
        with(sharedPref.edit()) {
            putString("id_token", idToken)
            putString("display_name", displayName)
            putString("photo_url", photoUrl)
            putBoolean("is_logged_in", true)
            apply()
        }
    }

    private fun isUserLoggedIn(): Boolean {
        return sharedPref.getBoolean("is_logged_in", false)
    }

    private fun navigateToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}