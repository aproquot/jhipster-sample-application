import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IUtilisateur } from 'app/shared/model/utilisateur.model';
import { AccountService } from 'app/core';
import { UtilisateurService } from './utilisateur.service';

@Component({
  selector: 'jhi-utilisateur',
  templateUrl: './utilisateur.component.html'
})
export class UtilisateurComponent implements OnInit, OnDestroy {
  utilisateurs: IUtilisateur[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected utilisateurService: UtilisateurService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.utilisateurService
      .query()
      .pipe(
        filter((res: HttpResponse<IUtilisateur[]>) => res.ok),
        map((res: HttpResponse<IUtilisateur[]>) => res.body)
      )
      .subscribe(
        (res: IUtilisateur[]) => {
          this.utilisateurs = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInUtilisateurs();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IUtilisateur) {
    return item.id;
  }

  registerChangeInUtilisateurs() {
    this.eventSubscriber = this.eventManager.subscribe('utilisateurListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
