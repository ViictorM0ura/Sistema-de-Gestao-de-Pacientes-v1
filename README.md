# Sistema de Gerenciamento de Pacientes V1

Este é um sistema de gerenciamento de pacientes desenvolvido em Java, com interface gráfica utilizando `JFrame`. Ele permite o cadastro de pacientes, a chamada de pacientes para atendimento, e a visualização e atualização de seus status e salas.

## Funcionalidades

- **Cadastro de Pacientes**: Permite registrar os dados dos pacientes (nome, idade, prontuário) com validações de campos obrigatórios e regras de formato.
- **Chamar Paciente**: Visualiza e chama pacientes da lista, além de permitir a atribuição de salas e alteração do status do atendimento (Atendido, Não Compareceu, Aguardando Atendimento).
- **Login de Usuário**: Possui diferentes tipos de login, com autenticação baseada em credenciais simples para atendentes e médicos.

## Estrutura do Projeto

### Classes

- **`Paciente`**: Representa um paciente, com informações como nome, idade, prontuário, status (atendido ou não) e sala atribuída.
- **`CadastroPacienteView`**: Tela de cadastro de pacientes, onde os dados são inseridos e validados.
- **`ChamarPacienteView`**: Tela para chamar pacientes da lista e modificar seu status ou sala.
- **`LoginView`**: Tela de login para autenticação do usuário (atendente ou médico).
- **`Main`**: Classe principal que inicializa o sistema e, exibindo a tela de login.
