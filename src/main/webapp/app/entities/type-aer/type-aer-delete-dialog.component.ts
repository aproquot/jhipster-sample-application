import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITypeAer } from 'app/shared/model/type-aer.model';
import { TypeAerService } from './type-aer.service';

@Component({
  selector: 'jhi-type-aer-delete-dialog',
  templateUrl: './type-aer-delete-dialog.component.html'
})
export class TypeAerDeleteDialogComponent {
  typeAer: ITypeAer;

  constructor(protected typeAerService: TypeAerService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.typeAerService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'typeAerListModification',
        content: 'Deleted an typeAer'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-type-aer-delete-popup',
  template: ''
})
export class TypeAerDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ typeAer }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(TypeAerDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.typeAer = typeAer;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/type-aer', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/type-aer', { outlets: { popup: null } }]);
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
