import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IFormatDate } from 'app/shared/model/format-date.model';

type EntityResponseType = HttpResponse<IFormatDate>;
type EntityArrayResponseType = HttpResponse<IFormatDate[]>;

@Injectable({ providedIn: 'root' })
export class FormatDateService {
  public resourceUrl = SERVER_API_URL + 'api/format-dates';

  constructor(protected http: HttpClient) {}

  create(formatDate: IFormatDate): Observable<EntityResponseType> {
    return this.http.post<IFormatDate>(this.resourceUrl, formatDate, { observe: 'response' });
  }

  update(formatDate: IFormatDate): Observable<EntityResponseType> {
    return this.http.put<IFormatDate>(this.resourceUrl, formatDate, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IFormatDate>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IFormatDate[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
