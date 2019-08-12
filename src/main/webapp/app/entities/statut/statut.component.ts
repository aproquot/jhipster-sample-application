import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IStatut } from 'app/shared/model/statut.model';
import { AccountService } from 'app/core';
import { StatutService } from './statut.service';

@Component({
  selector: 'jhi-statut',
  templateUrl: './statut.component.html'
})
export class StatutComponent implements OnInit, OnDestroy {
  statuts: IStatut[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected statutService: StatutService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.statutService
      .query()
      .pipe(
        filter((res: HttpResponse<IStatut[]>) => res.ok),
        map((res: HttpResponse<IStatut[]>) => res.body)
      )
      .subscribe(
        (res: IStatut[]) => {
          this.statuts = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInStatuts();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IStatut) {
    return item.id;
  }

  registerChangeInStatuts() {
    this.eventSubscriber = this.eventManager.subscribe('statutListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
