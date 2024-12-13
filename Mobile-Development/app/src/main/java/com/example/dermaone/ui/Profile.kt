package com.example.dermaone.ui

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.dermaone.R
import com.example.dermaone.databinding.FragmentProfileBinding
import com.example.dermaone.ui.ui.LoginActivity

class Profile : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedPref: SharedPreferences
    private var isLanguageVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPref = requireActivity().getSharedPreferences("user_prefs", android.content.Context.MODE_PRIVATE)
    }

    override fun onCreateView(
        inflater: android.view.LayoutInflater, container: android.view.ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        setupView()
        setupAction()
        return binding.root
    }

    private fun setupView() {
        val displayName = sharedPref.getString("display_name", "Guest")
        binding.tvUserName.text = displayName

        val photoUrl = sharedPref.getString("photo_url", null)

        if (photoUrl != null) {
            Glide.with(this)
                .load(photoUrl)
                .circleCrop()
                .placeholder(R.drawable.baseline_person_24)
                .into(binding.imgProfile)
        }
    }

    private fun setupAction() {
        binding.btnLogout.setOnClickListener { logout() }

        binding.arrowSettings.setOnClickListener {
            toggleLanguageContainer()
        }

        binding.btnChangeLanguage.setOnClickListener {
            changeLanguage()
        }
    }

    private fun logout() {
        sharedPref.edit().remove("id_token").apply()
        sharedPref.edit().clear().apply()

        val intent = Intent(requireContext(), LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    private fun toggleLanguageContainer() {
        if (isLanguageVisible) {
            val slideUp = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_up)
            binding.languageContainer.startAnimation(slideUp)
            binding.languageContainer.visibility = View.GONE
        } else {
            val slideDown = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_down)
            binding.languageContainer.startAnimation(slideDown)
            binding.languageContainer.visibility = View.VISIBLE
        }
        isLanguageVisible = !isLanguageVisible
    }

    private fun changeLanguage() {
        val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
