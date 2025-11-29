import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

export interface WorkRecordDTO {
  id?: number;
  employeeId?: number;
  checkInTime?: string;
  checkOutTime?: string;
  duration?: string;
}

@Injectable({
  providedIn: 'root',
})
export class CheckinService {
  private readonly apiUrl = 'http://localhost:8080/work';

  constructor(private http: HttpClient) {}

  checkIn(employeeId: number): Observable<WorkRecordDTO> {
    return this.http.post<WorkRecordDTO>(`${this.apiUrl}/checkin`, { employeeId });
  }

  checkOut(employeeId: number): Observable<WorkRecordDTO> {
    return this.http.post<WorkRecordDTO>(`${this.apiUrl}/checkout`, { employeeId });
  }
}
