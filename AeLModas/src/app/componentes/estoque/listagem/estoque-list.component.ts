import { Component, OnInit } from '@angular/core';
import { EstoqueModel } from '../../../models/EstoqueModel';
import { EstoqueServService } from '../../../services/estoque-serv.service';

@Component({
  selector: 'app-estoque-list',
  standalone: true,
  imports: [],
  templateUrl: './estoque-list.component.html',
  styleUrl: './estoque-list.component.css'
})
export class EstoqueListComponent implements OnInit {

  estoque: EstoqueModel[] = [];

  constructor( 
    private estoqueServService: EstoqueServService,
) { }
  
  ngOnInit(): void {

    // this.estoqueServService.getListarTodosProdutos().subscribe(
    //   (response) => {
    //     this.estoque = response;
    //   },
    //   (error) => {
    //     console.log('Erro ao listar os produtos:', error);
    //   }
    // );

  } 

}
