import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IClient, Client } from 'app/shared/model/client.model';
import { ClientService } from './client.service';
import { IAgence } from 'app/shared/model/agence.model';
import { AgenceService } from 'app/entities/agence';
import { IGroupe } from 'app/shared/model/groupe.model';
import { GroupeService } from 'app/entities/groupe';

@Component({
  selector: 'jhi-client-update',
  templateUrl: './client-update.component.html'
})
export class ClientUpdateComponent implements OnInit {
  isSaving: boolean;

  agences: IAgence[];

  groupes: IGroupe[];

  editForm = this.fb.group({
    id: [],
    libelle: [],
    agence: [],
    groupe: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected clientService: ClientService,
    protected agenceService: AgenceService,
    protected groupeService: GroupeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ client }) => {
      this.updateForm(client);
    });
    this.agenceService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IAgence[]>) => mayBeOk.ok),
        map((response: HttpResponse<IAgence[]>) => response.body)
      )
      .subscribe((res: IAgence[]) => (this.agences = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.groupeService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IGroupe[]>) => mayBeOk.ok),
        map((response: HttpResponse<IGroupe[]>) => response.body)
      )
      .subscribe((res: IGroupe[]) => (this.groupes = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(client: IClient) {
    this.editForm.patchValue({
      id: client.id,
      libelle: client.libelle,
      agence: client.agence,
      groupe: client.groupe
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const client = this.createFromForm();
    if (client.id !== undefined) {
      this.subscribeToSaveResponse(this.clientService.update(client));
    } else {
      this.subscribeToSaveResponse(this.clientService.create(client));
    }
  }

  private createFromForm(): IClient {
    return {
      ...new Client(),
      id: this.editForm.get(['id']).value,
      libelle: this.editForm.get(['libelle']).value,
      agence: this.editForm.get(['agence']).value,
      groupe: this.editForm.get(['groupe']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IClient>>) {
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

  trackAgenceById(index: number, item: IAgence) {
    return item.id;
  }

  trackGroupeById(index: number, item: IGroupe) {
    return item.id;
  }
}
