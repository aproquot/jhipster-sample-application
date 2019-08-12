import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IUtilisateur, Utilisateur } from 'app/shared/model/utilisateur.model';
import { UtilisateurService } from './utilisateur.service';
import { IRole } from 'app/shared/model/role.model';
import { RoleService } from 'app/entities/role';

@Component({
  selector: 'jhi-utilisateur-update',
  templateUrl: './utilisateur-update.component.html'
})
export class UtilisateurUpdateComponent implements OnInit {
  isSaving: boolean;

  roles: IRole[];

  editForm = this.fb.group({
    id: [],
    login: [],
    role: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected utilisateurService: UtilisateurService,
    protected roleService: RoleService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ utilisateur }) => {
      this.updateForm(utilisateur);
    });
    this.roleService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IRole[]>) => mayBeOk.ok),
        map((response: HttpResponse<IRole[]>) => response.body)
      )
      .subscribe((res: IRole[]) => (this.roles = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(utilisateur: IUtilisateur) {
    this.editForm.patchValue({
      id: utilisateur.id,
      login: utilisateur.login,
      role: utilisateur.role
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const utilisateur = this.createFromForm();
    if (utilisateur.id !== undefined) {
      this.subscribeToSaveResponse(this.utilisateurService.update(utilisateur));
    } else {
      this.subscribeToSaveResponse(this.utilisateurService.create(utilisateur));
    }
  }

  private createFromForm(): IUtilisateur {
    return {
      ...new Utilisateur(),
      id: this.editForm.get(['id']).value,
      login: this.editForm.get(['login']).value,
      role: this.editForm.get(['role']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IUtilisateur>>) {
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

  trackRoleById(index: number, item: IRole) {
    return item.id;
  }
}
