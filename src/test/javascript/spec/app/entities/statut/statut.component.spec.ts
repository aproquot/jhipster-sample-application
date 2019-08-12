/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { StatutComponent } from 'app/entities/statut/statut.component';
import { StatutService } from 'app/entities/statut/statut.service';
import { Statut } from 'app/shared/model/statut.model';

describe('Component Tests', () => {
  describe('Statut Management Component', () => {
    let comp: StatutComponent;
    let fixture: ComponentFixture<StatutComponent>;
    let service: StatutService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [StatutComponent],
        providers: []
      })
        .overrideTemplate(StatutComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(StatutComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(StatutService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Statut(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.statuts[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
