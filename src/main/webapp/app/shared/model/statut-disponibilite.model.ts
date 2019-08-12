import { IDisponibilite } from 'app/shared/model/disponibilite.model';

export interface IStatutDisponibilite {
  id?: number;
  libelle?: string;
  disponibilite?: IDisponibilite;
}

export class StatutDisponibilite implements IStatutDisponibilite {
  constructor(public id?: number, public libelle?: string, public disponibilite?: IDisponibilite) {}
}
