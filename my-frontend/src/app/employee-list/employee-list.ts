import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { RouterModule } from '@angular/router';

export interface Employee {
  id: number;
  name: string;
  position: string;
  department: string;
}

@Component({
  selector: 'app-employee-list',
  standalone: true,
  imports: [CommonModule, HttpClientModule, MatTableModule, MatButtonModule, MatIconModule, RouterModule],
  templateUrl: './employee-list.html',
  styleUrls: ['./employee-list.css']
})
export class EmployeeList {
  employees: Employee[] = [];
  displayedColumns: string[] = ['id', 'name', 'position', 'department'];

  constructor(private http: HttpClient) {}

  loadEmployees() {
    this.http.get<Employee[]>('http://backend:8080/api/employees')
      .subscribe({
        next: (data) => {
          this.employees = data;
        },
        error: (err) => {
          console.error('Error fetching employees:', err);
          alert('Failed to load employees!');
        }
      });
  }
}
