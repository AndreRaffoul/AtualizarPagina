"use strict";
exports.__esModule = true;
exports.routes = void 0;
var home_component_1 = require("./componentes/HOME/home.component");
var login_component_1 = require("./authJWT/login/login.component");
exports.routes = [
    { path: '', component: home_component_1.HOMEComponent },
    { path: 'home', component: home_component_1.HOMEComponent },
    { path: 'login', component: login_component_1.LoginComponent },
    { path: 'registro', component: login_component_1.LoginComponent },
    {
        path: 'fornecedoresCadas',
        loadComponent: function () {
            return Promise.resolve().then(function () { return require('../app/componentes/fornecedores/cadastro/fornecedores-cadas/fornecedores-cadas.component'); }).then(function (m) { return m.FornecedoresCadasComponent; });
        }
    },
    {
        path: 'estoqueCadas',
        loadComponent: function () {
            return Promise.resolve().then(function () { return require('../app/componentes/estoque/cadastro/estoque.component'); }).then(function (m) { return m.EstoqueComponent; });
        }
    },
    {
        path: 'produtoCadas',
        loadComponent: function () {
            return Promise.resolve().then(function () { return require('../app/componentes/produto/cadastro-produto/cadastro-produto.component'); }).then(function (m) { return m.CadastroProdutoComponent; });
        }
    },
    {
        path: 'devedorCadas',
        loadComponent: function () {
            return Promise.resolve().then(function () { return require('../app/componentes/devedor/cadastro-devedor/cadastro-devedor.component'); }).then(function (m) { return m.CadastroDevedorComponent; });
        }
    },
    {
        path: 'sobrenos',
        loadComponent: function () {
            return Promise.resolve().then(function () { return require('../app/componentes/HOME/sobre-nos/sobre-nos.component'); }).then(function (m) { return m.SobreNosComponent; });
        }
    },
    {
        path: 'contatos',
        loadComponent: function () {
            return Promise.resolve().then(function () { return require('../app/componentes/HOME/contatos/contatos.component'); }).then(function (m) { return m.ContatosComponent; });
        }
    },
    { path: '**', redirectTo: '' },
];
