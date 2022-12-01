package com.example.breakasweatui

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.provider.BaseColumns
import androidx.room.*

@Entity
data class Workout(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "reps") val reps: Int?,
    @ColumnInfo(name = "sets") val sets: Int?,
    @ColumnInfo(name = "weight") val weight: Int?,
)

@Dao
interface WorkoutDao {

    @Query("SELECT * FROM workout")
    fun getAll(): List<Workout>

    @Insert
    fun insertAll(vararg workout: Workout)

    @Delete
    fun delete(workout: Workout)

    @Update
    fun updateUsers(vararg workout: Workout)
}

@Database(entities = [Workout::class], version = 1)
abstract class WorkoutDatabase : RoomDatabase() {
    abstract fun workoutDao(): WorkoutDao
    companion object {
        private var INSTANCE: WorkoutDatabase? = null

        fun getInstance(context: Context?): WorkoutDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context!!.applicationContext,
                        WorkoutDatabase::class.java,
                        "workout_database"
                    ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
//    fun getDatabase(context: Context): WorkoutDatabase {
//        val tempInstance = INSTANCE
//        val db = Room.databaseBuilder(
//            context.applicationContext,
//            WorkoutDatabase::class.java,
//            "workout_database"
//        ).build()
//    }
}

