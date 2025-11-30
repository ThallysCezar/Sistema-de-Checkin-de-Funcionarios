import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { AdminRecord } from '../models/admin-record';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class AdminService {
  constructor(private httpClient: HttpClient) {}

  private readonly apiUrl = `${environment.apiUrl}/work`;

  listAll(): Observable<AdminRecord[]> {
    return this.httpClient.get<any[]>(this.apiUrl).pipe(
      map(items => items.map(i => ({
        id: i.id,
        employeeId: i.employeeId,
        employeeName: i.employeeName,
        checkInTime: i.checkInTime ? new Date(i.checkInTime) : null,
        checkOutTime: i.checkOutTime ? new Date(i.checkOutTime) : null,
        duration: i.duration,
      }) as AdminRecord))
    );
  }
}
