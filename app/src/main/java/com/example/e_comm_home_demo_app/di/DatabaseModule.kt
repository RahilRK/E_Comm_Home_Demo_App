package com.example.e_comm_home_demo_app.di

import android.app.Application
import androidx.room.Room
import com.example.e_comm_home_demo_app.data.local.MyDao
import com.example.e_comm_home_demo_app.data.local.MyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application, callback: MyDatabase.Callback): MyDatabase {
        return Room.databaseBuilder(application, MyDatabase::class.java, "home_database")
            .fallbackToDestructiveMigration()
            .addCallback(callback)
            .build()
    }

    @Provides
    fun provideArticleDao(db: MyDatabase): MyDao {
        return db.getMyDao()
    }
}