package ru.geekbrains.githubclient.lesson4

import android.content.ContentValues.TAG
import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.annotation.RequiresApi
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.observables.ConnectableObservable
import moxy.MvpPresenter

class Presenter(private val model: Model): MvpPresenter<View>() {

    @RequiresApi(Build.VERSION_CODES.P)
    private fun getCapturedImage(context: Context, imagePath: Uri): Bitmap?
            = loadImageP(context, imagePath)

    @RequiresApi(Build.VERSION_CODES.P)
    fun loadImageP(context: Context, imagePath: Uri): Bitmap? {
        val source = ImageDecoder.createSource(context.contentResolver, imagePath)
        return ImageDecoder.decodeBitmap(source)
    }

    @RequiresApi(Build.VERSION_CODES.P)
    fun convertImage(context: Context, imagePath: Uri) {
        val bitmap = getCapturedImage(context, imagePath)

        bitmap?.also { originalBitmap ->
            val root = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            root?.also { imagesRoot ->
                val hotObservable: ConnectableObservable<Int> = model.convert(
                        originalBitmap,
                        imagesRoot.toString(),
                        imagePath.getName(context)
                )
                hotObservable.subscribe(object : Observer<Int> {
                    override fun onSubscribe(@NonNull d: @NonNull Disposable?) {

                        viewState.runConvert(imagePath)
                        Log.i(TAG, "runConvert")
                    }

                    override fun onNext(t: Int) {
                        model.convImage?.run {
                            viewState.finConvert(this)
                        }
                    }

                    override fun onError(e: Throwable?) {
                       e.toString()
                    }

                    override fun onComplete() {
                        Log.i(TAG, "onComplete ")

                    }
                }
                )
                hotObservable.connect()
            }
        }

    }

}