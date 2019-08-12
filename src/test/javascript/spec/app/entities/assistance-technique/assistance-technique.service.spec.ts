/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { AssistanceTechniqueService } from 'app/entities/assistance-technique/assistance-technique.service';
import { IAssistanceTechnique, AssistanceTechnique } from 'app/shared/model/assistance-technique.model';

describe('Service Tests', () => {
  describe('AssistanceTechnique Service', () => {
    let injector: TestBed;
    let service: AssistanceTechniqueService;
    let httpMock: HttpTestingController;
    let elemDefault: IAssistanceTechnique;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(AssistanceTechniqueService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new AssistanceTechnique(
        0,
        'AAAAAAA',
        currentDate,
        currentDate,
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            dateReception: currentDate.format(DATE_FORMAT),
            dateCloture: currentDate.format(DATE_FORMAT),
            dateReponse: currentDate.format(DATE_FORMAT),
            dateDemarrage: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: elemDefault });
      });

      it('should create a AssistanceTechnique', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateReception: currentDate.format(DATE_FORMAT),
            dateCloture: currentDate.format(DATE_FORMAT),
            dateReponse: currentDate.format(DATE_FORMAT),
            dateDemarrage: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            dateReception: currentDate,
            dateCloture: currentDate,
            dateReponse: currentDate,
            dateDemarrage: currentDate
          },
          returnedFromService
        );
        service
          .create(new AssistanceTechnique(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a AssistanceTechnique', async () => {
        const returnedFromService = Object.assign(
          {
            description: 'BBBBBB',
            dateReception: currentDate.format(DATE_FORMAT),
            dateCloture: currentDate.format(DATE_FORMAT),
            dateReponse: currentDate.format(DATE_FORMAT),
            action: 'BBBBBB',
            ao: 'BBBBBB',
            codeProfil: 'BBBBBB',
            tjm: 'BBBBBB',
            dateDemarrage: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateReception: currentDate,
            dateCloture: currentDate,
            dateReponse: currentDate,
            dateDemarrage: currentDate
          },
          returnedFromService
        );
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should return a list of AssistanceTechnique', async () => {
        const returnedFromService = Object.assign(
          {
            description: 'BBBBBB',
            dateReception: currentDate.format(DATE_FORMAT),
            dateCloture: currentDate.format(DATE_FORMAT),
            dateReponse: currentDate.format(DATE_FORMAT),
            action: 'BBBBBB',
            ao: 'BBBBBB',
            codeProfil: 'BBBBBB',
            tjm: 'BBBBBB',
            dateDemarrage: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            dateReception: currentDate,
            dateCloture: currentDate,
            dateReponse: currentDate,
            dateDemarrage: currentDate
          },
          returnedFromService
        );
        service
          .query(expected)
          .pipe(
            take(1),
            map(resp => resp.body)
          )
          .subscribe(body => (expectedResult = body));
        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a AssistanceTechnique', async () => {
        const rxPromise = service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
