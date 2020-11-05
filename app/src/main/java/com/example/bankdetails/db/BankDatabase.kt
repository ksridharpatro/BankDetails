package com.example.bankdetails.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bankdetails.models.BankBranch

@Database(
    entities = [BankBranch::class],
    version = 1
)
abstract class BankDatabase : RoomDatabase() {

    abstract fun getBankDao(): BankDao

    companion object {
        private var instance: BankDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                BankDatabase::class.java,
                "bank_db.db"
            ).build()
    }
}