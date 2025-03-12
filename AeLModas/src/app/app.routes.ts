import { Routes } from '@angular/router';
import { CadastroDevedorComponent } from './componentes/devedor/cadastro-devedor/cadastro-devedor.component';
import { EstoqueComponent } from './componentes/estoque/cadastro/estoque.component';
import { FornecedoresCadasComponent } from './componentes/fornecedores/cadastro/fornecedores-cadas/fornecedores-cadas.component';
import { HOMEComponent } from './componentes/HOME/home.component';
import { CadastroProdutoComponent } from './componentes/produto/cadastro-produto/cadastro-produto.component';

import { authGuardGuard } from './authJWT/guards/auth-guard.guard';
import { LoginComponent } from './authJWT/login/login.component';

export const routes: Routes = [

  // Path raiz para exibir a home
  { path: '', component: HOMEComponent },

  // Rota de login de usuário para autenticação JWT
  // Só será acessível se o usuário não estiver autenticado
  // E precisar cadastrar um produto, devedor, fornecedor ou estoque. Mas
  // só poderá fazer isso, se estiver autenticado no sistema e tiver um token válido
  // e tiver uma role como administrador ou gerente.
  { path:"login", component: LoginComponent },

  // Rota coringa
  { path: '**', component: HOMEComponent },

  { path: 'home', component: HOMEComponent },

  // Componente RegistroComponent
  { path: 'registro', component: LoginComponent },

  // Componente FornecedoresCadasComponent
  { path: 'fornecedoresCadas', component: FornecedoresCadasComponent, canActivate: [authGuardGuard] },

  // Componente FornecedoresListComponent
  { path: 'estoqueCadas', component: EstoqueComponent, canActivate: [authGuardGuard] },

  // Componente CadastroDevedorComponent
  { path: 'devedorCadas', component: CadastroDevedorComponent, canActivate: [authGuardGuard] },

  // Componente CadastroProdutoComponent
  { path: 'produtoCadas', component: CadastroProdutoComponent, canActivate: [authGuardGuard] },

];
