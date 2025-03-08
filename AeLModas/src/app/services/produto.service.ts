import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ProdutoModel } from '../models/ProdutoModel.model';

@Injectable({
  providedIn: 'root'
})
export class ProdutoService {

  urlProdutos = 'http://localhost:8099/sitelojaaelmodas/produto';

  constructor(
    private http: HttpClient
  ) { }

  putAtualizarProdutoPorId(produto: ProdutoModel): Observable<ProdutoModel> {
    if (!produto.id || produto.id === 0 ||
      !produto.id === null || !produto.id === undefined ||
      Number.isNaN(produto.id) || produto.id === undefined
    ) {
      console.error('O ID do produto não pode ser nulo ou zero.');
      return this.http.put<ProdutoModel>(`${this.urlProdutos}/atualizarProdutoPorId`, produto);
    }
    return this.http.put<ProdutoModel>(`${this.urlProdutos}/atualizarProdutoPorId`, produto);
  }

  postCadastrarProduto(produto: ProdutoModel): Observable<ProdutoModel> {
    return this.http.post<ProdutoModel>(`${this.urlProdutos}/salvarProduto`, produto);
  }

  deletarProdutoPorId(id: number): Observable<any> {
    return this.http.delete<any>(`${this.urlProdutos}/deletarProdutoPorId/${id}`);
  }

  getBuscarProdutoPorId(id: number): Observable<ProdutoModel> {
    return this.http.get<ProdutoModel>(`${this.urlProdutos}/buscarProdutoPorId/${id}`);
  }

  getBuscarTodosOsProdutos(): Observable<ProdutoModel[]> {
    return this.http.get<ProdutoModel[]>(`${this.urlProdutos}/buscarTodosOsProdutos`);
  }

}
