import {Component, OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {ApiService} from '../api.service';
import {FormControl, FormGroupDirective, FormBuilder, FormGroup, NgForm, Validators} from '@angular/forms';

@Component({
  selector: 'app-book-edit',
  templateUrl: './book-edit.component.html',
  styleUrls: ['./book-edit.component.css']
})
export class BookEditComponent implements OnInit {
  bookForm: FormGroup;
  public bookId;
  isbn: string = '';
  title: string = '';
  description: string = '';
  author: string = '';
  publisher: string = '';
  published_year: string = '';

  constructor(private router: Router, private route: ActivatedRoute, private api: ApiService, private formBuilder: FormBuilder) {
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
     this.bookId = params['id'];
     this.api.getBook(this.bookId).subscribe(data => {
      this.bookForm.setValue({
        isbn: data.isbn,
        title: data.title,
        author: data.author,
        publisher: data.publisher,
        published_year: data.published_year,
        description: data.description
      })
     }) 
    });
    this.bookForm = this.formBuilder.group({
      'isbn': [null, Validators.required],
      'title': [null, Validators.required],
      'description': [null, Validators.required],
      'author': [null, Validators.required],
      'publisher': [null, Validators.required],
      'published_year': [null, Validators.required]
    });
  }

  onUpdateBook(form: NgForm) {
    this.api.updateBook(this.bookId, form)
      .subscribe(res => {
        let id = res['_id'];
        this.router.navigate(['/book-details', id],{ queryParams: { updated: true }});
      }, (err) => {
        console.log(err);
      });
  }

}
