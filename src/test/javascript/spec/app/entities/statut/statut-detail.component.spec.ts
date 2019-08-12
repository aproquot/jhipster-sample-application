/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { StatutDetailComponent } from 'app/entities/statut/statut-detail.component';
import { Statut } from 'app/shared/model/statut.model';

describe('Component Tests', () => {
  describe('Statut Management Detail Component', () => {
    let comp: StatutDetailComponent;
    let fixture: ComponentFixture<StatutDetailComponent>;
    const route = ({ data: of({ statut: new Statut(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [StatutDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(StatutDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(StatutDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.statut).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
