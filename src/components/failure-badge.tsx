interface FailureBadgeProps {
  message?: string;
}
export function FailureBadge({ message = "Perigo" }: FailureBadgeProps) {
  return (
    <div className="bg-red-600 border border-red-800 max-w-36 hover:opacity-90 hover:cursor-pointer flex gap-2 items-center px-2 py-1 rounded-[20px] shadow-lg">
      <div className="bg-red-400 rounded-full h-3 w-3" />
      <span className="text-sm font-bold text-stone-50">{message}</span>
    </div>
  );
}
