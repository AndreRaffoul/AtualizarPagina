import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListagemDevedorComponent } from './listagem-devedor.component';

describe('ListagemDevedorComponent', () => {
  let component: ListagemDevedorComponent;
  let fixture: ComponentFixture<ListagemDevedorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListagemDevedorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListagemDevedorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
