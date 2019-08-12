import { IAer } from 'app/shared/model/aer.model';

export interface ITypeAer {
  id?: number;
  libelle?: string;
  aers?: IAer[];
}

export class TypeAer implements ITypeAer {
  constructor(public id?: number, public libelle?: string, public aers?: IAer[]) {}
}
