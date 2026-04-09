import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { KeycloakService } from 'keycloak-angular';
import { from } from 'rxjs';
import { mergeMap, tap } from 'rxjs/operators';

@Injectable()
export class XhrInterceptor implements HttpInterceptor {

  constructor(private router: Router, private keycloak: KeycloakService) {}

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    return from(this.keycloak.getToken()).pipe(
      mergeMap((token: string | null) => {
        let headers = req.headers.set('X-Requested-With', 'XMLHttpRequest');

        if (token) {
          headers = headers.set('Authorization', `Bearer ${token}`);
        } else {
          const fallback = sessionStorage.getItem('Authorization');
          if (fallback) {
            headers = headers.set('Authorization', fallback);
          }
        }

        const xsrf = sessionStorage.getItem('XSRF-TOKEN');
        if (xsrf) {
          headers = headers.set('X-XSRF-TOKEN', xsrf);
        }

        const xhr = req.clone({ headers });

        return next.handle(xhr).pipe(
          tap({
            error: (err: any) => {
              if (err instanceof HttpErrorResponse && err.status === 401) {
                this.router.navigate(['login']);
              }
            }
          })
        );
      })
    );
  }
}