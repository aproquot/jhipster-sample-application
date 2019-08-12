import { IAer } from 'app/shared/model/aer.model';
import { IAssistanceTechnique } from 'app/shared/model/assistance-technique.model';

export interface IStatut {
  id?: number;
  libelle?: string;
  ordonnance?: number;
  aers?: IAer[];
  assistanceTechniques?: IAssistanceTechnique[];
}

export class Statut implements IStatut {
  constructor(
    public id?: number,
    public libelle?: string,
    public ordonnance?: number,
    public aers?: IAer[],
    public assistanceTechniques?: IAssistanceTechnique[]
  ) {}
}
