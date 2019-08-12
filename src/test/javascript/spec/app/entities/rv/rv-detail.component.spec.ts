/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { RvDetailComponent } from 'app/entities/rv/rv-detail.component';
import { Rv } from 'app/shared/model/rv.model';

describe('Component Tests', () => {
  describe('Rv Management Detail Component', () => {
    let comp: RvDetailComponent;
    let fixture: ComponentFixture<RvDetailComponent>;
    const route = ({ data: of({ rv: new Rv(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [RvDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(RvDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(RvDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.rv).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
