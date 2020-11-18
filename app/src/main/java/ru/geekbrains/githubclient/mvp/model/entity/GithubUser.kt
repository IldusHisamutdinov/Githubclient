package ru.geekbrains.githubclient.mvp.model.entity;

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GithubUser(
        var login: String
) : Parcelable