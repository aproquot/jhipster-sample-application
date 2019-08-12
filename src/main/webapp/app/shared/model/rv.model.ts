import { Moment } from 'moment';

export interface IRv {
  id?: number;
  client?: string;
  agence?: string;
  objet?: string;
  dateRv?: Moment;
  description?: string;
  dateCreation?: Moment;
  dateModification?: Moment;
  commercial?: string;
}

export class Rv implements IRv {
  constructor(
    public id?: number,
    public client?: string,
    public agence?: string,
    public objet?: string,
    public dateRv?: Moment,
    public description?: string,
    public dateCreation?: Moment,
    public dateModification?: Moment,
    public commercial?: string
  ) {}
}
