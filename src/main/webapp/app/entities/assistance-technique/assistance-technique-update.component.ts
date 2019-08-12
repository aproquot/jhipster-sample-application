import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';
import { IAssistanceTechnique, AssistanceTechnique } from 'app/shared/model/assistance-technique.model';
import { AssistanceTechniqueService } from './assistance-technique.service';
import { IStatut } from 'app/shared/model/statut.model';
import { StatutService } from 'app/entities/statut';
import { IClient } from 'app/shared/model/client.model';
import { ClientService } from 'app/entities/client';
import { IUtilisateur } from 'app/shared/model/utilisateur.model';
import { UtilisateurService } from 'app/entities/utilisateur';

@Component({
  selector: 'jhi-assistance-technique-update',
  templateUrl: './assistance-technique-update.component.html'
})
export class AssistanceTechniqueUpdateComponent implements OnInit {
  isSaving: boolean;

  statuts: IStatut[];

  clients: IClient[];

  utilisateurs: IUtilisateur[];
  dateReceptionDp: any;
  dateClotureDp: any;
  dateReponseDp: any;
  dateDemarrageDp: any;

  editForm = this.fb.group({
    id: [],
    description: [],
    dateReception: [],
    dateCloture: [],
    dateReponse: [],
    action: [],
    ao: [],
    codeProfil: [],
    tjm: [],
    dateDemarrage: [],
    statut: [],
    client: [],
    utilisateur: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected assistanceTechniqueService: AssistanceTechniqueService,
    protected statutService: StatutService,
    protected clientService: ClientService,
    protected utilisateurService: UtilisateurService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ assistanceTechnique }) => {
      this.updateForm(assistanceTechnique);
    });
    this.statutService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IStatut[]>) => mayBeOk.ok),
        map((response: HttpResponse<IStatut[]>) => response.body)
      )
      .subscribe((res: IStatut[]) => (this.statuts = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.clientService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IClient[]>) => mayBeOk.ok),
        map((response: HttpResponse<IClient[]>) => response.body)
      )
      .subscribe((res: IClient[]) => (this.clients = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.utilisateurService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IUtilisateur[]>) => mayBeOk.ok),
        map((response: HttpResponse<IUtilisateur[]>) => response.body)
      )
      .subscribe((res: IUtilisateur[]) => (this.utilisateurs = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(assistanceTechnique: IAssistanceTechnique) {
    this.editForm.patchValue({
      id: assistanceTechnique.id,
      description: assistanceTechnique.description,
      dateReception: assistanceTechnique.dateReception,
      dateCloture: assistanceTechnique.dateCloture,
      dateReponse: assistanceTechnique.dateReponse,
      action: assistanceTechnique.action,
      ao: assistanceTechnique.ao,
      codeProfil: assistanceTechnique.codeProfil,
      tjm: assistanceTechnique.tjm,
      dateDemarrage: assistanceTechnique.dateDemarrage,
      statut: assistanceTechnique.statut,
      client: assistanceTechnique.client,
      utilisateur: assistanceTechnique.utilisateur
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const assistanceTechnique = this.createFromForm();
    if (assistanceTechnique.id !== undefined) {
      this.subscribeToSaveResponse(this.assistanceTechniqueService.update(assistanceTechnique));
    } else {
      this.subscribeToSaveResponse(this.assistanceTechniqueService.create(assistanceTechnique));
    }
  }

  private createFromForm(): IAssistanceTechnique {
    return {
      ...new AssistanceTechnique(),
      id: this.editForm.get(['id']).value,
      description: this.editForm.get(['description']).value,
      dateReception: this.editForm.get(['dateReception']).value,
      dateCloture: this.editForm.get(['dateCloture']).value,
      dateReponse: this.editForm.get(['dateReponse']).value,
      action: this.editForm.get(['action']).value,
      ao: this.editForm.get(['ao']).value,
      codeProfil: this.editForm.get(['codeProfil']).value,
      tjm: this.editForm.get(['tjm']).value,
      dateDemarrage: this.editForm.get(['dateDemarrage']).value,
      statut: this.editForm.get(['statut']).value,
      client: this.editForm.get(['client']).value,
      utilisateur: this.editForm.get(['utilisateur']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAssistanceTechnique>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackStatutById(index: number, item: IStatut) {
    return item.id;
  }

  trackClientById(index: number, item: IClient) {
    return item.id;
  }

  trackUtilisateurById(index: number, item: IUtilisateur) {
    return item.id;
  }
}
