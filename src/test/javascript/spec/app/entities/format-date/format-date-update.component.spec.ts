/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { FormatDateUpdateComponent } from 'app/entities/format-date/format-date-update.component';
import { FormatDateService } from 'app/entities/format-date/format-date.service';
import { FormatDate } from 'app/shared/model/format-date.model';

describe('Component Tests', () => {
  describe('FormatDate Management Update Component', () => {
    let comp: FormatDateUpdateComponent;
    let fixture: ComponentFixture<FormatDateUpdateComponent>;
    let service: FormatDateService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [FormatDateUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(FormatDateUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FormatDateUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FormatDateService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new FormatDate(123);
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
        const entity = new FormatDate();
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
