import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CadastroEstoqueComponent } from './cadastro-estoque.component';

describe('CadastroEstoqueComponent', () => {
  let component: CadastroEstoqueComponent;
  let fixture: ComponentFixture<CadastroEstoqueComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CadastroEstoqueComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CadastroEstoqueComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
