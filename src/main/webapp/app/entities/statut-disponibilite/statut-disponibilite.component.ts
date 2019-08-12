import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IStatutDisponibilite } from 'app/shared/model/statut-disponibilite.model';
import { AccountService } from 'app/core';
import { StatutDisponibiliteService } from './statut-disponibilite.service';

@Component({
  selector: 'jhi-statut-disponibilite',
  templateUrl: './statut-disponibilite.component.html'
})
export class StatutDisponibiliteComponent implements OnInit, OnDestroy {
  statutDisponibilites: IStatutDisponibilite[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected statutDisponibiliteService: StatutDisponibiliteService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.statutDisponibiliteService
      .query()
      .pipe(
        filter((res: HttpResponse<IStatutDisponibilite[]>) => res.ok),
        map((res: HttpResponse<IStatutDisponibilite[]>) => res.body)
      )
      .subscribe(
        (res: IStatutDisponibilite[]) => {
          this.statutDisponibilites = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInStatutDisponibilites();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IStatutDisponibilite) {
    return item.id;
  }

  registerChangeInStatutDisponibilites() {
    this.eventSubscriber = this.eventManager.subscribe('statutDisponibiliteListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
