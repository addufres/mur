import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AuthService } from '../auth/auth.service';
import { Router } from '@angular/router';
import { RegisterRequest } from './RegisterRequest';

@Component({
	selector: 'app-register',
	templateUrl: './register.component.html',
	styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

	registerForm: FormGroup;
	request: RegisterRequest;
	constructor(private formBuilder: FormBuilder, private authService: AuthService, private router: Router) {
		this.request = {
			email: '',
			username: '',
			password: ''
		}
	}

	ngOnInit() {
		this.registerForm = this.formBuilder.group({
			email: ['', Validators.required],
			username: ['', Validators.required],
			password: ['', Validators.required],
		})
	}

	newUser() {
		this.request.email = this.registerForm.get('email').value;
		this.request.username = this.registerForm.get('username').value;
		this.request.password = this.registerForm.get('password').value;

		this.authService.register(this.request).subscribe(data => {
			this.router.navigateByUrl('/login');
		}, error => {
			console.log("could not create user: " + error);
		});
	}
}
