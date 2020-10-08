import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {MatSnackBar} from '@angular/material/snack-bar';
import {ApiService} from '../api.service';

@Component({
  selector: 'app-book-detail',
  templateUrl: './book-detail.component.html',
  styleUrls: ['./book-detail.component.css']
})
export class BookDetailComponent implements OnInit {

  book = {};

  constructor(private route: ActivatedRoute, private api: ApiService, private router: Router, private _snackBar: MatSnackBar) {
  }

  ngOnInit() {
    this.getBookDetails(this.route.snapshot.params['id']);
    console.log(this.route.snapshot.queryParams);
    if(this.route.snapshot.queryParams['updated']) {
        this._snackBar.open('Updated successfully', "Success",{
          duration: 5000,
          horizontalPosition: "right",
          verticalPosition: "top",
        });
    }
  }

  getBookDetails(id) {
    this.api.getBook(id)
      .subscribe(data => {
        console.log(data);
        this.book = data;
      });
  }

  deleteBook(id) {
    this.api.deleteBook(id)
      .subscribe(res => {
          this.router.navigate(['/books']);
        }, (err) => {
          console.log(err);
        }
      );
  }

}
