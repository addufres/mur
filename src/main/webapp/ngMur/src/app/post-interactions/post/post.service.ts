import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Post } from './post.model';
import { Observable } from 'rxjs';
import { CommentRequest } from '../comment/commentRequest';
import { CreatePostPayload } from '../createPost/CreatePostRequest';

@Injectable({
  providedIn: 'root'
})
export class PostService {

		private baseUrl: string = 'http://localhost:8080/api/';
		private posts: string = 'posts/';
		private comments: string = 'comments/';
		private user: string = 'user/';
		private create: string = 'create';
		private query: string = 'query/';
		
	
		constructor(private http: HttpClient) { }

 		getPost(postId: Number): Observable<Post> {
			return this.http.get<Post>(this.baseUrl+this.posts+this.query+postId);
		}

		getAllPosts(): Observable<Post[]> {
	    	return this.http.get<Post[]>(this.baseUrl+this.posts+this.query+'all');
  		}	

	  	createPost(postPayload: CreatePostPayload): Observable<any> {
	   		return this.http.post(this.baseUrl+this.posts+this.create, postPayload);
	  	}

	  	postComment(request: CommentRequest): Observable<any> {
	    	return this.http.post<any>(this.baseUrl+this.comments+this.create, request);
	  	}

	  	getAllComments(postId: Number): Observable<CommentRequest[]> {
 	   		return this.http.get<CommentRequest[]>(this.baseUrl+this.comments+this.query+postId);
 	 	}
	
	  	getAllPostsByThread(threadId: Number): Observable<Post[]> {
 	   		return this.http.get<Post[]>(this.baseUrl+this.posts+this.query+'all/thread/' + threadId);
 	 	}

 	 	getAllPostsByUser(name: string): Observable<Post[]> {
  	  		return this.http.get<Post[]>(this.baseUrl+this.posts+this.query+this.user+name);
  		}

  		getAllCommentsByUser(name: string): Observable<CommentRequest[]> {
    		return this.http.get<CommentRequest[]>(this.baseUrl+this.comments+this.query+this.user+name);
  		}

}
