import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { ITypeAer, TypeAer } from 'app/shared/model/type-aer.model';
import { TypeAerService } from './type-aer.service';

@Component({
  selector: 'jhi-type-aer-update',
  templateUrl: './type-aer-update.component.html'
})
export class TypeAerUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    libelle: []
  });

  constructor(protected typeAerService: TypeAerService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ typeAer }) => {
      this.updateForm(typeAer);
    });
  }

  updateForm(typeAer: ITypeAer) {
    this.editForm.patchValue({
      id: typeAer.id,
      libelle: typeAer.libelle
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const typeAer = this.createFromForm();
    if (typeAer.id !== undefined) {
      this.subscribeToSaveResponse(this.typeAerService.update(typeAer));
    } else {
      this.subscribeToSaveResponse(this.typeAerService.create(typeAer));
    }
  }

  private createFromForm(): ITypeAer {
    return {
      ...new TypeAer(),
      id: this.editForm.get(['id']).value,
      libelle: this.editForm.get(['libelle']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITypeAer>>) {
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
