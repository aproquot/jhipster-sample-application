import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';
import { IAer, Aer } from 'app/shared/model/aer.model';
import { AerService } from './aer.service';
import { ITypeAer } from 'app/shared/model/type-aer.model';
import { TypeAerService } from 'app/entities/type-aer';
import { IStatut } from 'app/shared/model/statut.model';
import { StatutService } from 'app/entities/statut';
import { IClient } from 'app/shared/model/client.model';
import { ClientService } from 'app/entities/client';
import { IUtilisateur } from 'app/shared/model/utilisateur.model';
import { UtilisateurService } from 'app/entities/utilisateur';

@Component({
  selector: 'jhi-aer-update',
  templateUrl: './aer-update.component.html'
})
export class AerUpdateComponent implements OnInit {
  isSaving: boolean;

  typeaers: ITypeAer[];

  statuts: IStatut[];

  clients: IClient[];

  utilisateurs: IUtilisateur[];
  dateReceptionDp: any;
  dateClotureDp: any;
  dateReponseDp: any;

  editForm = this.fb.group({
    id: [],
    description: [],
    dateReception: [],
    dateCloture: [],
    dateReponse: [],
    action: [],
    ao: [],
    montant: [],
    typeAer: [],
    statut: [],
    client: [],
    utilisateur: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected aerService: AerService,
    protected typeAerService: TypeAerService,
    protected statutService: StatutService,
    protected clientService: ClientService,
    protected utilisateurService: UtilisateurService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ aer }) => {
      this.updateForm(aer);
    });
    this.typeAerService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<ITypeAer[]>) => mayBeOk.ok),
        map((response: HttpResponse<ITypeAer[]>) => response.body)
      )
      .subscribe((res: ITypeAer[]) => (this.typeaers = res), (res: HttpErrorResponse) => this.onError(res.message));
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

  updateForm(aer: IAer) {
    this.editForm.patchValue({
      id: aer.id,
      description: aer.description,
      dateReception: aer.dateReception,
      dateCloture: aer.dateCloture,
      dateReponse: aer.dateReponse,
      action: aer.action,
      ao: aer.ao,
      montant: aer.montant,
      typeAer: aer.typeAer,
      statut: aer.statut,
      client: aer.client,
      utilisateur: aer.utilisateur
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const aer = this.createFromForm();
    if (aer.id !== undefined) {
      this.subscribeToSaveResponse(this.aerService.update(aer));
    } else {
      this.subscribeToSaveResponse(this.aerService.create(aer));
    }
  }

  private createFromForm(): IAer {
    return {
      ...new Aer(),
      id: this.editForm.get(['id']).value,
      description: this.editForm.get(['description']).value,
      dateReception: this.editForm.get(['dateReception']).value,
      dateCloture: this.editForm.get(['dateCloture']).value,
      dateReponse: this.editForm.get(['dateReponse']).value,
      action: this.editForm.get(['action']).value,
      ao: this.editForm.get(['ao']).value,
      montant: this.editForm.get(['montant']).value,
      typeAer: this.editForm.get(['typeAer']).value,
      statut: this.editForm.get(['statut']).value,
      client: this.editForm.get(['client']).value,
      utilisateur: this.editForm.get(['utilisateur']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAer>>) {
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

  trackTypeAerById(index: number, item: ITypeAer) {
    return item.id;
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
