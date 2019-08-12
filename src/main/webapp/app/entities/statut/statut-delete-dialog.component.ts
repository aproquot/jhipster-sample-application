import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IStatut } from 'app/shared/model/statut.model';
import { StatutService } from './statut.service';

@Component({
  selector: 'jhi-statut-delete-dialog',
  templateUrl: './statut-delete-dialog.component.html'
})
export class StatutDeleteDialogComponent {
  statut: IStatut;

  constructor(protected statutService: StatutService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.statutService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'statutListModification',
        content: 'Deleted an statut'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-statut-delete-popup',
  template: ''
})
export class StatutDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ statut }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(StatutDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.statut = statut;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/statut', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/statut', { outlets: { popup: null } }]);
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
