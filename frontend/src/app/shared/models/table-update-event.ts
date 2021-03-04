import {Table} from 'primeng/table';
import {DefaultFilter} from '../filters/default-filter';

export class TableUpdateEvent {
  table: Table;
  filter?: DefaultFilter;
}
