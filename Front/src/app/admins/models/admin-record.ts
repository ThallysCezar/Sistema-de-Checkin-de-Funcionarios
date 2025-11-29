export interface AdminRecord {
  id: number;
  employeeId: number | string;
  checkInTime: Date | null;
  checkOutTime: Date | null;
  duration: string;
}
