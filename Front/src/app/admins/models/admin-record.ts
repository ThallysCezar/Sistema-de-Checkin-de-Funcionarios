export interface AdminRecord {
  id: number;
  employeeId: number;
  employeeName: string;
  checkInTime: Date | null;
  checkOutTime: Date | null;
  duration: string;
}
