import { Injectable, Inject, PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { CanActivate, CanLoad, Route, Router, UrlTree } from '@angular/router';
import { AuthService } from './auth.service';

@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate, CanLoad {
  private isBrowser: boolean;
  constructor(private authService: AuthService, private router: Router, @Inject(PLATFORM_ID) private platformId: object) {
    this.isBrowser = isPlatformBrowser(this.platformId);
  }

  canActivate(): boolean | UrlTree {
    if (!this.isBrowser) return true;
    if (this.authService.isAuthenticated) {
      return true;
    }
    return this.router.parseUrl('/logins');
  }

  canLoad(route: Route): boolean | UrlTree {
    if (!this.isBrowser) return true;
    return this.canActivate();
  }
}
