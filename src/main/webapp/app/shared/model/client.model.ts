import { IAer } from 'app/shared/model/aer.model';
import { IAssistanceTechnique } from 'app/shared/model/assistance-technique.model';
import { IAgence } from 'app/shared/model/agence.model';
import { IGroupe } from 'app/shared/model/groupe.model';

export interface IClient {
  id?: number;
  libelle?: string;
  aers?: IAer[];
  assistanceTechniques?: IAssistanceTechnique[];
  agence?: IAgence;
  groupe?: IGroupe;
}

export class Client implements IClient {
  constructor(
    public id?: number,
    public libelle?: string,
    public aers?: IAer[],
    public assistanceTechniques?: IAssistanceTechnique[],
    public agence?: IAgence,
    public groupe?: IGroupe
  ) {}
}
