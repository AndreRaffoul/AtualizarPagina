import { HttpClientModule } from '@angular/common/http';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { MatTableModule } from '@angular/material/table';
import { Router } from '@angular/router';
import { FornecedoresModel } from '../../../../models/FornecedoresModel';
import { FornecedoresServService } from '../../../../services/fornecedores-serv.service';

@Component({
  selector: 'app-fornecedores-list',
  standalone: true,
  imports: [
    MatListModule,
    MatIconModule,
    MatTableModule,
    HttpClientModule],
  templateUrl: './fornecedores-list.component.html',
  styleUrl: './fornecedores-list.component.css',
  providers: [FornecedoresServService]
})
export class FornecedoresListComponent implements OnInit{

  @Output() fornecedorSelecionado = new EventEmitter<number>();

  displayedColumns: string[] = ['nomeFornecedor', 'cnpj', 'email', 'cidade', 'cep', 'editar', 'deletar'];
  dataSource: any[] = [];

  novoFornecedor: FornecedoresModel = new FornecedoresModel(0, '', '', '', '', '', '', '', '');
  id!: Promise<boolean>;
  editando!: boolean;

  constructor(
    private fornecedoresServService: FornecedoresServService
  ) { }

  ngOnInit(): void {
    this.carregarFornecedores();
  }

  carregarFornecedores(): void {
    this.fornecedoresServService.buscarTodosFornecedores().subscribe((fornecedores) => {
      this.dataSource = fornecedores;
    });
  }

  editarFornecedor(id: number): void {
    if(id || id > 0 || id !== null || Number.isNaN(id)) {
      console.log(`ID emitido para edição: ${id}`);
      this.fornecedorSelecionado.emit(id);
    } else {
      console.error("Erro: Tentativa de editar um fornecedor sem ID válido.");
      alert("Erro: O fornecedor selecionado não possui um ID válido.");
    }
  }

  deletarFornecedor(id: number): void {
    if(confirm("Tem certeza que deseja deletar este fornecedor?")) {
      this.fornecedoresServService.deletarFornecedorPorId(id).subscribe(() => {
        this.carregarFornecedores();
      });
    }
  } 

}
