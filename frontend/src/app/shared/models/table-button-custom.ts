export class TableButtonCustom<T> {
  icon: string;
  description: string;
  action: (row: T) => void;
  hideButton?: (row?: T) => boolean;
}
