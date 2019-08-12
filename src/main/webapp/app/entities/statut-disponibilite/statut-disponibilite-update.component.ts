import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IStatutDisponibilite, StatutDisponibilite } from 'app/shared/model/statut-disponibilite.model';
import { StatutDisponibiliteService } from './statut-disponibilite.service';
import { IDisponibilite } from 'app/shared/model/disponibilite.model';
import { DisponibiliteService } from 'app/entities/disponibilite';

@Component({
  selector: 'jhi-statut-disponibilite-update',
  templateUrl: './statut-disponibilite-update.component.html'
})
export class StatutDisponibiliteUpdateComponent implements OnInit {
  isSaving: boolean;

  disponibilites: IDisponibilite[];

  editForm = this.fb.group({
    id: [],
    libelle: [],
    disponibilite: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected statutDisponibiliteService: StatutDisponibiliteService,
    protected disponibiliteService: DisponibiliteService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ statutDisponibilite }) => {
      this.updateForm(statutDisponibilite);
    });
    this.disponibiliteService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IDisponibilite[]>) => mayBeOk.ok),
        map((response: HttpResponse<IDisponibilite[]>) => response.body)
      )
      .subscribe((res: IDisponibilite[]) => (this.disponibilites = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(statutDisponibilite: IStatutDisponibilite) {
    this.editForm.patchValue({
      id: statutDisponibilite.id,
      libelle: statutDisponibilite.libelle,
      disponibilite: statutDisponibilite.disponibilite
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const statutDisponibilite = this.createFromForm();
    if (statutDisponibilite.id !== undefined) {
      this.subscribeToSaveResponse(this.statutDisponibiliteService.update(statutDisponibilite));
    } else {
      this.subscribeToSaveResponse(this.statutDisponibiliteService.create(statutDisponibilite));
    }
  }

  private createFromForm(): IStatutDisponibilite {
    return {
      ...new StatutDisponibilite(),
      id: this.editForm.get(['id']).value,
      libelle: this.editForm.get(['libelle']).value,
      disponibilite: this.editForm.get(['disponibilite']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IStatutDisponibilite>>) {
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

  trackDisponibiliteById(index: number, item: IDisponibilite) {
    return item.id;
  }
}
