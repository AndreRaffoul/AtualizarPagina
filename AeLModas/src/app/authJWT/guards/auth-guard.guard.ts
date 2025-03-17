import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(
    private authService: AuthService,
    private router: Router) { }

  canActivate(): boolean {
    if (!this.authService.isAuthenticated()) {
      this.router.navigate(['/login']);
      return false;
    }

    /* permitir estas roles "ROLE_USER", "ROLE_ADMIN", "ROLE_MANAGER"
        Redireciona usuários sem permissão */
    const userRoles = this.authService.getRoles();
    if (userRoles.includes('ROLE_USER') || userRoles.includes('ROLE_ADMIN') || userRoles.includes('ROLE_MANAGER')) {
      // Redireciona usuários sem permissão para home
      this.router.navigate(['/home']);
      return false;
    }

    return true;
  }
}

