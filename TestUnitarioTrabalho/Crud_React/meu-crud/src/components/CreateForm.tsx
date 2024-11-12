import React, { useState } from "react";
import { useForm, Controller } from "react-hook-form";
import { TextField, Button, Typography, IconButton, InputAdornment } from "@mui/material";
import { Visibility, VisibilityOff } from "@mui/icons-material";
import * as yup from "yup";
import { yupResolver } from "@hookform/resolvers/yup";
import InputMask from "react-input-mask";
import { cpf } from "cpf-cnpj-validator";
import { isPhone } from "brazilian-values";
import styled from "styled-components";

interface Cadastro {
  email: string;
  cpf: string;
  telefone: string;
}

interface CreateFormProps {
  onAddCadastro: (cadastro: Cadastro) => void;
}

const schema = yup.object().shape({
  email: yup.string().email("Email inválido").required("Email é obrigatório"),
  confirmEmail: yup
    .string()
    .oneOf([yup.ref("email")], "Emails devem coincidir")
    .required("Confirmação de email é obrigatória"),
  cpf: yup
    .string()
    .test("isValidCPF", "CPF inválido", (value) => cpf.isValid(value || ""))
    .required("CPF é obrigatório"),
  telefone: yup
    .string()
    .test("isValidPhone", "Formato de telefone inválido", (value) => isPhone(value || ""))
    .required("Telefone é obrigatório"),
  senha: yup.string().min(8, "A senha deve ter no mínimo 8 caracteres").required("Senha é obrigatória"),
  confirmSenha: yup
    .string()
    .oneOf([yup.ref("senha")], "Senhas devem coincidir")
    .required("Confirmação de senha é obrigatória"),
});

const FormContainer = styled.form`
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 2rem;
  flex-grow: 1;
`;

const FormPaper = styled.div`
  padding: 2rem;
  border-radius: 8px;
  width: 100%;
  max-width: 500px;
  background-color: rgba(255, 255, 255, 0.85);
  backdrop-filter: blur(10px);
`;

const SubmitButton = styled(Button)`
  margin-top: 1.5rem;
  padding: 0.75rem;
  font-size: 1rem;
  background-color: #1976d2;
  &:hover {
    background-color: #115293;
  }
`;

const Title = styled(Typography)`
  font-size: 1.5rem; 
  text-align: center; 
  margin-bottom: 1rem;
`;

const CreateForm: React.FC<CreateFormProps> = ({ onAddCadastro }) => {
  const { handleSubmit, control, reset } = useForm({
    resolver: yupResolver(schema),
    mode: "onBlur",
  });

  const [showPassword, setShowPassword] = useState(false);
  const [showConfirmPassword, setShowConfirmPassword] = useState(false);

  const handleClickShowPassword = () => setShowPassword(!showPassword);
  const handleClickShowConfirmPassword = () => setShowConfirmPassword(!showConfirmPassword);

  const onSubmit = (data: any) => {
    const cadastro: Cadastro = {
      email: data.email,
      cpf: data.cpf,
      telefone: data.telefone,
    };
    onAddCadastro(cadastro); 
    alert("Dados gravados com sucesso!");
    reset({
      email: "",
      confirmEmail: "",
      cpf: "",
      telefone: "",
      senha: "",
      confirmSenha: ""
    });
  };

  return (
    <FormContainer onSubmit={handleSubmit(onSubmit)}>
      <FormPaper>
        <Title variant="h5" align="center" gutterBottom>
          Cadastro de Usuário
        </Title>

        <Controller
          name="email"
          control={control}
          render={({ field, fieldState }) => (
            <TextField
              {...field}
              label="Email"
              fullWidth
              margin="normal"
              error={!!fieldState.error}
              helperText={fieldState.error ? fieldState.error.message : ""}
              variant="outlined"
            />
          )}
        />

        <Controller
          name="confirmEmail"
          control={control}
          render={({ field, fieldState }) => (
            <TextField
              {...field}
              label="Confirme o Email"
              fullWidth
              margin="normal"
              error={!!fieldState.error}
              helperText={fieldState.error ? fieldState.error.message : ""}
              variant="outlined"
            />
          )}
        />

        <Controller
          name="cpf"
          control={control}
          render={({ field, fieldState }) => (
            <InputMask
              mask="999.999.999-99"
              value={field.value}
              onChange={field.onChange}
              onBlur={field.onBlur}
            >
              {() => (
                <TextField
                  label="CPF"
                  fullWidth
                  margin="normal"
                  error={!!fieldState.error}
                  helperText={fieldState.error ? fieldState.error.message : "Formato: XXX.XXX.XXX-XX"}
                  variant="outlined"
                />
              )}
            </InputMask>
          )}
        />

        <Controller
          name="telefone"
          control={control}
          render={({ field, fieldState }) => (
            <InputMask
              mask="(99) 99999-9999"
              value={field.value}
              onChange={field.onChange}
              onBlur={field.onBlur}
            >
              {() => (
                <TextField
                  label="Telefone"
                  fullWidth
                  margin="normal"
                  error={!!fieldState.error}
                  helperText={fieldState.error ? fieldState.error.message : "Formato: (XX) XXXXX-XXXX"}
                  variant="outlined"
                />
              )}
            </InputMask>
          )}
        />

        <Controller
          name="senha"
          control={control}
          render={({ field, fieldState }) => (
            <TextField
              {...field}
              type={showPassword ? "text" : "password"}
              label="Senha"
              fullWidth
              margin="normal"
              error={!!fieldState.error}
              helperText={fieldState.error ? fieldState.error.message : "Mínimo 8 caracteres"}
              variant="outlined"
              InputProps={{
                endAdornment: (
                  <InputAdornment position="end">
                    <IconButton onClick={handleClickShowPassword} edge="end">
                      {showPassword ? <VisibilityOff /> : <Visibility />}
                    </IconButton>
                  </InputAdornment>
                ),
              }}
            />
          )}
        />

        <Controller
          name="confirmSenha"
          control={control}
          render={({ field, fieldState }) => (
            <TextField
              {...field}
              type={showConfirmPassword ? "text" : "password"}
              label="Confirme a Senha"
              fullWidth
              margin="normal"
              error={!!fieldState.error}
              helperText={fieldState.error ? fieldState.error.message : ""}
              variant="outlined"
              InputProps={{
                endAdornment: (
                  <InputAdornment position="end">
                    <IconButton onClick={handleClickShowConfirmPassword} edge="end">
                      {showConfirmPassword ? <VisibilityOff /> : <Visibility />}
                    </IconButton>
                  </InputAdornment>
                ),
              }}
            />
          )}
        />

        <SubmitButton type="submit" variant="contained" color="primary" fullWidth>
          Gravar Dados
        </SubmitButton>
      </FormPaper>
    </FormContainer>
  );
};

export default CreateForm;