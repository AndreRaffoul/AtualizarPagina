import { TestBed } from '@angular/core/testing';

import { EstoqueServService } from './estoque-serv.service';

describe('EstoqueServService', () => {
  let service: EstoqueServService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EstoqueServService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
