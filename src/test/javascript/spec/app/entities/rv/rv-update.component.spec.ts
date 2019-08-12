/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { RvUpdateComponent } from 'app/entities/rv/rv-update.component';
import { RvService } from 'app/entities/rv/rv.service';
import { Rv } from 'app/shared/model/rv.model';

describe('Component Tests', () => {
  describe('Rv Management Update Component', () => {
    let comp: RvUpdateComponent;
    let fixture: ComponentFixture<RvUpdateComponent>;
    let service: RvService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [RvUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(RvUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(RvUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RvService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Rv(123);
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
        const entity = new Rv();
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
