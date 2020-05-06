package com.android.lina.modernandprac

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodoDao {
	@Query("SELECT * FROM Todo")
	fun getAll(): LiveData<List<Todo>> // 관찰하고자 하는 객체를 LiveData<>로 감쌈

	@Insert
	fun insert(todo: Todo)

	@Update
	fun update(todo: Todo)

	@Delete
	fun delete(todo: Todo)
}