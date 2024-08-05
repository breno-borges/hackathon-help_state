export function SuccessBadge() {
  return (
    <div className="bg-green-700 border border-green-950 hover:opacity-90 w-24 hover:cursor-pointer flex gap-2 items-center px-2 py-1 rounded-[20px] shadow-lg">
      <div className="bg-green-400 rounded-full h-3 w-3" />
      <span className="text-sm font-bold text-stone-50">Tudo Ok</span>
    </div>
  );
}
