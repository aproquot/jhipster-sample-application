/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { AerDetailComponent } from 'app/entities/aer/aer-detail.component';
import { Aer } from 'app/shared/model/aer.model';

describe('Component Tests', () => {
  describe('Aer Management Detail Component', () => {
    let comp: AerDetailComponent;
    let fixture: ComponentFixture<AerDetailComponent>;
    const route = ({ data: of({ aer: new Aer(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [AerDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(AerDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AerDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.aer).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
