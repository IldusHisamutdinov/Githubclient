package ru.geekbrains.githubclient.mvp.model.entity.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomGithubUser(
        @PrimaryKey
        var id: String,
        @ColumnInfo
        var login: String,
        @ColumnInfo
        var avatarUrl: String,
        @ColumnInfo
        var reposUrl: String
)