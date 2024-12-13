package com.example.dermaone.ui

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.dermaone.databinding.FragmentDashboardBinding
import com.example.dermaone.ui.ui.ResultActivity
import com.example.dermaone.ui.ui.api.ApiConfig
import com.example.dermaone.ui.ui.response.PredictResponse
import com.example.dermaone.ui.ui.view.DashboardViewModel
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import com.example.dermaone.R
import com.example.dermaone.ui.ui.list.ListDiseases
import com.example.dermaone.ui.ui.virus.ListVirus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Dashboard : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: DashboardViewModel

    private var selectedImageUri: Uri? = null
    private var capturedImageFile: File? = null

    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val uri = result.data?.data
            if (uri != null) {
                selectedImageUri = uri
                binding.imageBoxPreview.setImageURI(uri)
                binding.imageBoxPreview.visibility = View.VISIBLE
                binding.btnPredict.visibility = View.VISIBLE
            } else {
                showToast("Failed to select image")
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as? AppCompatActivity)?.supportActionBar?.hide()

        viewModel = ViewModelProvider(this)[DashboardViewModel::class.java]

        val sharedPref = requireContext().getSharedPreferences("user_prefs", AppCompatActivity.MODE_PRIVATE)
        val displayName = sharedPref.getString("display_name", "User")

        val spannable = SpannableString("DermaOne")
        spannable.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.myprimary)),
            0, 5,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannable.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.merah)),
            5, 8,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        binding.title.text = spannable

        viewModel.selectedImageUri?.let { uri ->
            binding.imageBoxPreview.setImageURI(uri)
            binding.imageBoxPreview.visibility = View.VISIBLE
        }

        binding.imageBox.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT).apply { type = "image/*" }
            val chooser = Intent.createChooser(intent, "Choose a Picture")
            launcherIntentGallery.launch(chooser)
        }

        binding.menuButton.setOnClickListener {
            if (binding.menuPopup.visibility == View.GONE) {
                binding.menuPopup.visibility = View.VISIBLE
            } else {
                binding.menuPopup.visibility = View.GONE
            }
        }

        binding.btnPredict.setOnClickListener {
            val imageFile = getImageFile()
            if (imageFile != null) {
                uploadImage(imageFile)
            } else {
                showToast("Choose or capture an image first")
            }
        }
        binding.btnListOfDiseases.setOnClickListener{
            Log.d("Dashboard", "Button clicked: Navigating to ListDisease")
            val intent = Intent(requireContext(), ListDiseases::class.java)
            startActivity(intent)
        }
        binding.btnListVirus.setOnClickListener{
            Log.d("Dashboard", "Button clicked: Navigating to ListVirus")
            val intent = Intent(requireContext(), ListVirus::class.java)
            startActivity(intent)
        }
    }

    private fun getImageFile(): File? {
        val file = capturedImageFile ?: selectedImageUri?.let { uri -> getFileFromUri(uri) }
        if (file == null || !file.exists()) {
            Log.e("Dashboard", "Image file is null or does not exist!")
            showToast("Invalid image file. Please select or capture a valid image.")
            return null
        }
        return file
    }

    private fun uploadImage(file: File) {
        val mimeType = requireContext().contentResolver.getType(Uri.fromFile(file)) ?: "image/jpeg"
        val requestFile = RequestBody.create(mimeType.toMediaTypeOrNull(), file)
        val body = MultipartBody.Part.createFormData("image", file.name, requestFile)

        Log.d("Dashboard", "Uploading file: ${file.path}, Size: ${file.length()} bytes")
        if (!file.exists() || file.length() == 0L) {
            showToast("Selected file is invalid or empty.")
            return
        }

        val apiService = ApiConfig.getPredictApiService()

        binding.progressBar.visibility = View.VISIBLE

        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val response = apiService.predict(body)
                withContext(Dispatchers.Main) {
                    navigateToResult(response)
                }
            } catch (e: Exception) {
                Log.e("Dashboard", "Error uploading image: ${e.localizedMessage}")
                withContext(Dispatchers.Main) {
                    showToast("Prediction failed: ${e.message}")
                }
            } finally {
                withContext(Dispatchers.Main) {
                    binding.progressBar.visibility = View.GONE
                }
            }
        }
    }

    private fun navigateToResult(response: PredictResponse) {
        val confidenceValue = response.confidence.replace("%", "").toFloatOrNull() ?: 0f
        val intent = Intent(requireContext(), ResultActivity::class.java).apply {
            putExtra("Disease_NAME", response.label)
            putExtra("CONFIDENCE", confidenceValue)
            putExtra("IMAGE_URL", response.imageUrl)
        }
        startActivity(intent)
    }

    private fun getFileFromUri(uri: Uri): File? {
        val inputStream = requireContext().contentResolver.openInputStream(uri) ?: return null
        val file = File(requireContext().cacheDir, "selected_image.jpg")
        return try {
            FileOutputStream(file).use { out ->
                inputStream.copyTo(out)
            }
            file
        } catch (e: IOException) {
            Log.e("Dashboard", "Error getting file from URI: ${e.message}")
            null
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

