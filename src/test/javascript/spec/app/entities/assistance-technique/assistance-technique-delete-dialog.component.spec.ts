/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { AssistanceTechniqueDeleteDialogComponent } from 'app/entities/assistance-technique/assistance-technique-delete-dialog.component';
import { AssistanceTechniqueService } from 'app/entities/assistance-technique/assistance-technique.service';

describe('Component Tests', () => {
  describe('AssistanceTechnique Management Delete Component', () => {
    let comp: AssistanceTechniqueDeleteDialogComponent;
    let fixture: ComponentFixture<AssistanceTechniqueDeleteDialogComponent>;
    let service: AssistanceTechniqueService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [AssistanceTechniqueDeleteDialogComponent]
      })
        .overrideTemplate(AssistanceTechniqueDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AssistanceTechniqueDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AssistanceTechniqueService);
      mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
      mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
    });
  });
});
