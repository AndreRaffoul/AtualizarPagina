import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MENUComponent } from './menu.component';

describe('MENUComponent', () => {
  let component: MENUComponent;
  let fixture: ComponentFixture<MENUComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MENUComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MENUComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
