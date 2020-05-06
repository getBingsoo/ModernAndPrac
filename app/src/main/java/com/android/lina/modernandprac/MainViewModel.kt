package com.android.lina.modernandprac

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room

// ViewModel VS AndroidViewModel?
// AndroidViewModel을 상속받으면 context를 받을 수 있음
class MainViewModel(application: Application) : AndroidViewModel(application) {

	private val db = Room.databaseBuilder(
		application,
		AppDatabase::class.java, "database-name"
	)
//			.allowMainThreadQueries()  // 메인쓰레드에서 원래는 디비 하면 성능이 안좋아짐. 화면그리는거 느려지거나 .. 그런식이므로 백그라운드 스레드에서 동작시켜야 함.
		// 자바에서는 Asynctask가 가장 낫고, 코틀린에서는 코루틴이 좋음
		.build()

	fun getAll(): LiveData<List<Todo>> {
		return db.todoDao().getAll()
	}

	// suspend: 코루틴 안에 처리 안하면 오류.. 빨간불 나게 하기 위함. 무조건 코루틴 안으로 넣게끔 하기 위해서..
	suspend fun insert(todo: Todo) {
		db.todoDao().insert(todo)
	}
}