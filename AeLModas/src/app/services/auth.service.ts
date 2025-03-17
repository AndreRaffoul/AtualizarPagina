import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  // Chamando o environment para pegar a URL da API
  apiAuthUrl = `${environment.apiUrl}/usuario/login`;

  // Guardando o estado do usuário
  private authStatus = new BehaviorSubject<boolean>(this.isAuthenticated());

  constructor( private http: HttpClient ) { }

  login(auth: { login: string, senha: string }): Observable<{ Authorization: string }> {
    return this.http.post<{ Authorization: string }>(this.apiAuthUrl, auth).pipe(
      tap(response => {
        if (response && response.Authorization) {
          localStorage.setItem('token', response.Authorization); // Armazena o token
          this.authStatus.next(true); // Atualiza o estado do usuário
        }
      })
    );
  }

  logout(): void {
    localStorage.removeItem('token'); // Remove o token
    this.authStatus.next(false); // Atualiza o estado do usuário
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  isAuthenticated(): boolean {
    return this.hasValidToken();
  }

  getAuthStatus(): Observable<boolean> {
    return this.authStatus.asObservable();
  }

  private hasValidToken(): boolean {
    const token = this.getToken();
    return token !== null && token !== ''; // Verifica se há token válido
  }

  // Retorna as roles do usuário
  getRoles(): string[] {
    const token = this.getToken();
    if (!token) {
      return [];
    }

    const payload = token.split('.')[1];
    const decodedPayload = atob(payload);
    const roles = JSON.parse(decodedPayload).roles;

    return roles;
  }

}
