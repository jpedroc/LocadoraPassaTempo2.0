import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {DialogModule} from 'primeng/dialog';
import {ToastModule} from 'primeng/toast';
import {RouterModule} from '@angular/router';

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    DialogModule,
    ToastModule,
    RouterModule
  ]
})
export class SharedModule { }
