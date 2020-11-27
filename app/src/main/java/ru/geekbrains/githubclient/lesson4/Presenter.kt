package ru.geekbrains.githubclient.lesson4


import android.net.Uri

import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.observables.ConnectableObservable
import moxy.MvpPresenter

class Presenter(private val model: Model): MvpPresenter<View>() {

    fun convertImage(imagePath: Uri, imagesRoot: String) {

                val hotObservable: ConnectableObservable<Int> = model.convert(

                        imagePath,  // ссылка на картинку jpg
                        imagesRoot// путь к папке готового png

                )
                hotObservable.subscribe(object : Observer<Int> {
                    override fun onSubscribe(d: Disposable?) {
                        viewState.runConvert(imagePath)
                    }

                    override fun onNext(t: Int) {
                        //progressbar
                    }

                    override fun onError(e: Throwable?) {
                       e.toString()
                    }

                    override fun onComplete() {
                        model.convImage?.run {
                            viewState.finConvert(this)
                        }
                    }
                }
                )
                hotObservable.connect()
            }

        }
