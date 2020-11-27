package ru.geekbrains.githubclient.lesson4


import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
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

    @RequiresApi(Build.VERSION_CODES.P)
    fun convert(imagePath: Uri, imagesRoot: String): ConnectableObservable<Int> =
            Observable.create<Int> { emitter ->

                val file = File(IMAGE_FILE)
                file.mkdirs()
                val result = String.format("%s%s/%s", imagesRoot, IMAGE_FILE, imagePath.getName())
                val out = FileOutputStream(File(result))

                imagePath.getCapturedImage()?.compress(Bitmap.CompressFormat.PNG, 100, out)?:
                emitter.onError(Throwable("Картинка ERROR"))
                emitter.onNext(2)
                convImage = result.toUri()
                out.close()
                emitter.onComplete()

            }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .publish()

}

