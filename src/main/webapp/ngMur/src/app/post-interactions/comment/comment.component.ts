import { Component, OnInit, Input} from '@angular/core';
import { CommentRequest } from './commentRequest';

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.css']
})
export class CommentComponent implements OnInit {

	@Input() comments: CommentRequest[];
  	
	constructor() { }

  	ngOnInit() {
  	}

}
