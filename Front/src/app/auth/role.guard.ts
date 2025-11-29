import { Injectable, Inject, PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { CanActivate, Router, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, CanLoad, Route } from '@angular/router';
import { AuthService } from './auth.service';

@Injectable({ providedIn: 'root' })
export class RoleGuard implements CanActivate, CanLoad {
  private isBrowser: boolean;
  constructor(private authService: AuthService, private router: Router, @Inject(PLATFORM_ID) private platformId: object) {
    this.isBrowser = isPlatformBrowser(this.platformId);
  }

  canActivate(route: ActivatedRouteSnapshot): boolean | UrlTree {
    const expectedRole = route.data['role'] as string;
    if (!this.isBrowser) {
      return this.router.parseUrl('/logins');
    }
    if (!this.authService.isAuthenticated) {
      return this.router.parseUrl('/logins');
    }
    if (this.authService.hasRole(expectedRole)) {
      return true;
    }
    return this.router.parseUrl('/unauthorized');
  }

  canLoad(route: Route): boolean {
    if (!this.isBrowser) {
      return false;
    }
    const expectedRole = route.data && (route.data as any)['role'] as string;

    if (!this.authService.isAuthenticated) {
      setTimeout(() => this.router.navigate(['/logins']), 0);
      return false;
    }
    if (!expectedRole) return true;
    if (this.authService.hasRole(expectedRole)) {
      return true;
    }
    setTimeout(() => this.router.navigate(['/unauthorized']), 0);
    return false;
  }
}
