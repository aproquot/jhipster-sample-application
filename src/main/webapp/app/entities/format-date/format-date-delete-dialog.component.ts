import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFormatDate } from 'app/shared/model/format-date.model';
import { FormatDateService } from './format-date.service';

@Component({
  selector: 'jhi-format-date-delete-dialog',
  templateUrl: './format-date-delete-dialog.component.html'
})
export class FormatDateDeleteDialogComponent {
  formatDate: IFormatDate;

  constructor(
    protected formatDateService: FormatDateService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.formatDateService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'formatDateListModification',
        content: 'Deleted an formatDate'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-format-date-delete-popup',
  template: ''
})
export class FormatDateDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ formatDate }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(FormatDateDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.formatDate = formatDate;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/format-date', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/format-date', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          }
        );
      }, 0);
    });
  }

  ngOnDestroy() {
    this.ngbModalRef = null;
  }
}
