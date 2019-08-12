/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TypeAerDetailComponent } from 'app/entities/type-aer/type-aer-detail.component';
import { TypeAer } from 'app/shared/model/type-aer.model';

describe('Component Tests', () => {
  describe('TypeAer Management Detail Component', () => {
    let comp: TypeAerDetailComponent;
    let fixture: ComponentFixture<TypeAerDetailComponent>;
    const route = ({ data: of({ typeAer: new TypeAer(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TypeAerDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(TypeAerDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TypeAerDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.typeAer).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
