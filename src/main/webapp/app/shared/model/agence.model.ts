import { IClient } from 'app/shared/model/client.model';
import { IDisponibilite } from 'app/shared/model/disponibilite.model';

export interface IAgence {
  id?: number;
  libelle?: string;
  clients?: IClient[];
  disponibilites?: IDisponibilite[];
}

export class Agence implements IAgence {
  constructor(public id?: number, public libelle?: string, public clients?: IClient[], public disponibilites?: IDisponibilite[]) {}
}
