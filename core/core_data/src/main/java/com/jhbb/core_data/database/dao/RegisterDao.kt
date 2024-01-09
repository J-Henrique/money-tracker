package com.jhbb.core_data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.jhbb.core_data.database.entity.RegisterEntity
import com.jhbb.core_domain.model.CategoryType
import kotlinx.coroutines.flow.Flow

@Dao
interface RegisterDao {
    @Query("SELECT * FROM register")
    fun getAllRegisters(): Flow<List<RegisterEntity>>

    @Query("SELECT * FROM register WHERE register_category_type IN (:filter)")
    fun filterRegisterByCategory(filter: List<CategoryType>): Flow<List<RegisterEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRegister(register: RegisterEntity)

    @Update
    suspend fun updateRegister(register: RegisterEntity)
}