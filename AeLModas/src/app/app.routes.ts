import { Routes } from '@angular/router';
import { CadastroDevedorComponent } from './componentes/devedor/cadastro-devedor/cadastro-devedor.component';
import { EstoqueComponent } from './componentes/estoque/cadastro/estoque.component';
import { FornecedoresCadasComponent } from './componentes/fornecedores/cadastro/fornecedores-cadas/fornecedores-cadas.component';
import { CadastroProdutoComponent } from './componentes/produto/cadastro-produto/cadastro-produto.component';

export const routes: Routes = [

  // Componente FornecedoresCadasComponent
  { path: 'fornecedoresCadas', component: FornecedoresCadasComponent },

  // Rotas de listagem
  // { path: 'fornecedoresListagem', component: FornecedoresListComponent },

  // Componente FornecedoresListComponent
  { path: 'estoqueCadas', component: EstoqueComponent },

  // Componente CadastroDevedorComponent
  { path: 'devedorCadas', component: CadastroDevedorComponent },

  // Componente CadastroProdutoComponent
  { path: 'produtoCadas', component: CadastroProdutoComponent },

];
