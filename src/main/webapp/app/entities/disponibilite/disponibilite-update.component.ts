import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';
import { IDisponibilite, Disponibilite } from 'app/shared/model/disponibilite.model';
import { DisponibiliteService } from './disponibilite.service';
import { IAgence } from 'app/shared/model/agence.model';
import { AgenceService } from 'app/entities/agence';

@Component({
  selector: 'jhi-disponibilite-update',
  templateUrl: './disponibilite-update.component.html'
})
export class DisponibiliteUpdateComponent implements OnInit {
  isSaving: boolean;

  agences: IAgence[];
  dateDp: any;

  editForm = this.fb.group({
    id: [],
    nom: [],
    prenom: [],
    date: [],
    competence: [],
    logement: [],
    occupation: [],
    affaireEnVue: [],
    icDomicile: [],
    agence: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected disponibiliteService: DisponibiliteService,
    protected agenceService: AgenceService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ disponibilite }) => {
      this.updateForm(disponibilite);
    });
    this.agenceService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IAgence[]>) => mayBeOk.ok),
        map((response: HttpResponse<IAgence[]>) => response.body)
      )
      .subscribe((res: IAgence[]) => (this.agences = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(disponibilite: IDisponibilite) {
    this.editForm.patchValue({
      id: disponibilite.id,
      nom: disponibilite.nom,
      prenom: disponibilite.prenom,
      date: disponibilite.date,
      competence: disponibilite.competence,
      logement: disponibilite.logement,
      occupation: disponibilite.occupation,
      affaireEnVue: disponibilite.affaireEnVue,
      icDomicile: disponibilite.icDomicile,
      agence: disponibilite.agence
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const disponibilite = this.createFromForm();
    if (disponibilite.id !== undefined) {
      this.subscribeToSaveResponse(this.disponibiliteService.update(disponibilite));
    } else {
      this.subscribeToSaveResponse(this.disponibiliteService.create(disponibilite));
    }
  }

  private createFromForm(): IDisponibilite {
    return {
      ...new Disponibilite(),
      id: this.editForm.get(['id']).value,
      nom: this.editForm.get(['nom']).value,
      prenom: this.editForm.get(['prenom']).value,
      date: this.editForm.get(['date']).value,
      competence: this.editForm.get(['competence']).value,
      logement: this.editForm.get(['logement']).value,
      occupation: this.editForm.get(['occupation']).value,
      affaireEnVue: this.editForm.get(['affaireEnVue']).value,
      icDomicile: this.editForm.get(['icDomicile']).value,
      agence: this.editForm.get(['agence']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDisponibilite>>) {
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
}
