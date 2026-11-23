export interface User {
  id: number;
  firstName: string;
  lastName: string;
  email: string;
  profileImage: string | null;
  active: boolean;
}
export interface UsersResponse {
  status: string;
  data: User[];
  message: string;
  timestamp: string;
}
