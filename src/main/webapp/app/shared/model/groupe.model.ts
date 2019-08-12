import { IClient } from 'app/shared/model/client.model';

export interface IGroupe {
  id?: number;
  libelle?: string;
  clients?: IClient[];
}

export class Groupe implements IGroupe {
  constructor(public id?: number, public libelle?: string, public clients?: IClient[]) {}
}
