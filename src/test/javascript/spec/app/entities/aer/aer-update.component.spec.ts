/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { AerUpdateComponent } from 'app/entities/aer/aer-update.component';
import { AerService } from 'app/entities/aer/aer.service';
import { Aer } from 'app/shared/model/aer.model';

describe('Component Tests', () => {
  describe('Aer Management Update Component', () => {
    let comp: AerUpdateComponent;
    let fixture: ComponentFixture<AerUpdateComponent>;
    let service: AerService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [AerUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(AerUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AerUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AerService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Aer(123);
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
        const entity = new Aer();
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
