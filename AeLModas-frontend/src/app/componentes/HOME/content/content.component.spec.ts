import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CONTENTComponent } from './content.component';

describe('CONTENTComponent', () => {
  let component: CONTENTComponent;
  let fixture: ComponentFixture<CONTENTComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CONTENTComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CONTENTComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
