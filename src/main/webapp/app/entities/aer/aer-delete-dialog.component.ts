import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAer } from 'app/shared/model/aer.model';
import { AerService } from './aer.service';

@Component({
  selector: 'jhi-aer-delete-dialog',
  templateUrl: './aer-delete-dialog.component.html'
})
export class AerDeleteDialogComponent {
  aer: IAer;

  constructor(protected aerService: AerService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.aerService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'aerListModification',
        content: 'Deleted an aer'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-aer-delete-popup',
  template: ''
})
export class AerDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ aer }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(AerDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.aer = aer;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/aer', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/aer', { outlets: { popup: null } }]);
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
