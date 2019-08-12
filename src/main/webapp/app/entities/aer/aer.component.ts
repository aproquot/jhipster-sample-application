import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IAer } from 'app/shared/model/aer.model';
import { AccountService } from 'app/core';
import { AerService } from './aer.service';

@Component({
  selector: 'jhi-aer',
  templateUrl: './aer.component.html'
})
export class AerComponent implements OnInit, OnDestroy {
  aers: IAer[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected aerService: AerService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.aerService
      .query()
      .pipe(
        filter((res: HttpResponse<IAer[]>) => res.ok),
        map((res: HttpResponse<IAer[]>) => res.body)
      )
      .subscribe(
        (res: IAer[]) => {
          this.aers = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInAers();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IAer) {
    return item.id;
  }

  registerChangeInAers() {
    this.eventSubscriber = this.eventManager.subscribe('aerListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
