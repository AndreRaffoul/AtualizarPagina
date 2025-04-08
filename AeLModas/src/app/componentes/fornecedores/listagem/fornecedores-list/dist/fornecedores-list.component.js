"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
exports.FornecedoresListComponent = void 0;
var http_1 = require("@angular/common/http");
var core_1 = require("@angular/core");
var icon_1 = require("@angular/material/icon");
var list_1 = require("@angular/material/list");
var table_1 = require("@angular/material/table");
var FornecedoresModel_1 = require("../../../../models/FornecedoresModel");
var fornecedores_serv_service_1 = require("../../../../services/fornecedores-serv.service");
var FornecedoresListComponent = /** @class */ (function () {
    function FornecedoresListComponent(fornecedoresServService) {
        this.fornecedoresServService = fornecedoresServService;
        this.fornecedorSelecionado = new core_1.EventEmitter();
        this.displayedColumns = ['nomeFornecedor', 'cnpj',
            'email', 'cidade', 'cep', 'editar', 'deletar'];
        this.dataSource = [];
        this.novoFornecedor = new FornecedoresModel_1.FornecedoresModel(0, '', '', '', '', '', '', '', '');
    }
    FornecedoresListComponent.prototype.ngOnInit = function () {
        this.carregarFornecedores();
    };
    FornecedoresListComponent.prototype.carregarFornecedores = function () {
        var _this = this;
        this.fornecedoresServService.buscarTodosFornecedores().subscribe(function (fornecedores) {
            _this.dataSource = fornecedores;
        });
    };
    FornecedoresListComponent.prototype.editarFornecedor = function (id) {
        if (id || id > 0 || id !== null || Number.isNaN(id)) {
            console.log("ID emitido para edi\u00E7\u00E3o: " + id);
            this.fornecedorSelecionado.emit(id);
        }
        else {
            console.error("Erro: Tentativa de editar um fornecedor sem ID válido.");
            alert("Erro: O fornecedor selecionado não possui um ID válido.");
        }
    };
    FornecedoresListComponent.prototype.deletarFornecedor = function (id) {
        var _this = this;
        if (confirm("Tem certeza que deseja deletar este fornecedor?")) {
            this.fornecedoresServService.deletarFornecedorPorId(id).subscribe(function () {
                _this.carregarFornecedores();
            });
        }
    };
    __decorate([
        core_1.Output()
    ], FornecedoresListComponent.prototype, "fornecedorSelecionado");
    FornecedoresListComponent = __decorate([
        core_1.Component({
            selector: 'app-fornecedores-list',
            standalone: true,
            imports: [
                list_1.MatListModule,
                icon_1.MatIconModule,
                table_1.MatTableModule,
                http_1.HttpClientModule
            ],
            templateUrl: './fornecedores-list.component.html',
            styleUrl: './fornecedores-list.component.css',
            providers: [fornecedores_serv_service_1.FornecedoresServService]
        })
    ], FornecedoresListComponent);
    return FornecedoresListComponent;
}());
exports.FornecedoresListComponent = FornecedoresListComponent;
