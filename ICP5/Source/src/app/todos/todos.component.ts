import { Component, OnInit } from '@angular/core';
import {Todo} from './todo';

@Component({
  selector: 'app-todos',
  templateUrl: './todos.component.html',
  styleUrls: ['./todos.component.css']
})
export class TodosComponent implements OnInit {
  public todos: Todo[] = [];
  public todoName: String;
  public editingTodo: Todo = null;
  public error: Boolean = false;
  constructor() { }

  ngOnInit(): void {
    
  }

  addTodo() {
    if(this.todoName) {
    if (this.editingTodo && this.editingTodo['id']) {
      const todo = {
        ...this.editingTodo,
        name: this.todoName
      }
      const index = this.todos.findIndex(t => t.id === todo['id'])
      this.todos[index] = todo
    } else {
      this.todos.push({
        id: Math.random() * 10000,
        name: this.todoName,
        completed: false
      })
    }
    this.todoName = ''
    this.editingTodo = null;
    this.error = false;
  } else {
    this.error = true;
    this.editingTodo = null;
  }

  }

  toggleTodo(index) {
    console.log(index)
    const todo = this.todos[index];
    todo['completed'] = !todo['completed'];
    this.todos[index]= todo;
  }

  editTodo(index) {
    const todo = this.todos[index];
    this.todoName = todo['name'];
    this.editingTodo = todo;
  }

  deleteTodo(index) {
    console.log(index)
    this.todos.splice(index,1)
  }
}
