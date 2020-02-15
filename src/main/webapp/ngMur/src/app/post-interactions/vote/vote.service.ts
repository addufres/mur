import { Injectable, Output, EventEmitter } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Vote } from './Vote';
import { Post } from '../post/post.model';

@Injectable({
  providedIn: 'root'
})
export class VoteService {

	@Output() postChange: EventEmitter<Post> = new EventEmitter<Post>(); 
	private baseUrl: string = 'http://localhost:8080/api/votes/';   
  	
	constructor(private http: HttpClient) { }
	
	vote(request: Vote): void {
		this.http.post<Post>(this.baseUrl, request).subscribe(data => {
			this.postChange.emit(data);	
		}, error => {
			console.log(error);
		});
	}
}
