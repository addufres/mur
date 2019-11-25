import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { CookieService } from 'ngx-cookie-service';
import { HttpClientInterceptor } from './http.client.interceptor';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { EditorModule } from '@tinymce/tinymce-angular';
import { HeaderComponent } from './header/header.component';
import { HomeComponent } from './home/home.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UserComponent } from './user/user.component';

@NgModule({
	declarations: [
		AppComponent,
		LoginComponent,
		RegisterComponent,
		HeaderComponent,
		HomeComponent,
		UserComponent
	],
	imports: [
		BrowserModule,
		AppRoutingModule,
		RouterModule.forRoot([
			{ path: '', component: HomeComponent },
			{ path: 'login', component: LoginComponent },
			{ path: 'signup', component: RegisterComponent }
		], { onSameUrlNavigation: "reload" }),
		FontAwesomeModule,
		NgbModule,
		EditorModule,
		HttpClientModule,
		FormsModule,
		ReactiveFormsModule

	],
	providers: [CookieService, { provide: HTTP_INTERCEPTORS, useClass: HttpClientInterceptor, multi: true }],
	bootstrap: [AppComponent]
})
export class AppModule { }
