import { z } from "zod";
import { Input } from "../../../components/input";
import { Controller, useForm, useWatch } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { RadixSelect } from "../../../components/select";
import { SelectItem } from "../../../components/select-item";
import {
  affectedArea,
  occurencesCategories,
  occurrencesSubCategories,
} from "../../../common/constants";
import { FormErrorMessage } from "../../../components/form-error-message";
const newOccurenceFormSchema = z.object({
  title: z.string().min(1, "Por favor, insira o titulo"),
  description: z.string().min(1, "Por favor, insira a descrição"),
  category: z.string().min(1),
  subCategory: z.string().min(1),
  affectedArea: z.string().min(1),
});
type NewOccurenceFormSchema = z.infer<typeof newOccurenceFormSchema>;

interface NewOccurencesFormProps {
  onClose: () => void;
}
export function NewOccurencesForm({ onClose }: NewOccurencesFormProps) {
  const {
    control,
    watch,
    handleSubmit,
    formState: { errors },
  } = useForm<NewOccurenceFormSchema>({
    resolver: zodResolver(newOccurenceFormSchema),
  });

  const categorySelected = watch("category");
  const subCategories = occurrencesSubCategories.filter(
    (sc) => sc.relationedOccurency === categorySelected
  );

  const handleCreateOccurency = (payload: NewOccurenceFormSchema) => {
    // mutation here
    alert(JSON.stringify(payload, null, 2));
    onClose();
  };
  return (
    <form
      className="flex flex-col gap-5"
      onSubmit={handleSubmit(handleCreateOccurency)}
    >
      <Controller
        control={control}
        name="title"
        render={({ field }) => (
          <Input
            {...field}
            htmlFor="occurence-title"
            title="Titulo da Ocorrencia"
            placeholder="Insira o titulo da ocorrencia"
          />
        )}
      />

      {errors.title && <FormErrorMessage message={errors.title.message!} />}

      <Controller
        control={control}
        name="description"
        render={({ field }) => (
          <Input
            {...field}
            htmlFor="occurence-description"
            title="Descrição da Ocorrencia"
            placeholder="Insira o descrição da ocorrencia"
          />
        )}
      />

      {errors.description && (
        <FormErrorMessage message={errors.description.message!} />
      )}
      <Controller
        control={control}
        name="category"
        render={({ field }) => {
          return (
            <div className="flex flex-col gap-2">
              <label htmlFor="category" className="font-bold">
                Categoria
              </label>
              <RadixSelect
                {...field}
                name="category"
                placeholder="Selecione uma categoria"
                onValueChange={field.onChange}
              >
                {occurencesCategories?.map((category, index) => (
                  <SelectItem key={index} value={category}>
                    {category}
                  </SelectItem>
                ))}
              </RadixSelect>
            </div>
          );
        }}
      />
      <Controller
        disabled={subCategories.length === 0}
        control={control}
        name="subCategory"
        render={({ field }) => {
          return (
            <div className="flex flex-col gap-2">
              <label htmlFor="subCategory" className="font-bold">
                Sub Categoria
              </label>
              <RadixSelect
                {...field}
                name="subCategory"
                placeholder="Selecione uma sub-categoria"
                onValueChange={field.onChange}
              >
                {subCategories?.map((subcategory, index) => (
                  <SelectItem key={index} value={subcategory.description}>
                    {subcategory.description}
                  </SelectItem>
                ))}
              </RadixSelect>
            </div>
          );
        }}
      />
      <Controller
        disabled={subCategories.length === 0}
        control={control}
        name="affectedArea"
        render={({ field }) => {
          return (
            <div className="flex flex-col gap-2">
              <label htmlFor="affectedArea" className="font-bold">
                Area Afetada
              </label>
              <RadixSelect
                {...field}
                name="affectedArea"
                placeholder="Qual area esta afetando?"
                onValueChange={field.onChange}
              >
                {affectedArea?.map((affectedArea, index) => (
                  <SelectItem key={index} value={affectedArea}>
                    {affectedArea}
                  </SelectItem>
                ))}
              </RadixSelect>
            </div>
          );
        }}
      />

      <div className="flex  justify-end">
        <button
          type="submit"
          className="px-4 py-2 hover:bg-emerald-200 rounded-md text-gray-700"
        >
          Salvar
        </button>
        <button
          type="button"
          className="px-4 py-2 hover:bg-emerald-200 rounded-md text-gray-700"
          onClick={() => onClose()}
        >
          Fechar
        </button>
      </div>
    </form>
  );
}
