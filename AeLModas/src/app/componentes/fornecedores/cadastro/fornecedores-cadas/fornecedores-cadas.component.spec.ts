import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FornecedoresCadasComponent } from './fornecedores-cadas.component';

describe('FornecedoresCadasComponent', () => {
  let component: FornecedoresCadasComponent;
  let fixture: ComponentFixture<FornecedoresCadasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FornecedoresCadasComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FornecedoresCadasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
