/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TypeAerUpdateComponent } from 'app/entities/type-aer/type-aer-update.component';
import { TypeAerService } from 'app/entities/type-aer/type-aer.service';
import { TypeAer } from 'app/shared/model/type-aer.model';

describe('Component Tests', () => {
  describe('TypeAer Management Update Component', () => {
    let comp: TypeAerUpdateComponent;
    let fixture: ComponentFixture<TypeAerUpdateComponent>;
    let service: TypeAerService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TypeAerUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(TypeAerUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TypeAerUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TypeAerService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TypeAer(123);
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
        const entity = new TypeAer();
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
