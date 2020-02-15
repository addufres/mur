import { Component, OnInit, Input } from '@angular/core';
import { Post } from './post.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {

	@Input() posts: Post[];

	constructor(private router: Router) { }

	ngOnInit() {
  	}
	
  	goToPost(id: Number) {
    	this.router.navigateByUrl('/view-post/' + id);
  	}

}
