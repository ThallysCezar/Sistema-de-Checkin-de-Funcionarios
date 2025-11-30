import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

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
    MatIconModule,
    MatProgressSpinnerModule,
  ]
})
export class CheckinsModule { }
