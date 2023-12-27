package com.jhbb.core_data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jhbb.core_data.database.entity.RegisterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RegisterDao {
    @Query("SELECT * FROM register")
    fun selectRegister(): Flow<List<RegisterEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRegister(register: RegisterEntity)
}