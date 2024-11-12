import React from "react";
import { List, ListItem, ListItemText, Typography } from "@mui/material";
import styled from "styled-components";

interface Cadastro {
  email: string;
  cpf: string;
  telefone: string;
}

interface CadastroListProps {
  cadastros: Cadastro[];
}

const CadastroListContainer = styled.div`
  margin-top: 4rem; 
`;

const Title = styled(Typography)`
  font-size: 1.5rem;
  text-align: center; 
  margin-bottom: 1rem; 
`;

const CadastroList: React.FC<CadastroListProps> = ({ cadastros }) => {
  return (
    <CadastroListContainer>
      <Title variant="h6" gutterBottom>
        Lista de Cadastros
      </Title>
      <List>
        {cadastros.length > 0 ? (
          cadastros.map((cadastro, index) => (
            <ListItem key={index}>
              <ListItemText
                primary={`Email: ${cadastro.email}`}
                secondary={`CPF: ${cadastro.cpf} - Telefone: ${cadastro.telefone}`}
              />
            </ListItem>
          ))
        ) : (
          <Typography align="center" color="textSecondary">
            Nenhum cadastro realizado
          </Typography>
        )}
      </List>
    </CadastroListContainer>
  );
};

export default CadastroList;