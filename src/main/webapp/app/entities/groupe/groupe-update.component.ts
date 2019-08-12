import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IGroupe, Groupe } from 'app/shared/model/groupe.model';
import { GroupeService } from './groupe.service';

@Component({
  selector: 'jhi-groupe-update',
  templateUrl: './groupe-update.component.html'
})
export class GroupeUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    libelle: []
  });

  constructor(protected groupeService: GroupeService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ groupe }) => {
      this.updateForm(groupe);
    });
  }

  updateForm(groupe: IGroupe) {
    this.editForm.patchValue({
      id: groupe.id,
      libelle: groupe.libelle
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const groupe = this.createFromForm();
    if (groupe.id !== undefined) {
      this.subscribeToSaveResponse(this.groupeService.update(groupe));
    } else {
      this.subscribeToSaveResponse(this.groupeService.create(groupe));
    }
  }

  private createFromForm(): IGroupe {
    return {
      ...new Groupe(),
      id: this.editForm.get(['id']).value,
      libelle: this.editForm.get(['libelle']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGroupe>>) {
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
