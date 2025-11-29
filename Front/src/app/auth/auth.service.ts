import { Injectable, Inject, PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { LoginService, EmployeeDTO, LoginResponse } from '../logins/services/login';
import { Observable, of, tap } from 'rxjs';

const STORAGE_KEY = 'employee';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private readonly isBrowser: boolean;

  constructor(private loginService: LoginService, @Inject(PLATFORM_ID) private platformId: object) {
    this.isBrowser = isPlatformBrowser(this.platformId);
  }

  login(username: string, password: string): Observable<LoginResponse> {
    return this.loginService.login(username, password).pipe(
      tap(res => this.setSession(res))
    );
  }

  logout() {
    if (!this.isBrowser) return;
    localStorage.removeItem(STORAGE_KEY);
    localStorage.removeItem('role');
  }

  private setSession(loginResponse: LoginResponse) {
    if (!this.isBrowser) return;
    const employee: EmployeeDTO = { id: loginResponse.id, name: loginResponse.name, email: loginResponse.email };
    localStorage.setItem(STORAGE_KEY, JSON.stringify(employee));
    localStorage.setItem('role', loginResponse.role);
  }

  get currentUser(): EmployeeDTO | null {
    if (!this.isBrowser) return null;
    const raw = localStorage.getItem(STORAGE_KEY);
    try {
      return raw ? JSON.parse(raw) : null;
    } catch (e) {
      console.warn('Failed to parse employee from storage', e);
      return null;
    }
  }

  get isAuthenticated(): boolean {
    if (!this.isBrowser) return false;
    return !!this.currentUser;
  }

  hasRole(role: string): boolean {
    if (!this.isBrowser) return false;
    const storedRole = localStorage.getItem('role');
    return (storedRole?.toLowerCase() ?? '') === role.toLowerCase();
  }
}
