import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { IRv, Rv } from 'app/shared/model/rv.model';
import { RvService } from './rv.service';

@Component({
  selector: 'jhi-rv-update',
  templateUrl: './rv-update.component.html'
})
export class RvUpdateComponent implements OnInit {
  isSaving: boolean;
  dateRvDp: any;
  dateCreationDp: any;
  dateModificationDp: any;

  editForm = this.fb.group({
    id: [],
    client: [],
    agence: [],
    objet: [],
    dateRv: [],
    description: [],
    dateCreation: [],
    dateModification: [],
    commercial: []
  });

  constructor(protected rvService: RvService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ rv }) => {
      this.updateForm(rv);
    });
  }

  updateForm(rv: IRv) {
    this.editForm.patchValue({
      id: rv.id,
      client: rv.client,
      agence: rv.agence,
      objet: rv.objet,
      dateRv: rv.dateRv,
      description: rv.description,
      dateCreation: rv.dateCreation,
      dateModification: rv.dateModification,
      commercial: rv.commercial
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const rv = this.createFromForm();
    if (rv.id !== undefined) {
      this.subscribeToSaveResponse(this.rvService.update(rv));
    } else {
      this.subscribeToSaveResponse(this.rvService.create(rv));
    }
  }

  private createFromForm(): IRv {
    return {
      ...new Rv(),
      id: this.editForm.get(['id']).value,
      client: this.editForm.get(['client']).value,
      agence: this.editForm.get(['agence']).value,
      objet: this.editForm.get(['objet']).value,
      dateRv: this.editForm.get(['dateRv']).value,
      description: this.editForm.get(['description']).value,
      dateCreation: this.editForm.get(['dateCreation']).value,
      dateModification: this.editForm.get(['dateModification']).value,
      commercial: this.editForm.get(['commercial']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRv>>) {
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
