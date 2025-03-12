import { HttpEvent, HttpHandler, HttpInterceptor,
  HttpRequest, } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({providedIn: 'root'})
export class AuthInterceptorService implements HttpInterceptor {
  constructor() { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // Obtém o token armazenado no LocalStorage ou SessionStorage
    const token = localStorage.getItem('token'); // ou sessionStorage.getItem('token');

    if (token) {// Clona a requisição e adiciona o cabeçalho de autorização
      const clonedRequest = request.clone({
        setHeaders: { Authorization: `Bearer ${token}` }
      });
      return next.handle(clonedRequest);
    }
    return next.handle(request);
  }
}
