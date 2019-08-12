/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { FormatDateComponent } from 'app/entities/format-date/format-date.component';
import { FormatDateService } from 'app/entities/format-date/format-date.service';
import { FormatDate } from 'app/shared/model/format-date.model';

describe('Component Tests', () => {
  describe('FormatDate Management Component', () => {
    let comp: FormatDateComponent;
    let fixture: ComponentFixture<FormatDateComponent>;
    let service: FormatDateService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [FormatDateComponent],
        providers: []
      })
        .overrideTemplate(FormatDateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FormatDateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FormatDateService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new FormatDate(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.formatDates[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
