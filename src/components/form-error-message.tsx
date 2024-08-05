interface FormErrorMessageProps {
  message: string;
}
export function FormErrorMessage({ message }: FormErrorMessageProps) {
  return <small className="-mt-3">{message}</small>;
}
