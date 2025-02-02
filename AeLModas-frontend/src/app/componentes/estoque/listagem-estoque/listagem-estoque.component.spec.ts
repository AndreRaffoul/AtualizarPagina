import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListagemEstoqueComponent } from './listagem-estoque.component';

describe('ListagemEstoqueComponent', () => {
  let component: ListagemEstoqueComponent;
  let fixture: ComponentFixture<ListagemEstoqueComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListagemEstoqueComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListagemEstoqueComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
