import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DevedorModel } from '../models/DevedorModel.model';

@Injectable({
  providedIn: 'root'
})
export class DevedorService {

  apiDevedorUrl = 'http://localhost:8099/devedor/';

  constructor(
    private http: HttpClient
  ) { }
  
  deletarDevedorId(id: number): Observable<any> {
    return this.http.delete(this.apiDevedorUrl + 'deletarDevedor/' + id);
  }

  getTodosDevedores(): Observable<DevedorModel[]> {
    return this.http.get<DevedorModel[]>(this.apiDevedorUrl + 'buscarTodosDevedores');
  }

}
