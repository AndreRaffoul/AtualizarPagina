import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EstoqueListComponentComponent } from './estoque-list.component';

describe('EstoqueListComponentComponent', () => {
  let component: EstoqueListComponentComponent;
  let fixture: ComponentFixture<EstoqueListComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EstoqueListComponentComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EstoqueListComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
