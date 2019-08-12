import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IFormatDate, FormatDate } from 'app/shared/model/format-date.model';
import { FormatDateService } from './format-date.service';

@Component({
  selector: 'jhi-format-date-update',
  templateUrl: './format-date-update.component.html'
})
export class FormatDateUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    libelle: [],
    format: []
  });

  constructor(protected formatDateService: FormatDateService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ formatDate }) => {
      this.updateForm(formatDate);
    });
  }

  updateForm(formatDate: IFormatDate) {
    this.editForm.patchValue({
      id: formatDate.id,
      libelle: formatDate.libelle,
      format: formatDate.format
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const formatDate = this.createFromForm();
    if (formatDate.id !== undefined) {
      this.subscribeToSaveResponse(this.formatDateService.update(formatDate));
    } else {
      this.subscribeToSaveResponse(this.formatDateService.create(formatDate));
    }
  }

  private createFromForm(): IFormatDate {
    return {
      ...new FormatDate(),
      id: this.editForm.get(['id']).value,
      libelle: this.editForm.get(['libelle']).value,
      format: this.editForm.get(['format']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFormatDate>>) {
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
