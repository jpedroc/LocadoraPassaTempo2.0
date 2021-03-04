import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {DialogModule} from 'primeng/dialog';
import {ToastModule} from 'primeng/toast';
import {RouterModule} from '@angular/router';
import {TableCustomComponent} from './components/table-custom/table-custom.component';
import {InputCustomComponent} from './components/input-custom/input-custom.component';
import {TableModule} from 'primeng/table';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {ButtonModule} from 'primeng/button';
import {TooltipModule} from 'primeng/tooltip';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HttpClientModule} from '@angular/common/http';

@NgModule({
  declarations: [InputCustomComponent, TableCustomComponent, InputCustomComponent],
  exports: [
    TableCustomComponent,
    InputCustomComponent,
    InputCustomComponent,
    CommonModule,
    DialogModule,
    ToastModule,
    RouterModule,
    TableModule,
    FormsModule,
    ButtonModule,
    TooltipModule,
    ReactiveFormsModule,
  ],
  imports: [
    CommonModule,
    BrowserAnimationsModule,
    DialogModule,
    ToastModule,
    RouterModule,
    TableModule,
    FormsModule,
    ButtonModule,
    TooltipModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SharedModule { }
