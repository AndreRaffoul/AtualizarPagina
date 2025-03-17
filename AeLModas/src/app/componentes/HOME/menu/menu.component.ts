import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../../services/auth.service';

@Component({
  selector: 'app-menu',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,
    MatSlideToggleModule,
    MatIconModule,
    MatMenuModule,
    MatButtonModule],
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.css',
  providers: []
})
export class MENUComponent {

  isLoggedIn = false;

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
    // Atualiza o estado sempre que houver mudanças
    this.authService.getAuthStatus().subscribe(status => {
      this.isLoggedIn = status;
    });
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']); // Redireciona para login ao deslogar
  }

}
