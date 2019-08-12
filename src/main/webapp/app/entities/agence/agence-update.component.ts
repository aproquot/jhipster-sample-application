import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IAgence, Agence } from 'app/shared/model/agence.model';
import { AgenceService } from './agence.service';

@Component({
  selector: 'jhi-agence-update',
  templateUrl: './agence-update.component.html'
})
export class AgenceUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    libelle: []
  });

  constructor(protected agenceService: AgenceService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ agence }) => {
      this.updateForm(agence);
    });
  }

  updateForm(agence: IAgence) {
    this.editForm.patchValue({
      id: agence.id,
      libelle: agence.libelle
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const agence = this.createFromForm();
    if (agence.id !== undefined) {
      this.subscribeToSaveResponse(this.agenceService.update(agence));
    } else {
      this.subscribeToSaveResponse(this.agenceService.create(agence));
    }
  }

  private createFromForm(): IAgence {
    return {
      ...new Agence(),
      id: this.editForm.get(['id']).value,
      libelle: this.editForm.get(['libelle']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAgence>>) {
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
