import { HttpClientModule } from '@angular/common/http';
import { Component, EventEmitter, Output } from '@angular/core';
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
export class ListagemDevedorComponent {

  @Output() devedorSelecionado = new EventEmitter<DevedorModel>();

  carregarDevedoresParaEdicao() {}
}
