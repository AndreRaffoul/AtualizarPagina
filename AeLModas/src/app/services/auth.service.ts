import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { AuthUser } from '../models/auth-user.models';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  // Chamando o environment para pegar a URL da API
  apiAuthUrl = `${environment.apiUrl}/auth`;
  private auth = new AuthUser(0, '', '', '', '', [])

  constructor(
    private http: HttpClient
  ) { }

  getLogin() { return this.http.get<AuthUser>(this.apiAuthUrl + '/login'); }
  getLoginId(id: number) { return this.http.get<AuthUser>(this.apiAuthUrl + '/login/' + id); }

  postLogin(auth: AuthUser) {
    return this.http.post<AuthUser>(this.apiAuthUrl + '/login', auth);
  }

  putLoginId(id: number, auth: AuthUser) {
    return this.http.put<AuthUser>(this.apiAuthUrl + '/login/' + id, auth);
  }

  deleteLoginId(id: number) {
    return this.http.delete(this.apiAuthUrl + '/login/' + id);
  }

  

}
