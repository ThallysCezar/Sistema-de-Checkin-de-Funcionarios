import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';

import { Checkin } from './checkin/checkin';
import { CheckinsRoutingModule } from './checkins-routing-module';


@NgModule({
  declarations: [
    Checkin
  ],
  imports: [
    CommonModule,
    CheckinsRoutingModule,
    MatButtonModule,
  ]
})
export class CheckinsModule { }
