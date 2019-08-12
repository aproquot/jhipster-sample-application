import { Moment } from 'moment';
import { IAgence } from 'app/shared/model/agence.model';

export interface IDisponibilite {
  id?: number;
  nom?: string;
  prenom?: string;
  date?: Moment;
  competence?: string;
  logement?: string;
  occupation?: string;
  affaireEnVue?: string;
  icDomicile?: boolean;
  agence?: IAgence;
}

export class Disponibilite implements IDisponibilite {
  constructor(
    public id?: number,
    public nom?: string,
    public prenom?: string,
    public date?: Moment,
    public competence?: string,
    public logement?: string,
    public occupation?: string,
    public affaireEnVue?: string,
    public icDomicile?: boolean,
    public agence?: IAgence
  ) {
    this.icDomicile = this.icDomicile || false;
  }
}
