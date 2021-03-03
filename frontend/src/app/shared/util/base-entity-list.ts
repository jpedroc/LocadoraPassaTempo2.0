import {ModalService} from './modal-service';
import {BaseEntityService} from './base-entity-service';
import {BlockUI, NgBlockUI} from 'ng-block-ui';
import {Table} from 'primeng/table';
import {OnDestroy, ViewChild} from '@angular/core';
import {DynamicDialogRef} from 'primeng/dynamicdialog';
import {finalize, takeWhile} from 'rxjs/operators';

export abstract class BaseEntityList<Model, List> implements OnDestroy {

  protected constructor(protected modalService: ModalService) {
  }
  abstract SERVICE: BaseEntityService<any, any>;

  _alive = true;

  @BlockUI() blockUI: NgBlockUI;
  @ViewChild(Table) table: Table;

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

  updateTable(event?: any) {
    this.blockUI.start("Carregando...");

    const table = event ? event.table : this.table;
    const filter = event ? event.filter : { query: '', ...this.table.filters};

    this.SERVICE.search(table, filter)
      .pipe(finalize(() => {
        this.blockUI.stop();
      }))
      .subscribe((result) => {
        this.table.value = result.content;
        this.table._totalRecords = result.totalElements;
      });
  }
}
