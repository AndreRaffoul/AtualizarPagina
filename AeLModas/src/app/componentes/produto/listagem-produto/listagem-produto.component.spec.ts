import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListagemProdutoComponent } from './listagem-produto.component';

describe('ListagemProdutoComponent', () => {
  let component: ListagemProdutoComponent;
  let fixture: ComponentFixture<ListagemProdutoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListagemProdutoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListagemProdutoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
