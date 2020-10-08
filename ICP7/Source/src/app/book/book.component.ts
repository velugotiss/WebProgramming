import {Component, OnInit, ViewChild, ElementRef, TemplateRef} from '@angular/core';
import {ApiService} from '../api.service';
import {DataSource} from '@angular/cdk/collections';
import {MatSnackBar} from '@angular/material/snack-bar';
import { Router } from '@angular/router';

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css']
})
export class BookComponent implements OnInit {

  books: any;
  displayedColumns = ['isbn', 'title', 'author', 'edit', 'delete'];
  dataSource = new BookDataSource(this.api);

  constructor(private api: ApiService, private router:Router, private _snackBar: MatSnackBar) {
  }

  ngOnInit() {
    this.getBooks();
  }

  getBooks() {
    this.api.getBooks()
    .subscribe(res => {
      console.log(res);
      this.books = res;
    }, err => {
      console.log(err);
    });
  }

  deleteBook(event,element) {
    event.stopPropagation();
    this.api.deleteBook(element._id).subscribe(res => {
      this.getBooks();
      this._snackBar.open('Deleted successfully', "Deleted",{
        duration: 5000,
        horizontalPosition: "right",
        verticalPosition: "top",
      });
    },(err => {
      console.log(err)
    }))
  }
}

export class BookDataSource extends DataSource<any> {
  constructor(private api: ApiService) {
    super();
  }

  connect() {
    return this.api.getBooks();
  }

  disconnect() {

  }
}
