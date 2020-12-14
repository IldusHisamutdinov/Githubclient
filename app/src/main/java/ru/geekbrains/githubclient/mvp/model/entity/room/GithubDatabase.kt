package ru.geekbrains.githubclient.mvp.model.entity.room


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized
import ru.geekbrains.githubclient.GithubApplication
import ru.geekbrains.githubclient.mvp.model.entity.room.dao.RepositoryDao
import ru.geekbrains.githubclient.mvp.model.entity.room.dao.UserDao


@Database(entities = [RoomGithubUser::class, RoomGithubRepository::class],
        version = 1)
abstract class GithubDatabase : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val repositoryDao: RepositoryDao

    companion object {
        private const val DB_NAME = "database.db"
        var instance: GithubDatabase? = null
        fun newInstance() = instance ?: RuntimeException("База данных не создана!")
        fun create(context: Context) {
            if (instance == null) {
                instance = Room.databaseBuilder(
                        context.applicationContext,
                        GithubDatabase::class.java,
                        DB_NAME)
                        .build()
            }
        }

    }
}