import { IAer } from 'app/shared/model/aer.model';
import { IAssistanceTechnique } from 'app/shared/model/assistance-technique.model';
import { IRole } from 'app/shared/model/role.model';

export interface IUtilisateur {
  id?: number;
  login?: string;
  aers?: IAer[];
  assistanceTechniques?: IAssistanceTechnique[];
  role?: IRole;
}

export class Utilisateur implements IUtilisateur {
  constructor(
    public id?: number,
    public login?: string,
    public aers?: IAer[],
    public assistanceTechniques?: IAssistanceTechnique[],
    public role?: IRole
  ) {}
}
