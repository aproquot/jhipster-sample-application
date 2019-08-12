/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { AssistanceTechniqueUpdateComponent } from 'app/entities/assistance-technique/assistance-technique-update.component';
import { AssistanceTechniqueService } from 'app/entities/assistance-technique/assistance-technique.service';
import { AssistanceTechnique } from 'app/shared/model/assistance-technique.model';

describe('Component Tests', () => {
  describe('AssistanceTechnique Management Update Component', () => {
    let comp: AssistanceTechniqueUpdateComponent;
    let fixture: ComponentFixture<AssistanceTechniqueUpdateComponent>;
    let service: AssistanceTechniqueService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [AssistanceTechniqueUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(AssistanceTechniqueUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AssistanceTechniqueUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AssistanceTechniqueService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new AssistanceTechnique(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new AssistanceTechnique();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
