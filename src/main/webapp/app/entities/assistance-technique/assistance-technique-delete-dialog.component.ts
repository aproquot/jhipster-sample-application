import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAssistanceTechnique } from 'app/shared/model/assistance-technique.model';
import { AssistanceTechniqueService } from './assistance-technique.service';

@Component({
  selector: 'jhi-assistance-technique-delete-dialog',
  templateUrl: './assistance-technique-delete-dialog.component.html'
})
export class AssistanceTechniqueDeleteDialogComponent {
  assistanceTechnique: IAssistanceTechnique;

  constructor(
    protected assistanceTechniqueService: AssistanceTechniqueService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.assistanceTechniqueService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'assistanceTechniqueListModification',
        content: 'Deleted an assistanceTechnique'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-assistance-technique-delete-popup',
  template: ''
})
export class AssistanceTechniqueDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ assistanceTechnique }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(AssistanceTechniqueDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.assistanceTechnique = assistanceTechnique;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/assistance-technique', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/assistance-technique', { outlets: { popup: null } }]);
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
