export type RegisterUserRequest = {
  name: string;
  age: number;
  email: string;
  state: string;
  userType: string;
  username: string;
  password: string;
  profilePicture?: string;
};

export type User = {
  id: string;
  name: string;
  age: number;
  email: string;
  state: string;
  userType: string;
  username: string;
  password: string;
  profilePicture?: string;
};
