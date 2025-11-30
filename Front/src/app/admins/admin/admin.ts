import { AdminService } from './../services/admin.service';
import { AdminRecord } from '../models/admin-record';
import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin',
  standalone: false,
  templateUrl: './admin.html',
  styleUrls: ['./admin.scss'],
})
export class Admin implements OnInit, AfterViewInit {
  dataSource = new MatTableDataSource<AdminRecord>([]);
  @ViewChild(MatPaginator) paginator: MatPaginator | null = null;

  constructor(private adminService: AdminService, private router: Router) {}


  readonly columnMap = Object.freeze({
    id: {
      label: 'ID',
      description: 'Record ID',
    },
    employeeName: {
      label: 'Employee Name',
      description: 'Employee Name',
    },
    checkInTime: {
      label: 'Check-in Time',
      description: 'Check-in Time',
    },
    checkOutTime: {
      label: 'Check-out Time',
      description: 'Check-out Time',
    },
    duration: {
      label: 'Duration',
      description: 'Total Duration',
    },
  });

  displayedColumns = Object.keys(this.columnMap) as string[];

  ngOnInit(): void {
    this.adminService.listAll().subscribe((data) => {
      this.dataSource.data = data;
      if (this.paginator) {
        this.dataSource.paginator = this.paginator;
      }
    });
  }

  ngAfterViewInit(): void {
    if (this.paginator) {
      this.dataSource.paginator = this.paginator;
    }
  }

  goToCheckin() {
    this.router.navigate(['/checkins']);
  }
}
