package com.android.lina.modernandprac

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

//	private val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
	private val viewModel: MainViewModel by viewModels()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		val model: MainViewModel by viewModels()
//		val viewModel: MainViewModel by viewModels()
//		val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
//		val viewModel = ViewModelProvider(this)[MainViewModel::class.java]


		viewModel.getAll().observe(this, Observer { todos ->
			// 이름 붙일 수 있음. 안붙이면 it
			result_text.text = todos.toString() //
		})

		add_button.setOnClickListener {
			lifecycleScope.launch(Dispatchers.IO) { // Asynctask 대신 코루틴을 사용한다.
				viewModel.insert(Todo(todo_edit.text.toString()))
			}
//			result_text.text = db.todoDao().getAll().toString() // 갱신하는부분.. 위에 LiveData로 대체함
		}
	}
}
