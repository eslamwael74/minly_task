package com.eslamwael74.minlyTask

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.eslamwael74.minlyTask.databinding.ActivityMainBinding
import com.eslamwael74.minlyTask.ui.ImagesAdapter
import com.eslamwael74.minlyTask.ui.ImagesViewModel
import com.eslamwael74.minlyTask.utils.FileUtils
import com.eslamwael74.minlyTask.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val PICK_IMAGE_CODE = 100
    private val ATTACH_IMG_REQUEST = 14

    private lateinit var binding: ActivityMainBinding
    private val viewModel: ImagesViewModel by viewModels()
    private lateinit var adapter: ImagesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getImages()

        initView()
        initObservers()
    }

    /**
     * init views
     */
    private fun initView() {
        adapter = ImagesAdapter()
        binding.recyclerView.adapter = adapter

        binding.fabAdd.setOnClickListener { openImgPicker() }
    }

    /**
     * Check on storage permission
     * to open image picker and upload photo
     */
    private fun openImgPicker() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            startForResult.launch(
                Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                )
            )
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                ATTACH_IMG_REQUEST
            )
        }
    }

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
                intent?.data?.let {
                    val imageFile = FileUtils.uriToImageFile(it, contentResolver)
                    imageFile?.let { it1 -> viewModel.uploadImage(it1) }
                }
            }
        }


    private fun initObservers() {
        viewModel.images.observe(this, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) {
                        binding.tvNoImg.visibility = View.INVISIBLE
                        adapter.setItems(ArrayList(it.data))
                    } else
                        binding.tvNoImg.visibility = View.VISIBLE
                }
                Resource.Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
                Resource.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        })
        viewModel.uploadImage.observe(this, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    viewModel.getImages()
                }
                Resource.Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, "${it.message}", Toast.LENGTH_LONG).show()
                }
                Resource.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        })
    }


    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            when (requestCode) {
                ATTACH_IMG_REQUEST -> openImgPicker()
            }
        }
    }
}