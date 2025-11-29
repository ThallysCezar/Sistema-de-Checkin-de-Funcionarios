import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-unauthorized',
  template: `
    <div class="unauthorized-container">
      <div class="unauthorized-card">
        <div class="error-icon">🚫</div>
        <h1>Acesso Negado</h1>
        <p>Você não tem permissão para acessar esta página.</p>
        <p class="detail">Apenas gerentes podem acessar a área administrativa.</p>
        <button class="btn-primary" (click)="goBack()">
          Voltar para Check-in
        </button>
      </div>
    </div>
  `,
  styles: [`
    .unauthorized-container {
      display: flex;
      align-items: center;
      justify-content: center;
      height: 100vh;
      background: #fafafa;
      font-family: Roboto, sans-serif;
    }

    .unauthorized-card {
      text-align: center;
      padding: 40px;
      background: white;
      border-radius: 8px;
      box-shadow: 0 2px 8px rgba(0,0,0,0.1);
      max-width: 500px;
    }

    .error-icon {
      font-size: 80px;
      margin-bottom: 20px;
    }

    h1 {
      font-size: 28px;
      margin-bottom: 16px;
      color: #333;
    }

    p {
      font-size: 16px;
      color: #666;
      margin-bottom: 12px;
    }

    .detail {
      font-size: 14px;
      color: #999;
      margin-bottom: 24px;
    }

    .btn-primary {
      background-color: #3f51b5;
      color: white;
      border: none;
      padding: 10px 24px;
      font-size: 14px;
      border-radius: 4px;
      cursor: pointer;
      margin-top: 8px;
      text-transform: uppercase;
      font-weight: 500;
    }

    .btn-primary:hover {
      background-color: #303f9f;
    }
  `],
  standalone: false
})
export class UnauthorizedComponent {
  constructor(private router: Router) {}

  goBack() {
    this.router.navigate(['/checkins']);
  }
}
