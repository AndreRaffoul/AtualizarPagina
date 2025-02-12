import { TestBed } from '@angular/core/testing';

import { DevedorService } from './devedor.service';

describe('DevedorService', () => {
  let service: DevedorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DevedorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
