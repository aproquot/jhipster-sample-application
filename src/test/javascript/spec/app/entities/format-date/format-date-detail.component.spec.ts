/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { FormatDateDetailComponent } from 'app/entities/format-date/format-date-detail.component';
import { FormatDate } from 'app/shared/model/format-date.model';

describe('Component Tests', () => {
  describe('FormatDate Management Detail Component', () => {
    let comp: FormatDateDetailComponent;
    let fixture: ComponentFixture<FormatDateDetailComponent>;
    const route = ({ data: of({ formatDate: new FormatDate(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [FormatDateDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(FormatDateDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(FormatDateDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.formatDate).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
