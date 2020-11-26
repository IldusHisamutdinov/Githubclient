package ru.geekbrains.githubclient.lesson4


import android.graphics.Bitmap
import android.net.Uri
import androidx.core.net.toUri
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.observables.ConnectableObservable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.File
import java.io.FileOutputStream

object Model {

    private val IMAGE_FILE = ""
    var convImage: Uri? = null

    fun convert(bitmap: Bitmap, fileImg: String, fileName: String): ConnectableObservable<Int> =
            Observable.create<Int> { emitter ->

                val file = File(IMAGE_FILE)//fileImg +
                file.mkdirs()
                val result = String.format("%s%s/%s", IMAGE_FILE, fileImg, fileName)
                val out = FileOutputStream(File(result))
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
                emitter.onNext(2)
                convImage = result.toUri()
                out.close()
                emitter.onComplete()

            }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .publish()

}

