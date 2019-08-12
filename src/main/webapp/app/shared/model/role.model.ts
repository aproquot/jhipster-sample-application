import { IUtilisateur } from 'app/shared/model/utilisateur.model';

export interface IRole {
  id?: number;
  libelle?: string;
  utilisateurs?: IUtilisateur[];
}

export class Role implements IRole {
  constructor(public id?: number, public libelle?: string, public utilisateurs?: IUtilisateur[]) {}
}
