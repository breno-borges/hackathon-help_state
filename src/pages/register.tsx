import { ChevronRight } from "lucide-react";
import { Input } from "../components/input";
import Logo2x from "../assets/logo-2x";
import { Link, useNavigate } from "react-router-dom";
import { useMutation } from "@tanstack/react-query";
import { z } from "zod";
import { Controller, useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { FormErrorMessage } from "../components/form-error-message";
import { RadixSelect } from "../components/select";
import { SelectItem } from "../components/select-item";
import { ufs } from "../common/constants";
import { RegisterUserRequest } from "../common/types";
import { useContext } from "react";
import { AuthContext } from "../context/auth-context";
const registerFormSchema = z.object({
  name: z.string(),
  age: z.coerce.number(),
  email: z.string().email(),
  username: z.string(),
  state: z.string(),
  password: z.string(),
  confirmPassword: z.string(),
});
type RegisterFormSchema = z.infer<typeof registerFormSchema>;
export function RegisterPage() {
  const { register } = useContext(AuthContext);
  const navigate = useNavigate();
  const {
    control,
    handleSubmit,
    formState: { errors },
  } = useForm<RegisterFormSchema>({
    resolver: zodResolver(registerFormSchema),
  });
  const { mutateAsync: createUser, isPending: isCreatingUser } = useMutation({
    mutationFn: async (payload: RegisterUserRequest) => await register(payload),
  });

  const handleSignIn = async (payload: RegisterFormSchema) => {
    if (payload.password !== payload.confirmPassword) return alert("erro");
    alert(JSON.stringify(payload));
    await createUser({
      name: payload.name,
      age: payload.age,
      email: payload.email,
      password: payload.password,
      state: payload.state,
      username: payload.username,
      userType: "CITIZEN",
    });
    navigate("/login");
  };
  return (
    <main className="h-screen w-screen grid gap-5 place-content-center ">
      <Logo2x className="mx-auto" />
      <h1 className="text-5xl font-extrabold">Cadastro</h1>
      <form
        className=" w-[550px] flex flex-col gap-5"
        onSubmit={handleSubmit(handleSignIn)}
      >
        <div className="flex gap-4">
          <Controller
            control={control}
            name="name"
            render={({ field }) => (
              <Input
                {...field}
                title="Nome Completo"
                htmlFor="name"
                type="name"
                placeholder="Digite seu nome"
              />
            )}
          />
          {errors.name && <FormErrorMessage message={errors.name?.message!} />}
          <Controller
            control={control}
            name="username"
            render={({ field }) => (
              <Input
                {...field}
                title="Nickname"
                htmlFor="username"
                type="username"
                placeholder="Digite um nome de usuario"
              />
            )}
          />
          {errors.username && (
            <FormErrorMessage message={errors.username?.message!} />
          )}
        </div>

        <div className="flex gap-4">
          <Controller
            control={control}
            name="email"
            render={({ field }) => (
              <Input
                {...field}
                title="E-mail"
                htmlFor="email"
                type="email"
                placeholder="Digite seu e-mail"
              />
            )}
          />
          {errors.email && (
            <FormErrorMessage message={errors.email?.message!} />
          )}
          <Controller
            control={control}
            name="age"
            render={({ field }) => (
              <span>
                <Input
                  {...field}
                  title="Idade"
                  htmlFor="age"
                  type="number"
                  placeholder="Digite sua idade"
                />
                {errors.age && (
                  <FormErrorMessage message={errors.age?.message!} />
                )}
              </span>
            )}
          />
        </div>
        <Controller
          control={control}
          name="state"
          render={({ field }) => {
            return (
              <div className="flex flex-col gap-2">
                <label htmlFor="state" className="font-bold">
                  Estado
                </label>
                <RadixSelect
                  {...field}
                  name="state"
                  placeholder="Selecione um estado"
                  onValueChange={field.onChange}
                >
                  {ufs?.map((uf, index) => (
                    <SelectItem key={index} value={uf}>
                      {uf}
                    </SelectItem>
                  ))}
                </RadixSelect>
              </div>
            );
          }}
        />
        {errors.state && <FormErrorMessage message={errors.state?.message!} />}

        <div className="flex gap-4">
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
          {errors.password && (
            <FormErrorMessage message={errors.password?.message!} />
          )}
          <Controller
            control={control}
            name="confirmPassword"
            render={({ field }) => (
              <Input
                {...field}
                title="Confirmar Senha"
                htmlFor="confirm-password"
                type="password"
                placeholder="Digite sua senha novamente"
              />
            )}
          />
          {errors.confirmPassword && (
            <FormErrorMessage message={errors.confirmPassword?.message!} />
          )}
        </div>
        <button
          type="submit"
          disabled={isCreatingUser}
          className="mx-auto flex gap-1 items-center px-4 py-2 rounded-md hover:bg-stone-100"
        >
          <span className="font-bold">Cadastrar-se</span>
          <ChevronRight className="w-4 h-4" />
        </button>
      </form>
      <hr />
      <small className="mx-auto">
        Já tem uma conta? Faça login clicando{" "}
        <Link to={"/login"} className="text-emerald-600 hover:underline">
          aqui
        </Link>
      </small>
    </main>
  );
}

/**
 * 

 */
