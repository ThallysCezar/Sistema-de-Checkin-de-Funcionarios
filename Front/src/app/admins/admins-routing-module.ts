import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { Admin } from './admin/admin';
import { RoleGuard } from '../auth/role.guard';

const routes: Routes = [
  {  path: '', component: Admin, canActivate: [RoleGuard], data: { role: 'manager' } },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminsRoutingModule { }
