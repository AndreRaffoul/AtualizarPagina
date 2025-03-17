import { Routes } from '@angular/router';
import { CadastroDevedorComponent } from './componentes/devedor/cadastro-devedor/cadastro-devedor.component';
import { EstoqueComponent } from './componentes/estoque/cadastro/estoque.component';
import { FornecedoresCadasComponent } from './componentes/fornecedores/cadastro/fornecedores-cadas/fornecedores-cadas.component';
import { HOMEComponent } from './componentes/HOME/home.component';
import { CadastroProdutoComponent } from './componentes/produto/cadastro-produto/cadastro-produto.component';

import { AuthGuard } from './authJWT/guards/auth-guard.guard';
import { LoginComponent } from './authJWT/login/login.component';

export const routes: Routes = [
  { path: '', component: HOMEComponent },
  { path: 'home', component: HOMEComponent },
  { path: 'login', component: LoginComponent },
  { path: 'registro', component: LoginComponent },
  { path: 'fornecedoresCadas', component: FornecedoresCadasComponent, canActivate: [AuthGuard] },
  { path: 'estoqueCadas', component: EstoqueComponent, canActivate: [AuthGuard] },
  { path: 'devedorCadas', component: CadastroDevedorComponent, canActivate: [AuthGuard] },
  { path: 'produtoCadas', component: CadastroProdutoComponent, canActivate: [AuthGuard]  },
  { path: '**', redirectTo: '' },
];


