import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AuthGuard } from './auth/auth.guard';
import { RoleGuard } from './auth/role.guard';

const routes: Routes = [
  {  path: '', redirectTo: 'logins', pathMatch: 'full' },
  {
  path: 'checkins',
  loadChildren: () => import('./checkins/checkins-module').then(mod => mod.CheckinsModule),
  canLoad: [AuthGuard]
  },
  {
  path: 'logins',
  loadChildren: () => import('./logins/logins-module').then(mod => mod.LoginsModule),
  },
  {
  path: 'unauthorized',
  loadChildren: () => import('./auth/unauthorized.module').then(mod => mod.UnauthorizedModule),
  },
  {
  path: 'admins',
  loadChildren: () => import('./admins/admins-module').then(mod => mod.AdminsModule),
  canLoad: [RoleGuard],
  canActivate: [RoleGuard],
  data: { role: 'manager' }
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
