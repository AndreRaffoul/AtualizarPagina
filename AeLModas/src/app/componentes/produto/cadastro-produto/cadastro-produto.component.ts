import { Component } from '@angular/core';
import { ListagemProdutoComponent } from "../listagem-produto/listagem-produto.component";

@Component({
  selector: 'app-cadastro-produto',
  standalone: true,
  imports: [ListagemProdutoComponent],
  templateUrl: './cadastro-produto.component.html',
  styleUrl: './cadastro-produto.component.css'
})
export class CadastroProdutoComponent {

}
