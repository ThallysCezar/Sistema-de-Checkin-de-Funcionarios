import { Component, ChangeDetectorRef } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../auth/auth.service';
import { CheckinService, WorkRecordDTO } from '../services/checkin';

@Component({
  selector: 'app-checkin',
  standalone: false,
  templateUrl: './checkin.html',
  styleUrls: ['./checkin.scss'],
})
export class Checkin {
  totalHoras: string | null = null;
  isLoading = false;
  lastRecord: WorkRecordDTO | null = null;
  errorMessage: string | null = null;
  successMessage: string | null = null;

  constructor(
    private authService: AuthService,
    private checkinService: CheckinService,
    private cdr: ChangeDetectorRef,
    private router: Router
  ) {}

  get isManager(): boolean {
    return this.authService.hasRole('manager') || this.authService.hasRole('admin');
  }

  goToDashboard() {
    this.router.navigate(['/admins']);
  }

  onCheckIn() {
    if (!this.authService.isAuthenticated) {
      console.warn('Not authenticated');
      return;
    }
    const user = this.authService.currentUser;
    if (!user?.id) return;

    console.log('Iniciando check-in...');
    this.isLoading = true;
    this.errorMessage = null;
    this.successMessage = null;
    this.cdr.detectChanges();

    this.checkinService.checkIn(user.id).subscribe({
      next: (record) => {
        console.log('Check-in sucesso:', record);
        this.isLoading = false;
        this.lastRecord = record;
        this.totalHoras = null;
        this.successMessage = 'Check-in realizado com sucesso!';
        this.cdr.detectChanges();
        console.log('successMessage definido:', this.successMessage);
        setTimeout(() => {
          this.successMessage = null;
          this.cdr.detectChanges();
        }, 5000);
      },
      error: (err) => {
        console.log('Check-in erro:', err);
        this.isLoading = false;
        if (err.status === 409) {
          this.errorMessage = (err.error && err.error.message) || 'Você já fez check-in e ainda não fez check-out.';
        } else {
          this.errorMessage = (err.error && err.error.message) || 'Falha no check-in. Tente novamente.';
        }
        this.cdr.detectChanges();
        console.log('errorMessage definido:', this.errorMessage);
        setTimeout(() => {
          this.errorMessage = null;
          this.cdr.detectChanges();
        }, 5000);
      }
    });
  }

  onCheckOut() {
    if (!this.authService.isAuthenticated) {
      console.warn('Not authenticated');
      return;
    }
    const user = this.authService.currentUser;
    if (!user?.id) return;

    console.log('Iniciando check-out...');
    this.isLoading = true;
    this.errorMessage = null;
    this.successMessage = null;
    this.cdr.detectChanges();

    this.checkinService.checkOut(user.id).subscribe({
      next: (record) => {
        console.log('Check-out sucesso:', record);
        this.isLoading = false;
        this.lastRecord = record;
        this.totalHoras = record.duration ?? null;
        this.successMessage = 'Check-out realizado com sucesso!';
        this.cdr.detectChanges();
        console.log('successMessage definido:', this.successMessage);
        setTimeout(() => {
          this.successMessage = null;
          this.cdr.detectChanges();
        }, 5000);
      },
      error: (err) => {
        console.log('Check-out erro:', err);
        this.isLoading = false;
        if (err.status === 404) {
          this.errorMessage = (err.error && err.error.message) || 'Nenhum check-in ativo encontrado. Faça check-in primeiro.';
        } else {
          this.errorMessage = (err.error && err.error.message) || 'Falha no check-out. Tente novamente.';
        }
        this.cdr.detectChanges();
        console.log('errorMessage definido:', this.errorMessage);
        setTimeout(() => {
          this.errorMessage = null;
          this.cdr.detectChanges();
        }, 5000);
      }
    });
  }
}
