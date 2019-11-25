import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginRequest } from './LoginRequest';
import { AuthService } from '..//auth/auth.service';

@Component({
	selector: 'app-login',
	templateUrl: './login.component.html',
	styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

	loginForm: FormGroup;
	request: LoginRequest;
	error: boolean;

	constructor(private formBuilder: FormBuilder, private authService: AuthService, private router: Router) {
		this.request = {
			username: '',
			password: ''
		};
	}

	ngOnInit() {
		this.loginForm = this.formBuilder.group({
			username: ['', Validators.required],
			password: ['', Validators.required]
		});
	}

	login() {
		this.request.username = this.loginForm.get('username').value;
		this.request.password = this.loginForm.get('password').value;

		this.authService.login(this.request).subscribe((result) => {
			if (result) {
				this.error = false;
				this.router.navigateByUrl('/');
			} else {
				this.error = true;
			}
		}, () => {this.error = true;})
	}

}
