package ru.geekbrains.githubclient.di.module

import androidx.room.Database
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.geekbrains.githubclient.GithubApplication
import ru.geekbrains.githubclient.mvp.model.cache.IGithubUsersCache
import ru.geekbrains.githubclient.mvp.model.cache.IReposUserCache
import ru.geekbrains.githubclient.mvp.model.cache.room.RoomGithubUsersCache
import ru.geekbrains.githubclient.mvp.model.cache.room.RoomRepositoriesCache
import ru.geekbrains.githubclient.mvp.model.entity.room.GithubDatabase
import javax.inject.Singleton

@Module
class CacheModule {

    @Singleton
    @Provides
    fun database(): GithubDatabase = Room.databaseBuilder(
                GithubApplication.instance,
                GithubDatabase::class.java,
                GithubDatabase.DB_NAME)
                .build()

    @Singleton
    @Provides
    fun usersCache(database:GithubDatabase): IGithubUsersCache = RoomGithubUsersCache(database)

    @Singleton
    @Provides
    fun repoCache(database:GithubDatabase): IReposUserCache = RoomRepositoriesCache(database)

}