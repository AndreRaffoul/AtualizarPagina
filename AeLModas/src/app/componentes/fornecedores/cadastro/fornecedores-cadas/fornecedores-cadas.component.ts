import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { timeout } from 'rxjs';
import { FornecedoresModel } from '../../../../models/FornecedoresModel';
import { FornecedoresServService } from '../../../../services/fornecedores-serv.service';
import { FornecedoresListComponent } from "../../listagem/fornecedores-list/fornecedores-list.component";

@Component({
  selector: 'app-fornecedores-cadas',
  standalone: true,
  imports: [
    FornecedoresListComponent,
    FormsModule,
    HttpClientModule,
    CommonModule
],
  templateUrl: './fornecedores-cadas.component.html',
  styleUrl: './fornecedores-cadas.component.css',
  providers: [FornecedoresServService]
})
export class FornecedoresCadasComponent implements OnInit {

  novoFornecedor: FornecedoresModel = new FornecedoresModel(0, '', '', '', '', '', '', '', '');
  dataSource: FornecedoresModel[] = [];

  editando: boolean = false; // Flag para indicar se está no modo de edição
  id!: Promise<boolean>;

  constructor(
    private fornecedoresServService: FornecedoresServService
  ) { }

  ngOnInit(): void {}

  carregarFornecedorParaEdicao(id: number): void {    
    this.fornecedoresServService.buscarFornecedorPorId(id)
    .pipe(timeout(5000))
        .subscribe( (fornecedor) => {
            if(fornecedor !== null || fornecedor !== undefined ||
              fornecedor !== null || Number.isNaN(fornecedor) ) {
              console.log('Fornecedor carregado para edição:', fornecedor);
              this.novoFornecedor = new FornecedoresModel(
                fornecedor.id,
                fornecedor.nomeFornecedor,
                fornecedor.email,
                fornecedor.telefone,
                fornecedor.endereco,
                fornecedor.cidade,
                fornecedor.estado,
                fornecedor.cep,
                fornecedor.cnpj
              );
              this.editando = true; }
            else {
              console.error("Erro: Tentativa de editar um fornecedor sem ID válido.");
              alert("Erro: O fornecedor selecionado não possui um ID válido.");
            }
          },
        (error) => { console.error('Erro ao buscar fornecedor:', error); });
          alert("Deseja editar esse fornecedor?");
  }

  salvarFornecedor(): void {
    if (this.editando) {
      if (!this.novoFornecedor.id || this.novoFornecedor.id === 0
        || this.novoFornecedor.id === undefined || this.novoFornecedor.id === null
        || Number.isNaN(this.novoFornecedor.id)
      ) {
        console.error("Erro: Tentativa de atualizar um fornecedor sem ID válido.");
        alert("Deseja editar esse fornecedor? ");
        return;
      }
  
      this.fornecedoresServService.atualizarFornecedorPorId(this.novoFornecedor)
        .subscribe(
          (fornecedor) => {
            console.log("Fornecedor atualizado:", fornecedor);
            alert("Fornecedor atualizado com sucesso!");
          },
          (error) => {
            console.error("Erro ao atualizar fornecedor:", error);
          }
        );
    } else {
      this.fornecedoresServService.salvarFornecedor(this.novoFornecedor)
        .subscribe(
          () => {
            alert("Fornecedor cadastrado com sucesso!");
          },
          (error) => {
            console.error("Erro ao salvar fornecedor:", error);
          }
        );
    }
  }
  

  buscarFornecedorPorId(id: number): void {
    this.fornecedoresServService.buscarFornecedorPorId(id)
    .subscribe((fornecedor) => {
      console.log('Fornecedor encontrado:', fornecedor);
    },
    (error) => {
      console.error('Erro ao buscar fornecedor:', error);
    });
  }

  resetForm(): void {
    this.novoFornecedor = new FornecedoresModel(0, '', '', '', '', '', '', '', '');
    this.editando = false; 
  }

  refreshPage() {
    window.location.reload();
  }


}
