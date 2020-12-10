package ru.geekbrains.githubclient.lesson4

import android.net.Uri
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface View: MvpView {
    fun runConvert(imagePath: Uri)
    fun finConvert(imagePath: Uri)

}