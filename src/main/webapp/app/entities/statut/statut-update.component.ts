import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IStatut, Statut } from 'app/shared/model/statut.model';
import { StatutService } from './statut.service';

@Component({
  selector: 'jhi-statut-update',
  templateUrl: './statut-update.component.html'
})
export class StatutUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    libelle: [],
    ordonnance: []
  });

  constructor(protected statutService: StatutService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ statut }) => {
      this.updateForm(statut);
    });
  }

  updateForm(statut: IStatut) {
    this.editForm.patchValue({
      id: statut.id,
      libelle: statut.libelle,
      ordonnance: statut.ordonnance
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const statut = this.createFromForm();
    if (statut.id !== undefined) {
      this.subscribeToSaveResponse(this.statutService.update(statut));
    } else {
      this.subscribeToSaveResponse(this.statutService.create(statut));
    }
  }

  private createFromForm(): IStatut {
    return {
      ...new Statut(),
      id: this.editForm.get(['id']).value,
      libelle: this.editForm.get(['libelle']).value,
      ordonnance: this.editForm.get(['ordonnance']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IStatut>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
