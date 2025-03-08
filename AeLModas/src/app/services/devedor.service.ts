import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DevedorModel } from '../models/DevedorModel.model';

@Injectable({
  providedIn: 'root'
})
export class DevedorService {

  apiDevedorUrl = 'http://localhost:8099/sitelojaaelmodas/devedor/';

  constructor(
    private http: HttpClient
  ) { }

  putDevedorPorId(id: number, devedor: DevedorModel): Observable<DevedorModel> {
    return this.http.put<DevedorModel>(this.apiDevedorUrl + 'atualizarDevedor/' + id, devedor);
  }

  saveDevedor(devedor: DevedorModel): Observable<DevedorModel> {
    return this.http.post<DevedorModel>(this.apiDevedorUrl + 'salvarDevedor', devedor);
  }

  getDevedorPorId(id: number): Observable<DevedorModel> {
    return this.http.get<DevedorModel>(this.apiDevedorUrl + 'buscarDevedorPorId/' + id);
  }

  deletarDevedorId(id: number): Observable<any> {
    return this.http.delete(this.apiDevedorUrl + 'deletarDevedor/' + id);
  }

  getTodosDevedores(): Observable<DevedorModel[]> {
    return this.http.get<DevedorModel[]>(this.apiDevedorUrl + 'buscarTodosDevedores');
  }

}
