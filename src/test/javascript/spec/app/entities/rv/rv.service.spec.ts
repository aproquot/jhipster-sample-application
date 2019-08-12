/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { RvService } from 'app/entities/rv/rv.service';
import { IRv, Rv } from 'app/shared/model/rv.model';

describe('Service Tests', () => {
  describe('Rv Service', () => {
    let injector: TestBed;
    let service: RvService;
    let httpMock: HttpTestingController;
    let elemDefault: IRv;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(RvService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Rv(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', currentDate, 'AAAAAAA', currentDate, currentDate, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            dateRv: currentDate.format(DATE_FORMAT),
            dateCreation: currentDate.format(DATE_FORMAT),
            dateModification: currentDate.format(DATE_FORMAT)
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

      it('should create a Rv', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateRv: currentDate.format(DATE_FORMAT),
            dateCreation: currentDate.format(DATE_FORMAT),
            dateModification: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            dateRv: currentDate,
            dateCreation: currentDate,
            dateModification: currentDate
          },
          returnedFromService
        );
        service
          .create(new Rv(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a Rv', async () => {
        const returnedFromService = Object.assign(
          {
            client: 'BBBBBB',
            agence: 'BBBBBB',
            objet: 'BBBBBB',
            dateRv: currentDate.format(DATE_FORMAT),
            description: 'BBBBBB',
            dateCreation: currentDate.format(DATE_FORMAT),
            dateModification: currentDate.format(DATE_FORMAT),
            commercial: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateRv: currentDate,
            dateCreation: currentDate,
            dateModification: currentDate
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

      it('should return a list of Rv', async () => {
        const returnedFromService = Object.assign(
          {
            client: 'BBBBBB',
            agence: 'BBBBBB',
            objet: 'BBBBBB',
            dateRv: currentDate.format(DATE_FORMAT),
            description: 'BBBBBB',
            dateCreation: currentDate.format(DATE_FORMAT),
            dateModification: currentDate.format(DATE_FORMAT),
            commercial: 'BBBBBB'
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            dateRv: currentDate,
            dateCreation: currentDate,
            dateModification: currentDate
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

      it('should delete a Rv', async () => {
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
