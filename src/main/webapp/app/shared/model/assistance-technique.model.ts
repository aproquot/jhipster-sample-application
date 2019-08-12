import { Moment } from 'moment';
import { IStatut } from 'app/shared/model/statut.model';
import { IClient } from 'app/shared/model/client.model';
import { IUtilisateur } from 'app/shared/model/utilisateur.model';

export interface IAssistanceTechnique {
  id?: number;
  description?: string;
  dateReception?: Moment;
  dateCloture?: Moment;
  dateReponse?: Moment;
  action?: string;
  ao?: string;
  codeProfil?: string;
  tjm?: string;
  dateDemarrage?: Moment;
  statut?: IStatut;
  client?: IClient;
  utilisateur?: IUtilisateur;
}

export class AssistanceTechnique implements IAssistanceTechnique {
  constructor(
    public id?: number,
    public description?: string,
    public dateReception?: Moment,
    public dateCloture?: Moment,
    public dateReponse?: Moment,
    public action?: string,
    public ao?: string,
    public codeProfil?: string,
    public tjm?: string,
    public dateDemarrage?: Moment,
    public statut?: IStatut,
    public client?: IClient,
    public utilisateur?: IUtilisateur
  ) {}
}
