import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRv } from 'app/shared/model/rv.model';
import { RvService } from './rv.service';

@Component({
  selector: 'jhi-rv-delete-dialog',
  templateUrl: './rv-delete-dialog.component.html'
})
export class RvDeleteDialogComponent {
  rv: IRv;

  constructor(protected rvService: RvService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.rvService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'rvListModification',
        content: 'Deleted an rv'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-rv-delete-popup',
  template: ''
})
export class RvDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ rv }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(RvDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.rv = rv;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/rv', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/rv', { outlets: { popup: null } }]);
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
