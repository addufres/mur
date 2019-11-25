import { Injectable, Output, EventEmitter } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { CookieService } from 'ngx-cookie-service';
import { RegisterRequest } from '../register/RegisterRequest';
import { LoginRequest } from '../login/LoginRequest';
import { AuthResponse } from './AuthResponse';


@Injectable({
	providedIn: 'root'
})
export class AuthService {

	@Output() userLoggedIn: EventEmitter<Boolean> = new EventEmitter();
	@Output() username: EventEmitter<string> = new EventEmitter();
	private baseUrl = 'http://localhost:8080/api/auth';
	private loginUrl: String = '/login';
	private regUrl: String = '/signup';

	constructor(private http: HttpClient, private cookieService: CookieService) { }

	register(request: RegisterRequest): Observable<any> {
		return this.http.post(this.baseUrl + this.regUrl, request);
	}

	login(request: LoginRequest): Observable<boolean> {
		return this.http.post<AuthResponse>(this.baseUrl + this.loginUrl, request).pipe(map(data => {
			this.cookieService.set('authenticationToken', data.authenticationToken);
			this.cookieService.set('user', data.username);
			this.userLoggedIn.emit(true);
			this.username.emit(data.username);
			return true;
		}));
	}

	logout() {
		this.cookieService.delete('authenticationToken');
		this.cookieService.delete('user');
		this.userLoggedIn.emit(false);
		this.username.emit();
	}

	getUserName(): string {
		return this.cookieService.get('user');
	}

	isLoggedIn(): Boolean {
		return this.cookieService.check('user');
	}
}
