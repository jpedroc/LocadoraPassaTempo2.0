import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {DialogModule} from 'primeng/dialog';
import {ToastModule} from 'primeng/toast';
import {RouterModule} from '@angular/router';
import { InputCustonComponent } from './components/input-custom/input-custom.component';

@NgModule({
  declarations: [InputCustonComponent],
  imports: [
    CommonModule,
    DialogModule,
    ToastModule,
    RouterModule
  ]
})
export class SharedModule { }
