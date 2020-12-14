package ru.geekbrains.githubclient.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ReposGithubUser(
        @Expose
        val id: String = "",
        @Expose
        val name: String = "",
        @Expose
        val forks: Int = 0,
        @Expose
        val userId: String = "",
        @Expose
        val description: String = ""
) : Parcelable {
}

