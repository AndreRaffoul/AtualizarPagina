import { Routes } from '@angular/router';
import { CadastroDevedorComponent } from './componentes/devedor/cadastro-devedor/cadastro-devedor.component';
import { CadastroFornecedorComponent } from './componentes/fornecedor/cadastro-fornecedor/cadastro-fornecedor.component';
import { HOMEComponent } from './componentes/HOME/home.component';

export const routes: Routes = [

  /* Rota da home */
  {path: '', component: HOMEComponent, pathMatch: 'full'},

  { path: 'cadastroDevedor', component: CadastroDevedorComponent },
  { path:'cadastroFornecedor', component: CadastroFornecedorComponent },
  // { path:'cadastroEstoque', component: CadastroEstoqueComponent },
  // { path:'cadastroProduto', component: CadastroProdutoComponent },

  

  // rotas de erro
  //{ path: '**', redirectTo: '' }

];
