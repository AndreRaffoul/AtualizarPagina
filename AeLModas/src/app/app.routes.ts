import { Routes } from '@angular/router';
import { EstoqueComponent } from './componentes/estoque/cadastro/estoque.component';
import { FornecedoresCadasComponent } from './componentes/fornecedores/cadastro/fornecedores-cadas/fornecedores-cadas.component';
import { HOMEComponent } from './componentes/HOME/home.component';

export const routes: Routes = [

  /* Rota da home */
  {path: '', component: HOMEComponent, pathMatch: 'full'},

  // Componente FornecedoresCadasComponent
  { path: 'fornecedoresCadas', component: FornecedoresCadasComponent },

  // Componente FornecedoresListComponent
  { path: 'estoque', component: EstoqueComponent }

];
