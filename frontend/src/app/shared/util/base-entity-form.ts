import {EventEmitter, Input, OnDestroy, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {ModalService} from './modal-service';
import {ConfirmationService} from 'primeng/api';
import {BlockUI, NgBlockUI} from 'ng-block-ui';
import {BaseEntityService} from './base-entity-service';
import {catchError, finalize, map, take, takeWhile} from 'rxjs/operators';
import {ValidationUtil} from './validation-util';
import {Observable} from 'rxjs';
import {PageNotificationService} from './page-notification-service';

export abstract class BaseEntityForm<Model> implements OnInit, OnDestroy {

  protected constructor(protected confirmationService: ConfirmationService,
                        protected router: Router,
                        protected formBuilder: FormBuilder,
                        protected route: ActivatedRoute,
                        protected modalService: ModalService,
                        protected pageNotificationService: PageNotificationService) {
  }

  @BlockUI() blockUI: NgBlockUI;
  @Output() done = new EventEmitter<Model | undefined>();
  @Input() modal = false;

  abstract SERVICE: BaseEntityService<Model, any>;
  form: FormGroup;
  entityId: number;
  editing = false;
  _alive = true;

  ngOnInit() {
    this.form = this.buildReactiveForm();
    if (!this.editing) {
      this.form.disable();
    }
    this.maybeLoadData();
  }

  abstract buildReactiveForm(): FormGroup;

  maybeLoadData() {
    if (!this.modal) {
      this.route.paramMap.subscribe(_ => {
        const snapshot = this.route.snapshot;
        this.entityId = Number(snapshot.paramMap.get('id'));
        this.editing = snapshot.data.editing;
        this.loadEntity();
      });
    } else {
      this.loadEntity();
    }
  }

  loadEntity() {
    if (this.entityId) {
      this.blockUI.start("Carregando...");
      return this.SERVICE.findById(this.entityId)
        .pipe(
          takeWhile(_ => this._alive),
          catchError(err => {
            this.close();
            throw err;
          }),
          finalize(() => {
            this.blockUI.stop();
          }),
          map(entity => this.onLoadEntity(entity)),
        ).subscribe();
    }
  }

  abstract onLoadEntity(entity: Model);

  saveForm(event) {
    if (event) { event.preventDefault(); }

    ValidationUtil.validateForm(this.form);

    if (this.form.valid) {
      const entity: Model = this.form.value;
      this.sendForm(entity);
    }
  }

  abstract sendForm(entity: Model);

  postAndShowNotification(result: Observable<Model>, message: string) {
    this.blockUI.start("Carregando...");

    return result
      .pipe(
        take(1),
        catchError(err => {
          this.close();
          throw err;
        }),
        finalize(() => {
          this.blockUI.stop();
        }),
        map(res => {
          this.close(res);

          this.pageNotificationService.showSuccess(message);
          return res;
        })
      ).subscribe();
  }

  isInsert() {
    return this.form.get('id').value === null;
  }

  close(entity?: Model) {
    this.modal ? this.done.emit(entity) : this.router.navigate(['../']);
  }

  ngOnDestroy() {
    this._alive = false;
  }

  isEdition() {
    return this.isInsert() || this.editing;
  }
}
