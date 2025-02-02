import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CadastroDevedorComponent } from './cadastro-devedor.component';

describe('CadastroDevedorComponent', () => {
  let component: CadastroDevedorComponent;
  let fixture: ComponentFixture<CadastroDevedorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CadastroDevedorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CadastroDevedorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
