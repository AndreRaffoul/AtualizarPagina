import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { map } from 'rxjs/operators';
import { environment } from '../../environments/environment';
import { EstoqueTecidoJoinModel } from '../models/EstoqueTecidoJoin.model';

@Injectable({
  providedIn: 'root'
})
export class EstoqueServService {

  //urlService: string = 'http://localhost:8099/sitelojaaelmodas/estoque-tecido-join';
  urlService: string = `${environment.apiUrl}/estoque-tecido-join`;

  constructor(
    private http: HttpClient
  ) { }

  salvarEstoque(estoque: EstoqueTecidoJoinModel): Observable<EstoqueTecidoJoinModel> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });

    return this.http.post<EstoqueTecidoJoinModel>(`${this.urlService}/salvar`, estoque, { headers })
    //.pipe( map(response => new EstoqueTecidoJoinModel(response)) );
  }

  salvarEstoqueTodos(estoque: EstoqueTecidoJoinModel): Observable<EstoqueTecidoJoinModel> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    const urlWithHeader = this.http.post(`${this.urlService}`, estoque, { headers }).subscribe();
    return this.http.post<EstoqueTecidoJoinModel>(`${urlWithHeader}/salvarTodos`, estoque).pipe(
      map((response) => new EstoqueTecidoJoinModel(response))
    );
  }

  atualizarEstoquePorId(id: number, estoque: EstoqueTecidoJoinModel): Observable<EstoqueTecidoJoinModel> {
    return this.http.put<EstoqueTecidoJoinModel>(`${this.urlService}/atualizarEstoquePorId/${id}`, estoque)
    .pipe( map((response) => new EstoqueTecidoJoinModel(response)) );
  }

  buscarEstoquePorId(id: number): Observable<EstoqueTecidoJoinModel> {
    return this.http.get<any>(`${this.urlService}/buscarPorID/${id}`).pipe(
      map((response) => new EstoqueTecidoJoinModel(response))
    );
  }

  buscarTodos(): Observable<EstoqueTecidoJoinModel[]> {
    return this.http.get<any[]>(`${this.urlService}/buscarTodos`).pipe(
      map((response) => {
        console.log("Resposta da API:", response);
        return response.map((item) => new EstoqueTecidoJoinModel(item));
      })
    );
  }

  editarEstoque(id: number): Observable<EstoqueTecidoJoinModel> {
    return this.http.get<any>(`${this.urlService}/buscarPorID/${id}`).pipe(
      map((response) => new EstoqueTecidoJoinModel(response))
    );
  }

  deletarPorId(id: number): Observable<any> {
    return this.http.delete(`${this.urlService}/deletarPorID/${id}`);
  }

}
