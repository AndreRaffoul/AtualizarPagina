import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { EstoqueListComponent } from '../listagem/estoque-list.component';

@Component({
  selector: 'app-estoque',
  standalone: true,
  imports: [EstoqueListComponent],
  templateUrl: './estoque.component.html',
  styleUrl: './estoque.component.css'
})
export class EstoqueComponent {
  form: FormGroup;

  constructor(private fb: FormBuilder) {
    this.form = this.fb.group({
      plusSize: [false] // Valor padrão: desmarcado
    });
  }

  onSubmit() {
    console.log('Formulário enviado:', this.form.value);
  }

}
