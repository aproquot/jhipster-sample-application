/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TypeAerComponent } from 'app/entities/type-aer/type-aer.component';
import { TypeAerService } from 'app/entities/type-aer/type-aer.service';
import { TypeAer } from 'app/shared/model/type-aer.model';

describe('Component Tests', () => {
  describe('TypeAer Management Component', () => {
    let comp: TypeAerComponent;
    let fixture: ComponentFixture<TypeAerComponent>;
    let service: TypeAerService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TypeAerComponent],
        providers: []
      })
        .overrideTemplate(TypeAerComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TypeAerComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TypeAerService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TypeAer(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.typeAers[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
