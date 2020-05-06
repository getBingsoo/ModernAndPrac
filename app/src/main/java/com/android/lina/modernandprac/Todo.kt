package com.android.lina.modernandprac

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
	var title: String
) // 생성자에 넣어줌
{
	@PrimaryKey(autoGenerate = true) var id: Int = 0 // 초기값 지정
}