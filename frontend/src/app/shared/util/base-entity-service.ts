import {HttpClient} from '@angular/common/http';
import {SelectItem} from 'primeng/api';
import {Table} from 'primeng/table';
import {environment} from '../../../environments/environment';
import {Observable} from 'rxjs';
import {RequestUtil} from './request-util';
import {Page} from './page';
import {DefaultFilter} from '../models/default-filter';

export abstract class BaseEntityService<T, Y> {

  protected constructor(protected http: HttpClient) { }

  resourceUrl = environment.apiUrl + '/' + this.getEntity();

  abstract getEntity(): string;

  search(table: Table, filter: DefaultFilter): Observable<Page<Y>> {
    return this.http.post<Page<Y>>(this.resourceUrl, filter,
      {params: RequestUtil.getRequestParamsTable(table)});
  }

  insert(entity: T): Observable<T> {
    return this.http.post<T>(this.resourceUrl, entity);
  }

  findById(id: number): Observable<T> {
    return this.http.get<T>(this.resourceUrl + '/' + id);
  }

  update(entity: T): Observable<T> {
    return this.http.put<T>(this.resourceUrl, entity);
  }

  findAllDropDown(): Observable<SelectItem[]> {
    return this.http.get<SelectItem[]>(this.resourceUrl + '/select');
  }

}
