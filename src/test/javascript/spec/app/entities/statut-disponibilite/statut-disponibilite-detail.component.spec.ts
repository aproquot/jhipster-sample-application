/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { StatutDisponibiliteDetailComponent } from 'app/entities/statut-disponibilite/statut-disponibilite-detail.component';
import { StatutDisponibilite } from 'app/shared/model/statut-disponibilite.model';

describe('Component Tests', () => {
  describe('StatutDisponibilite Management Detail Component', () => {
    let comp: StatutDisponibiliteDetailComponent;
    let fixture: ComponentFixture<StatutDisponibiliteDetailComponent>;
    const route = ({ data: of({ statutDisponibilite: new StatutDisponibilite(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [StatutDisponibiliteDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(StatutDisponibiliteDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(StatutDisponibiliteDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.statutDisponibilite).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
