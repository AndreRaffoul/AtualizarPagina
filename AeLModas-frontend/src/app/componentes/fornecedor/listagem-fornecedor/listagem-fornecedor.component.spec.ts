import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListagemFornecedorComponent } from './listagem-fornecedor.component';

describe('ListagemFornecedorComponent', () => {
  let component: ListagemFornecedorComponent;
  let fixture: ComponentFixture<ListagemFornecedorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListagemFornecedorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListagemFornecedorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
