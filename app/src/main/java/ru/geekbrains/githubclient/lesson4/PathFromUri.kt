package ru.geekbrains.githubclient.lesson4

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.OpenableColumns
import androidx.annotation.RequiresApi
import ru.geekbrains.githubclient.GithubApplication

fun Uri.getName(): String =
        GithubApplication.instance.baseContext

                .contentResolver
                .query(this, null, null, null, null)?.let { cursor ->
                    val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                    cursor.moveToFirst()
                    val fileName = cursor.getString(nameIndex)
                    cursor.close()
                    fileName.replace("jpg", "png", true)
                } ?: "result.png"


@RequiresApi(Build.VERSION_CODES.P)
fun Uri.getCapturedImage(): Bitmap? = loadImageP(GithubApplication.instance.baseContext, this)

@RequiresApi(Build.VERSION_CODES.P)
fun loadImageP(context: Context, imagePath: Uri): Bitmap? {
    val source = ImageDecoder.createSource(context.contentResolver, imagePath)
    return ImageDecoder.decodeBitmap(source)
}