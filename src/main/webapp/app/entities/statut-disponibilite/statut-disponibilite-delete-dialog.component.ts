import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IStatutDisponibilite } from 'app/shared/model/statut-disponibilite.model';
import { StatutDisponibiliteService } from './statut-disponibilite.service';

@Component({
  selector: 'jhi-statut-disponibilite-delete-dialog',
  templateUrl: './statut-disponibilite-delete-dialog.component.html'
})
export class StatutDisponibiliteDeleteDialogComponent {
  statutDisponibilite: IStatutDisponibilite;

  constructor(
    protected statutDisponibiliteService: StatutDisponibiliteService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.statutDisponibiliteService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'statutDisponibiliteListModification',
        content: 'Deleted an statutDisponibilite'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-statut-disponibilite-delete-popup',
  template: ''
})
export class StatutDisponibiliteDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ statutDisponibilite }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(StatutDisponibiliteDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.statutDisponibilite = statutDisponibilite;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/statut-disponibilite', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/statut-disponibilite', { outlets: { popup: null } }]);
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
