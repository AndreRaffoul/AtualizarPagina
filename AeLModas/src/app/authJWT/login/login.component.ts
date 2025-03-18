import { CommonModule } from '@angular/common';
import { AfterViewInit, Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements AfterViewInit {

  loginData = { login: '', senha: '' };
  isLoggedIn = false;
  isLoginFailed = false;

  constructor(
    private authService: AuthService,
    private router: Router,
  ) { }

  onLogin( event: Event ): void {
    event.preventDefault();
    this.authService.login(this.loginData).subscribe({
      next: () => {
        this.isLoggedIn = true;
        this.isLoginFailed = false;
        this.router.navigate(['/home']);
      },
      error: () => {
        this.isLoginFailed = true;
      }
    });
  }

  ngAfterViewInit() {
    setTimeout(() => {
      const nomeLoja = document.querySelector('.nome-loja');
      if (nomeLoja) {
        nomeLoja.classList.add('aparecer');
      }
    }, 500);
  }

  navigateToRegister() {
    this.router.navigate(['/registro']);
  }

  toggleSignUp(){
    const signUp = document.querySelector('.sign-up');
    if (signUp) {
      signUp.classList.toggle('active');
    }
  }

}
