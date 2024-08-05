import { ChevronRight } from "lucide-react";
import { Input } from "../components/input";
import Logo2x from "../assets/logo-2x";
import { Link, useNavigate } from "react-router-dom";
import { Controller, useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { z } from "zod";
import { useContext } from "react";
import { AuthContext } from "../context/auth-context";

const signInFormSchema = z.object({
  username: z.string(),
  password: z.string(),
});

type SignInFormSchema = z.infer<typeof signInFormSchema>;
export function LoginPage() {
  const navigate = useNavigate();
  const { isAdmin, signIn } = useContext(AuthContext);
  const handleSignIn = async (payload: SignInFormSchema) => {
    await signIn(payload);

    if (isAdmin) return navigate("/admin");

    navigate("/");
  };

  const {
    handleSubmit,
    control,
    formState: { errors },
  } = useForm<SignInFormSchema>({
    resolver: zodResolver(signInFormSchema),
  });
  return (
    <main className="h-screen w-screen grid gap-5 place-content-center ">
      <Logo2x className="mx-auto" />
      <h1 className="text-5xl font-extrabold">Login</h1>
      <form
        className=" w-[450px] flex flex-col gap-5"
        onSubmit={handleSubmit(handleSignIn)}
      >
        <Controller
          control={control}
          name="username"
          render={({ field }) => (
            <Input
              {...field}
              title="Nome de usuario"
              htmlFor="username"
              type="text"
              placeholder="Digite seu nome de usuario"
            />
          )}
        />
        <Controller
          control={control}
          name="password"
          render={({ field }) => (
            <Input
              {...field}
              title="Senha"
              htmlFor="password"
              type="password"
              placeholder="Digite sua senha"
            />
          )}
        />

        <button
          type="submit"
          className="mx-auto flex gap-1 items-center px-4 py-2 rounded-md hover:bg-stone-100"
        >
          <span className="font-bold">Entrar</span>
          <ChevronRight className="w-4 h-4" />
        </button>

        {errors && errors.root?.message}
      </form>
      <hr />
      <small className="mx-auto">
        Caso não tenha uma conta, cadastre-se clicando{" "}
        <Link to={"/register"} className="text-emerald-600 hover:underline">
          aqui
        </Link>
      </small>
    </main>
  );
}
