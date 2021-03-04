import {ModalService} from './modal-service';
import {BaseEntityService} from './base-entity-service';
import {BlockUI, NgBlockUI} from 'ng-block-ui';
import {Component, OnDestroy, ViewChild} from '@angular/core';
import {DynamicDialogRef} from 'primeng/dynamicdialog';
import {finalize, takeWhile} from 'rxjs/operators';
import {TableCustomComponent} from '../components/table-custom/table-custom.component';
import {ColumnCustom} from '../models/column-custom';
import {TableFilterCustom} from '../models/table-filter-custom';
import {TableUpdateEvent} from '../models/table-update-event';

@Component({template:''})
export abstract class BaseEntityList<Model, List> implements OnDestroy {

  protected constructor(protected modalService: ModalService) {
  }
  abstract SERVICE: BaseEntityService<any, any>;
  abstract COLUMNS: ColumnCustom[];
  abstract FILTERS: TableFilterCustom[];

  _alive = true;

  @BlockUI() blockUI: NgBlockUI;
  @ViewChild(TableCustomComponent) table: TableCustomComponent;

  ngOnDestroy() {
    this._alive = false;
  }

  openCreationModal(component): DynamicDialogRef {
    return this.openModal(component, { editing: true });
  }

  openViewModal(component, id: number): DynamicDialogRef {
    return this.openModal(component, { id, editing: false });
  }

  openUpdateModal(component, id: number): DynamicDialogRef {
    return this.openModal(component, { id, editing: true });
  }

  openModal(component: any, data): DynamicDialogRef {
    const form = this.modalService.openModal(component, {}, data);
    form.onClose
      .pipe(
        takeWhile(_ => this._alive)
      )
      .subscribe(res => {
        if (res) {
          this.updateTable();
        }
      });

    return form;
  }

  updateTable(event?: TableUpdateEvent) {
    this.blockUI.start("Carregando...");

    const table = event ? event.table : this.table.table;
    const filter = event ? event.filter : { query: '', ...this.table.filters};

    this.SERVICE.search(table, filter)
      .pipe(finalize(() => {
        this.blockUI.stop();
      }))
      .subscribe((result) => {
        this.table.result = result;
      });
  }
}
