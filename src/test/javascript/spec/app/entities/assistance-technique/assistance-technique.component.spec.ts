/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { AssistanceTechniqueComponent } from 'app/entities/assistance-technique/assistance-technique.component';
import { AssistanceTechniqueService } from 'app/entities/assistance-technique/assistance-technique.service';
import { AssistanceTechnique } from 'app/shared/model/assistance-technique.model';

describe('Component Tests', () => {
  describe('AssistanceTechnique Management Component', () => {
    let comp: AssistanceTechniqueComponent;
    let fixture: ComponentFixture<AssistanceTechniqueComponent>;
    let service: AssistanceTechniqueService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [AssistanceTechniqueComponent],
        providers: []
      })
        .overrideTemplate(AssistanceTechniqueComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AssistanceTechniqueComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AssistanceTechniqueService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new AssistanceTechnique(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.assistanceTechniques[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
