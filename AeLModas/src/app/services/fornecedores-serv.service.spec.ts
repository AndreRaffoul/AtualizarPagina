import { TestBed } from '@angular/core/testing';

import { FornecedoresServService } from './fornecedores-serv.service';

describe('FornecedoresServService', () => {
  let service: FornecedoresServService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FornecedoresServService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
