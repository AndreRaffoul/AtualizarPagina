import { HttpClientModule } from '@angular/common/http';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { MatTableModule } from '@angular/material/table';
import { DevedorModel } from '../../../models/DevedorModel.model';
import { DevedorService } from '../../../services/devedor.service';

@Component({
  selector: 'app-listagem-devedor',
  standalone: true,
  imports: [
    MatListModule,
        MatIconModule,
        MatTableModule,
        HttpClientModule
  ],
  templateUrl: './listagem-devedor.component.html',
  styleUrl: './listagem-devedor.component.css',
  providers: [ DevedorService ]
})
export class ListagemDevedorComponent implements OnInit {

  @Output() devedorSelecionado = new EventEmitter<DevedorModel>();
  editando: boolean = false; // 🔹 Flag para indicar se está no modo de edição
  
  displayedColumns: string[] = ['id', 'nomeDevedor', 'email', 'telefone', 'endereco', 'cpf', 'divida', 'dataDivida', 'dataVencimento', 'statusDivida', 'observacoes', 'preco', 'quantidade', 'editar', 'deletar'];
  dataSource: DevedorModel[] = [];

  constructor(
    private devedorService: DevedorService
  ) {}

  ngOnInit() { 
    this.carregarDevedores();
   }

  carregarDevedores() {
    this.devedorService.getTodosDevedores().subscribe((devedores) => {
      this.dataSource = devedores;
  } );
  }

  editarDevedor(devedor: DevedorModel): void {
    console.log(`Devedor emitido para edição:`, devedor);
    this.devedorSelecionado.emit(devedor); // 🔹 Agora emitimos o objeto, e não apenas o id.
  }

  deletarDevedorId(id: number): void {
    if(confirm("Tem certeza que deseja deletar este devedor?")) {
      this.devedorService.deletarDevedorId(id).subscribe(() => {
        this.carregarDevedores();
      });
    }
  }

  
  
}
