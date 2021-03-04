import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {SharedModule} from './shared/shared.module';
import {AtorModule} from './pages/ator/ator.module';
import {ModalService} from './shared/util/modal-service';
import {DialogService} from 'primeng/dynamicdialog';
import {AtorService} from './shared/services/ator-service.service';
import {HttpClientModule} from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    SharedModule,
    AtorModule,
  ],
  providers: [ModalService, DialogService, AtorService],
  bootstrap: [AppComponent]
})
export class AppModule { }
