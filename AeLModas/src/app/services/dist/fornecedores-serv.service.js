"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
exports.FornecedoresServService = void 0;
var core_1 = require("@angular/core");
var Observable_1 = require("rxjs/internal/Observable");
var environment_1 = require("../../environments/environment");
var FornecedoresServService = /** @class */ (function () {
    function FornecedoresServService(http) {
        this.http = http;
        //private urlFornecedor = 'http://localhost:8099/sitelojaaelmodas/fornecedor';
        this.urlFornecedor = environment_1.environment.apiUrl + "/fornecedor";
    }
    FornecedoresServService.prototype.salvarFornecedor = function (fornecedor) {
        return this.http.post(this.urlFornecedor + "/salvar", fornecedor);
    };
    FornecedoresServService.prototype.buscarTodosFornecedores = function () {
        return this.http.get(this.urlFornecedor + "/buscarTodos");
    };
    FornecedoresServService.prototype.buscarFornecedorPorId = function (id) {
        return this.http.get(this.urlFornecedor + '/buscarPorId/' + id);
    };
    FornecedoresServService.prototype.atualizarFornecedorPorId = function (fornecedor) {
        if (!fornecedor.id || fornecedor.id === 0 ||
            fornecedor.id === undefined || fornecedor.id === null ||
            Number.isNaN(fornecedor.id)) {
            console.error("Erro: ID do fornecedor está indefinido!");
            return new Observable_1.Observable();
        }
        var url = this.urlFornecedor + "/atualizar/" + fornecedor.id;
        console.log("Chamando API com URL:", url);
        return this.http.put(url, fornecedor);
    };
    FornecedoresServService.prototype.deletarFornecedorPorId = function (id) {
        return this.http["delete"](this.urlFornecedor + '/deletar/' + id);
    };
    FornecedoresServService = __decorate([
        core_1.Injectable({
            providedIn: 'root'
        })
    ], FornecedoresServService);
    return FornecedoresServService;
}());
exports.FornecedoresServService = FornecedoresServService;
