import { CommonModule } from '@angular/common';
import { AfterViewInit, Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [

    // CommonModule, RouterModule,
    CommonModule,
    RouterModule,
    //ngModel
    FormsModule,

  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements AfterViewInit {


  constructor(
    private router: Router,
  ) { }

  isLoggedIn = false;
  isLoginFailed = false;
  isSignUp = false;
  isSignUpFailed = false;
  isSignUpSuccessful = false;

  loginData = {
    username: '',
    email: '',
    password: ''
  };

  signUpData = {
    name: '',
    email: '',
    password: ''
  };

  ngAfterViewInit() {
    setTimeout(() => {
      const nomeLoja = document.querySelector('.nome-loja');
      if (nomeLoja) {
        nomeLoja.classList.add('aparecer'); // Ativa a animação
      }
    }, 500);
  }

  onLogin() {
    if (this.loginData.email === 'admin@teste.com' && this.loginData.password === '123456') {
      this.isLoggedIn = true;
      this.isLoginFailed = false;
    } else {
      this.isLoginFailed = true;
    }
  }

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

  navigateToRegister() {
    this.router.navigate(['/registro']);
  }

}
