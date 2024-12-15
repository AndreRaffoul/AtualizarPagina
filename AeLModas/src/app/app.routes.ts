import { Routes } from '@angular/router';
import { EstoqueComponent } from './componentes/estoque/cadastro/estoque.component';
import { FornecedoresCadasComponent } from './componentes/fornecedores/cadastro/fornecedores-cadas/fornecedores-cadas.component';
import { FornecedoresListComponent } from './componentes/fornecedores/listagem/fornecedores-list/fornecedores-list.component';

export const routes: Routes = [

  // Componente FornecedoresCadasComponent
  { path: 'fornecedoresCadas', component: FornecedoresCadasComponent },

  // Componente FornecedoresListComponent
  { path: 'fornecedoresList', component: FornecedoresListComponent },

  // Componente FornecedoresListComponent
  { path: 'estoque', component: EstoqueComponent }

];
