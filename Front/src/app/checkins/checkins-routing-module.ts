import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { Checkin } from './checkin/checkin';
import { AuthGuard } from '../auth/auth.guard';

const routes: Routes = [
  {  path: '', component: Checkin, canActivate: [AuthGuard] },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CheckinsRoutingModule { }
