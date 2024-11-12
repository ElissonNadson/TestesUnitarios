import React, { useState } from "react";
import { Container } from "@mui/material";
import CreateForm from "./components/CreateForm";
import CadastroList from "./components/CadastroList";
import styled from "styled-components";

interface Cadastro {
  email: string;
  cpf: string;
  telefone: string;
}

const AppContainer = styled(Container)`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  gap: 2rem;
  padding: 2rem;
`;

const ColumnsContainer = styled.div`
  display: flex;
  flex-direction: column;
  max-width: 1280px;
  gap: 2rem;

  @media (min-width: 768px) {
    flex-direction: row;
  }
`;

const Column = styled.div`
  flex: 1;
  display: flex;
  justify-content: center;
`;

const App: React.FC = () => {
  const [cadastros, setCadastros] = useState<Cadastro[]>([]);

  const handleAddCadastro = (cadastro: Cadastro) => {
    setCadastros((prevCadastros) => [...prevCadastros, cadastro]);
  };

  return (
    <AppContainer>
      <ColumnsContainer>
        <Column>
          <CreateForm onAddCadastro={handleAddCadastro} />
        </Column>
        <Column>
          <CadastroList cadastros={cadastros} />
        </Column>
      </ColumnsContainer>
    </AppContainer>
  );
};

export default App;