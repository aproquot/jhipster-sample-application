/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { AssistanceTechniqueDetailComponent } from 'app/entities/assistance-technique/assistance-technique-detail.component';
import { AssistanceTechnique } from 'app/shared/model/assistance-technique.model';

describe('Component Tests', () => {
  describe('AssistanceTechnique Management Detail Component', () => {
    let comp: AssistanceTechniqueDetailComponent;
    let fixture: ComponentFixture<AssistanceTechniqueDetailComponent>;
    const route = ({ data: of({ assistanceTechnique: new AssistanceTechnique(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [AssistanceTechniqueDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(AssistanceTechniqueDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AssistanceTechniqueDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.assistanceTechnique).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
