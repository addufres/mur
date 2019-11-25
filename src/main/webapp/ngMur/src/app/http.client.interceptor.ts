import { Injectable, Injector } from "@angular/core";
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpEventType, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { CookieService } from 'ngx-cookie-service';

@Injectable({
	providedIn: 'root'
})
export class HttpClientInterceptor implements HttpInterceptor {

	constructor(private injector: Injector, private cookieService: CookieService) {

	}

	intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
		const jwtToken = this.cookieService.get('authenticationToken');
		console.log('jwt token ' + jwtToken);
		if (jwtToken) {
			const cloned = req.clone({
				headers: req.headers.set("Authorization",
					"Bearer " + jwtToken)
			});

			return next.handle(cloned);
		} else {
			return next.handle(req);
		}
	}

}