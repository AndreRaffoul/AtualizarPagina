import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EstoqueServService {

  urlService: string = 'http://localhost:8080/buscar';

  constructor(
    private http: HttpClient
  ) { }

  // Método para listar todos os registros
  getListarTodosProdutos(): Observable<any>{
    return this.http.get<any>(this.urlService);
  }

}
