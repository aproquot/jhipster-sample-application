import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAer } from 'app/shared/model/aer.model';

type EntityResponseType = HttpResponse<IAer>;
type EntityArrayResponseType = HttpResponse<IAer[]>;

@Injectable({ providedIn: 'root' })
export class AerService {
  public resourceUrl = SERVER_API_URL + 'api/aers';

  constructor(protected http: HttpClient) {}

  create(aer: IAer): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(aer);
    return this.http
      .post<IAer>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(aer: IAer): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(aer);
    return this.http
      .put<IAer>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IAer>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IAer[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(aer: IAer): IAer {
    const copy: IAer = Object.assign({}, aer, {
      dateReception: aer.dateReception != null && aer.dateReception.isValid() ? aer.dateReception.format(DATE_FORMAT) : null,
      dateCloture: aer.dateCloture != null && aer.dateCloture.isValid() ? aer.dateCloture.format(DATE_FORMAT) : null,
      dateReponse: aer.dateReponse != null && aer.dateReponse.isValid() ? aer.dateReponse.format(DATE_FORMAT) : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateReception = res.body.dateReception != null ? moment(res.body.dateReception) : null;
      res.body.dateCloture = res.body.dateCloture != null ? moment(res.body.dateCloture) : null;
      res.body.dateReponse = res.body.dateReponse != null ? moment(res.body.dateReponse) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((aer: IAer) => {
        aer.dateReception = aer.dateReception != null ? moment(aer.dateReception) : null;
        aer.dateCloture = aer.dateCloture != null ? moment(aer.dateCloture) : null;
        aer.dateReponse = aer.dateReponse != null ? moment(aer.dateReponse) : null;
      });
    }
    return res;
  }
}
