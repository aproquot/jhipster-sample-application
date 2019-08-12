import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IFormatDate } from 'app/shared/model/format-date.model';
import { AccountService } from 'app/core';
import { FormatDateService } from './format-date.service';

@Component({
  selector: 'jhi-format-date',
  templateUrl: './format-date.component.html'
})
export class FormatDateComponent implements OnInit, OnDestroy {
  formatDates: IFormatDate[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected formatDateService: FormatDateService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.formatDateService
      .query()
      .pipe(
        filter((res: HttpResponse<IFormatDate[]>) => res.ok),
        map((res: HttpResponse<IFormatDate[]>) => res.body)
      )
      .subscribe(
        (res: IFormatDate[]) => {
          this.formatDates = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInFormatDates();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IFormatDate) {
    return item.id;
  }

  registerChangeInFormatDates() {
    this.eventSubscriber = this.eventManager.subscribe('formatDateListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
