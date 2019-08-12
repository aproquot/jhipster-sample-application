import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAssistanceTechnique } from 'app/shared/model/assistance-technique.model';

type EntityResponseType = HttpResponse<IAssistanceTechnique>;
type EntityArrayResponseType = HttpResponse<IAssistanceTechnique[]>;

@Injectable({ providedIn: 'root' })
export class AssistanceTechniqueService {
  public resourceUrl = SERVER_API_URL + 'api/assistance-techniques';

  constructor(protected http: HttpClient) {}

  create(assistanceTechnique: IAssistanceTechnique): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(assistanceTechnique);
    return this.http
      .post<IAssistanceTechnique>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(assistanceTechnique: IAssistanceTechnique): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(assistanceTechnique);
    return this.http
      .put<IAssistanceTechnique>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IAssistanceTechnique>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IAssistanceTechnique[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(assistanceTechnique: IAssistanceTechnique): IAssistanceTechnique {
    const copy: IAssistanceTechnique = Object.assign({}, assistanceTechnique, {
      dateReception:
        assistanceTechnique.dateReception != null && assistanceTechnique.dateReception.isValid()
          ? assistanceTechnique.dateReception.format(DATE_FORMAT)
          : null,
      dateCloture:
        assistanceTechnique.dateCloture != null && assistanceTechnique.dateCloture.isValid()
          ? assistanceTechnique.dateCloture.format(DATE_FORMAT)
          : null,
      dateReponse:
        assistanceTechnique.dateReponse != null && assistanceTechnique.dateReponse.isValid()
          ? assistanceTechnique.dateReponse.format(DATE_FORMAT)
          : null,
      dateDemarrage:
        assistanceTechnique.dateDemarrage != null && assistanceTechnique.dateDemarrage.isValid()
          ? assistanceTechnique.dateDemarrage.format(DATE_FORMAT)
          : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateReception = res.body.dateReception != null ? moment(res.body.dateReception) : null;
      res.body.dateCloture = res.body.dateCloture != null ? moment(res.body.dateCloture) : null;
      res.body.dateReponse = res.body.dateReponse != null ? moment(res.body.dateReponse) : null;
      res.body.dateDemarrage = res.body.dateDemarrage != null ? moment(res.body.dateDemarrage) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((assistanceTechnique: IAssistanceTechnique) => {
        assistanceTechnique.dateReception = assistanceTechnique.dateReception != null ? moment(assistanceTechnique.dateReception) : null;
        assistanceTechnique.dateCloture = assistanceTechnique.dateCloture != null ? moment(assistanceTechnique.dateCloture) : null;
        assistanceTechnique.dateReponse = assistanceTechnique.dateReponse != null ? moment(assistanceTechnique.dateReponse) : null;
        assistanceTechnique.dateDemarrage = assistanceTechnique.dateDemarrage != null ? moment(assistanceTechnique.dateDemarrage) : null;
      });
    }
    return res;
  }
}
