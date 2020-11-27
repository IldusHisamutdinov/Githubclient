package ru.geekbrains.githubclient.lesson4

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_lesson4.*
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.geekbrains.githubclient.R

class ActivityLesson4 : MvpAppCompatActivity(), View {
    private val REQUEST_CODE = 3
    private val REQUEST_PERMISSION_CODE = 7
    private val REQUESTED_PERMISSION = Manifest.permission.WRITE_EXTERNAL_STORAGE

    private fun checkPermission() =
            ContextCompat.checkSelfPermission(this, REQUESTED_PERMISSION) == PackageManager.PERMISSION_GRANTED

    private fun requestPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(REQUESTED_PERMISSION), REQUEST_PERMISSION_CODE)
    }

    private val presenter: Presenter by moxyPresenter {
        Presenter(Model)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson4)

        button.isEnabled = checkPermission()
        if (!button.isEnabled) {
            requestPermission()
        }

        button.setOnClickListener {
            pickImage()
        }
    }


    private fun pickImage() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_CODE)
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onActivityResult(requestCode: Int, resultCode: Int, resdata: Intent?) {
        super.onActivityResult(requestCode, resultCode, resdata)
        if (requestCode != REQUEST_CODE || resultCode != RESULT_OK)
            return

        resdata?.run {
            data?.apply {
                presenter.convertImage(this@ActivityLesson4, this)

            }
        }
    }


    override fun runConvert(imagePath: Uri) {
        imageJpg.setImageURI(imagePath)

    }

    override fun finConvert(imagePath: Uri) {
        imagePng.setImageURI(imagePath)
    }


}