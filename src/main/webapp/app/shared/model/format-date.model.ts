export interface IFormatDate {
  id?: number;
  libelle?: string;
  format?: string;
}

export class FormatDate implements IFormatDate {
  constructor(public id?: number, public libelle?: string, public format?: string) {}
}
