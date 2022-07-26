package me.ctossato.businesscard.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BusinessCardDao {
    @Query("SELECT * FROM BusinessCard")
    fun getAll(): LiveData<List<BusinessCard>>

    @Query("SELECT * FROM BusinessCard WHERE (name)")
    fun getByFields(): LiveData<List<BusinessCard>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(businessCard: BusinessCard)

    @Delete
    fun delete(businessCard: BusinessCard)
}