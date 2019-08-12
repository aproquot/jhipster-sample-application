/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { AerComponent } from 'app/entities/aer/aer.component';
import { AerService } from 'app/entities/aer/aer.service';
import { Aer } from 'app/shared/model/aer.model';

describe('Component Tests', () => {
  describe('Aer Management Component', () => {
    let comp: AerComponent;
    let fixture: ComponentFixture<AerComponent>;
    let service: AerService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [AerComponent],
        providers: []
      })
        .overrideTemplate(AerComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AerComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AerService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Aer(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.aers[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
