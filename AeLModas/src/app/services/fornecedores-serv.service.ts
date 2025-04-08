import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { environment } from '../../environments/environment';
import { FornecedoresModel } from '../models/FornecedoresModel';

@Injectable({
  providedIn: 'root'
})
export class FornecedoresServService {

  //private urlFornecedor = 'http://localhost:8099/sitelojaaelmodas/fornecedor';

  private urlFornecedor = `${environment.apiUrl}/fornecedor`;

  constructor(
    private http: HttpClient
  ) { }

  salvarFornecedor(fornecedor: FornecedoresModel): Observable<FornecedoresModel> {
    return this.http.post<FornecedoresModel>(this.urlFornecedor + "/salvar", fornecedor);
  }

  buscarTodosFornecedores(): Observable<FornecedoresModel[]> {
    return this.http.get<FornecedoresModel[]>(`${this.urlFornecedor}/buscarTodos`);
  }

  buscarFornecedorPorId(id: number): Observable<FornecedoresModel> {
    return this.http.get<FornecedoresModel>(this.urlFornecedor + '/buscarPorId/' + id);
  }

  atualizarFornecedorPorId(fornecedor: FornecedoresModel): Observable<any> {
    if (!fornecedor.id || fornecedor.id === 0 ||
      fornecedor.id === undefined || fornecedor.id === null ||
      Number.isNaN(fornecedor.id)
    ) {
      console.error("Erro: ID do fornecedor está indefinido!");
      return new Observable<FornecedoresModel>();
    }

    const url = `${this.urlFornecedor}/atualizar/${fornecedor.id}`;
    console.log("Chamando API com URL:", url);

    return this.http.put<FornecedoresModel>(url, fornecedor);
  }

  deletarFornecedorPorId(id: number): Observable<FornecedoresModel> {
    return this.http.delete<FornecedoresModel>(this.urlFornecedor + '/deletar/' + id);
  }

}
