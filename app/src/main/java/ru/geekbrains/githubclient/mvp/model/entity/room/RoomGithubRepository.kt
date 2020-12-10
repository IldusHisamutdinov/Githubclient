package ru.geekbrains.githubclient.mvp.model.entity.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
        foreignKeys = [ForeignKey(
                entity = RoomGithubUser::class,
                parentColumns = ["id"],
                childColumns = ["userId"],
                onDelete = ForeignKey.CASCADE
        )]
)

data class RoomGithubRepository(
        @PrimaryKey
        var id: String,
        @ColumnInfo
        var name: String,
        @ColumnInfo
        var forks: Int,
        @ColumnInfo
        var userId: String? = null,
        @ColumnInfo
        var description: String
)