import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { faUser } from '@fortawesome/free-solid-svg-icons';
import { AuthService } from '../auth/auth.service';

@Component({
	selector: 'app-header',
	templateUrl: './header.component.html',
	styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

	faUser = faUser;
	userStatus: Boolean;
	username: string;
	logo: string;
	constructor(private router: Router, private authService: AuthService) {
		this.authService.userLoggedIn.subscribe(flag => this.userStatus = flag);
		this.authService.username.subscribe(name => this.username = name);
		this.logo = "../../assets/img/MU2.jpg";
	}

	ngOnInit() {
		this.userStatus = this.authService.isLoggedIn();
		this.username = this.authService.getUserName();
	}

	logout() {
		this.authService.logout();
		this.router.navigateByUrl('').then(() => {
			window.location.reload();
		})
	}

	gotoProfile() {
		this.router.navigateByUrl('/user/' + this.username);
	}

}
