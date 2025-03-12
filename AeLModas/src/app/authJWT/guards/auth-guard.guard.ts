import { CanActivateFn, Router } from '@angular/router';

export const authGuardGuard: CanActivateFn = (route, state) => {

  const tonken = localStorage.getItem('token');

  if(!tonken){
    const router = new Router();
    router.navigate(['/login']);
    return false;
  }

  return true;
};
