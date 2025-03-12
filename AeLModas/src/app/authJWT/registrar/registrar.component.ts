import { CommonModule } from '@angular/common';
import { AfterViewInit, Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-registrar',
  standalone: true,
  imports: [
        CommonModule,
        RouterModule,
        FormsModule,
  ],
  templateUrl: './registrar.component.html',
  styleUrl: './registrar.component.css'
})
export class RegistrarComponent implements AfterViewInit {

  constructor() { }

  ngOnInit() { }

  isLoggedIn = false;
  isLoginFailed = false;
  isSignUp = false;
  isSignUpFailed = false;
  isSignUpSuccessful = false;

  signUpData = {
    name: '',
    email: '',
    password: ''
  };

  onSignUp() {
    if (this.signUpData.email && this.signUpData.password) {
      this.isSignUpSuccessful = true;
      this.isSignUpFailed = false;
    } else {
      this.isSignUpFailed = true;
    }
  }

  toggleSignUp() {
    this.isSignUp = !this.isSignUp;
  }

  // Função de animação para o bacround da box de login
  ngAfterViewInit() {
    setTimeout(() => {
      const nomeLoja = document.querySelector('.nome-loja');
      if (nomeLoja) {
        nomeLoja.classList.add('aparecer'); // Ativa a animação
      }
    }, 500);
  }

}
