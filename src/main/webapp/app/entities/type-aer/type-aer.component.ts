import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ITypeAer } from 'app/shared/model/type-aer.model';
import { AccountService } from 'app/core';
import { TypeAerService } from './type-aer.service';

@Component({
  selector: 'jhi-type-aer',
  templateUrl: './type-aer.component.html'
})
export class TypeAerComponent implements OnInit, OnDestroy {
  typeAers: ITypeAer[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected typeAerService: TypeAerService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.typeAerService
      .query()
      .pipe(
        filter((res: HttpResponse<ITypeAer[]>) => res.ok),
        map((res: HttpResponse<ITypeAer[]>) => res.body)
      )
      .subscribe(
        (res: ITypeAer[]) => {
          this.typeAers = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInTypeAers();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: ITypeAer) {
    return item.id;
  }

  registerChangeInTypeAers() {
    this.eventSubscriber = this.eventManager.subscribe('typeAerListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
