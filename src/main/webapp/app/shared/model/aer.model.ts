import { Moment } from 'moment';
import { ITypeAer } from 'app/shared/model/type-aer.model';
import { IStatut } from 'app/shared/model/statut.model';
import { IClient } from 'app/shared/model/client.model';
import { IUtilisateur } from 'app/shared/model/utilisateur.model';

export interface IAer {
  id?: number;
  description?: string;
  dateReception?: Moment;
  dateCloture?: Moment;
  dateReponse?: Moment;
  action?: string;
  ao?: string;
  montant?: string;
  typeAer?: ITypeAer;
  statut?: IStatut;
  client?: IClient;
  utilisateur?: IUtilisateur;
}

export class Aer implements IAer {
  constructor(
    public id?: number,
    public description?: string,
    public dateReception?: Moment,
    public dateCloture?: Moment,
    public dateReponse?: Moment,
    public action?: string,
    public ao?: string,
    public montant?: string,
    public typeAer?: ITypeAer,
    public statut?: IStatut,
    public client?: IClient,
    public utilisateur?: IUtilisateur
  ) {}
}
