/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { StatutDisponibiliteUpdateComponent } from 'app/entities/statut-disponibilite/statut-disponibilite-update.component';
import { StatutDisponibiliteService } from 'app/entities/statut-disponibilite/statut-disponibilite.service';
import { StatutDisponibilite } from 'app/shared/model/statut-disponibilite.model';

describe('Component Tests', () => {
  describe('StatutDisponibilite Management Update Component', () => {
    let comp: StatutDisponibiliteUpdateComponent;
    let fixture: ComponentFixture<StatutDisponibiliteUpdateComponent>;
    let service: StatutDisponibiliteService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [StatutDisponibiliteUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(StatutDisponibiliteUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(StatutDisponibiliteUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(StatutDisponibiliteService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new StatutDisponibilite(123);
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
        const entity = new StatutDisponibilite();
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
