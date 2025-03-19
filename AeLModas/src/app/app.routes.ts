import { Routes } from '@angular/router';
import { HOMEComponent } from './componentes/HOME/home.component';

import { LoginComponent } from './authJWT/login/login.component';

export const routes: Routes = [
  { path: '', component: HOMEComponent },
  { path: 'home', component: HOMEComponent },
  { path: 'login', component: LoginComponent },
  { path: 'registro', component: LoginComponent },

  {
    path: 'fornecedoresCadas', loadComponent: () =>
      import('../app/componentes/fornecedores/cadastro/fornecedores-cadas/fornecedores-cadas.component')
        .then(m => m.FornecedoresCadasComponent)
  },

  {
    path: 'estoqueCadas', loadComponent: () =>
      import('../app/componentes/estoque/cadastro/estoque.component')
        .then(m => m.EstoqueComponent)
  },

  {
    path: 'produtoCadas', loadComponent: () =>
      import('../app/componentes/produto/cadastro-produto/cadastro-produto.component')
        .then(m => m.CadastroProdutoComponent)
  },

  {
    path: 'devedorCadas', loadComponent: () =>
      import('../app/componentes/devedor/cadastro-devedor/cadastro-devedor.component')
        .then(m => m.CadastroDevedorComponent)
  },

  {
    path: 'sobrenos', loadComponent: () =>
      import('../app/componentes/HOME/sobre-nos/sobre-nos.component')
        .then(m => m.SobreNosComponent)
  },

  {
    path: 'contatos', loadComponent: () =>
      import('../app/componentes/HOME/contatos/contatos.component')
        .then(m => m.ContatosComponent)
  },

  { path: '**', redirectTo: '' },
];


