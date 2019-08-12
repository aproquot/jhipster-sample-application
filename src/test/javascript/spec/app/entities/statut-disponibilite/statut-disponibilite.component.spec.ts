/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { StatutDisponibiliteComponent } from 'app/entities/statut-disponibilite/statut-disponibilite.component';
import { StatutDisponibiliteService } from 'app/entities/statut-disponibilite/statut-disponibilite.service';
import { StatutDisponibilite } from 'app/shared/model/statut-disponibilite.model';

describe('Component Tests', () => {
  describe('StatutDisponibilite Management Component', () => {
    let comp: StatutDisponibiliteComponent;
    let fixture: ComponentFixture<StatutDisponibiliteComponent>;
    let service: StatutDisponibiliteService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [StatutDisponibiliteComponent],
        providers: []
      })
        .overrideTemplate(StatutDisponibiliteComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(StatutDisponibiliteComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(StatutDisponibiliteService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new StatutDisponibilite(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.statutDisponibilites[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
