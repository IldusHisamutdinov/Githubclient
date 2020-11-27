package ru.geekbrains.githubclient.lesson4

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.OpenableColumns
import kotlin.jvm.internal.Intrinsics

fun Uri.getName(context: Context): String =
        context
                .contentResolver
                .query(this, null, null, null, null)?.let { cursor ->
                    val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                    cursor.moveToFirst()
                    val fileName = cursor.getString(nameIndex)
                    cursor.close()
                    fileName.replace("jpg", "png", true)
                } ?: "result.png"

