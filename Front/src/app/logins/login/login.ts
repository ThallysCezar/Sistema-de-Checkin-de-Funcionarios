import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { EmployeeDTO, LoginResponse } from '../services/login';
import { AuthService } from '../../auth/auth.service';

@Component({
  selector: 'app-login',
  standalone: false,
  templateUrl: './login.html',
  styleUrls: ['./login.scss'],
})
export class Login {
  loginForm: FormGroup;
  loading = false;
  errorMessage: string | null = null;

  constructor(private fb: FormBuilder, private router: Router, private authService: AuthService) {
    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  onSubmit() {
    if (!this.loginForm.valid) return;

    const username = this.loginForm.get('username')?.value;
    const password = this.loginForm.get('password')?.value;

    this.loading = true;
    this.errorMessage = null;

    this.authService.login(username, password).subscribe({
      next: (res: LoginResponse) => {
        // Backend returns employee details; store minimal session info if needed
        localStorage.setItem('username', res.email || username || '');
        // AuthService already stored the employee and role in localStorage; also set a convenience item
        localStorage.setItem('employee', JSON.stringify({ id: res.id, name: res.name, email: res.email }));
        if (res.role && res.role.toLowerCase() === 'manager') {
          this.router.navigate(['/admins']);
        } else {
          this.router.navigate(['/checkins']);
        }
      },
      error: (error) => {
        this.loading = false;
        if (error?.status === 401) {
          this.errorMessage = 'Invalid credentials';
        } else if (error?.status === 404) {
          this.errorMessage = 'User not found';
        } else {
          this.errorMessage = 'Unable to login. Please try again.';
        }
      },
      complete: () => (this.loading = false),
    });
  }

  // getters for template convenience
  get username() { return this.loginForm.get('username'); }
  get password() { return this.loginForm.get('password'); }
}
