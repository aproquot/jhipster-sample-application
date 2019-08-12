import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IAssistanceTechnique } from 'app/shared/model/assistance-technique.model';
import { AccountService } from 'app/core';
import { AssistanceTechniqueService } from './assistance-technique.service';

@Component({
  selector: 'jhi-assistance-technique',
  templateUrl: './assistance-technique.component.html'
})
export class AssistanceTechniqueComponent implements OnInit, OnDestroy {
  assistanceTechniques: IAssistanceTechnique[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected assistanceTechniqueService: AssistanceTechniqueService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.assistanceTechniqueService
      .query()
      .pipe(
        filter((res: HttpResponse<IAssistanceTechnique[]>) => res.ok),
        map((res: HttpResponse<IAssistanceTechnique[]>) => res.body)
      )
      .subscribe(
        (res: IAssistanceTechnique[]) => {
          this.assistanceTechniques = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInAssistanceTechniques();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IAssistanceTechnique) {
    return item.id;
  }

  registerChangeInAssistanceTechniques() {
    this.eventSubscriber = this.eventManager.subscribe('assistanceTechniqueListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
