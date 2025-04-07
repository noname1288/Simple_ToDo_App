package com.example.simpletodo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simpletodo.MainApplication
import com.example.simpletodo.database.entity.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Date

class TodoViewModel : ViewModel() {
//    private val _todoList = MutableLiveData<List<Todo>>()

    val todoDao = MainApplication.instanceDB.getTodoDao()
    val todoList: LiveData<List<Todo>> = todoDao.getAllTodo()

//    fun getAllTodo(){
//        _todoList.value = TodoManager.getAllTodo().reversed()
//    }

    fun addTodo(title: String) {
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.addTodo(Todo(title = title, createdAt = Date.from(Instant.now())))

        }
    }

    fun deleteTodo(id : Int) {
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.deleteTodo(id)
        }
    }




}