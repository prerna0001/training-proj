import { Injectable } from '@angular/core';
import { Employee } from './employee.model';

@Injectable({ providedIn: 'root' })
export class EmployeeService {
  private employees: Employee[] = [];

  addEmployee(employee: Employee) {
    this.employees.push(employee);
  }

  getEmployees(): Employee[] {
    return this.employees;
  }
}
