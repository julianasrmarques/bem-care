Bem Care 🦷
Sistema de gestão para a ONG Turma do Bem, conectando dentistas voluntários a pacientes de baixa renda em todo o Brasil.

📋 Sobre o projeto
O Bem Care é uma aplicação web full-stack que digitaliza as operações da ONG Turma do Bem. O sistema permite cadastrar dentistas voluntários, gerenciar pacientes atendidos e controlar solicitações de agendamento de consultas odontológicas — tudo integrado em tempo real.

🏗️ Estrutura do repositório
bem-care/
├── backend/    # API RESTful em Java com Quarkus + Oracle
├── frontend/   # Painel web em React + TypeScript
└── README.md


🚀 Como rodar o projeto

Pré-requisitos:

Java 17+
Maven 3.8+
Node.js 18+
Banco Oracle acessível (FIAP)

Backend
bash# Entre na pasta do backend
cd backend

# Execute o script SQL no Oracle antes de iniciar
# (arquivo: src/main/resources/script-banco.sql)

# Inicie o servidor Quarkus
mvn quarkus:dev
A API estará disponível em http://localhost:8080
Para verificar: acesse http://localhost:8080/dentista — deve retornar um array JSON.
Frontend
bash# Entre na pasta do frontend
cd frontend

# Instale as dependências
npm install

# Inicie o servidor de desenvolvimento
npm run dev -- --port 5173
O painel estará disponível em http://localhost:5173

⚠️ O backend deve estar rodando antes de iniciar o frontend.

🔗 Endpoints da API
Dentistas /dentista
MétodoRotaDescriçãoGET/dentistaLista todos os dentistasGET/dentista/{id}Busca dentista por IDPOST/dentistaCadastra novo dentistaPUT/dentistaAtualiza dentistaDELETE/dentista/{id}Remove dentista
Pacientes /paciente
MétodoRotaDescriçãoGET/pacienteLista todos os pacientesGET/paciente/{id}Busca paciente por IDPOST/pacienteCadastra novo pacientePUT/pacienteAtualiza pacienteDELETE/paciente/{id}Remove paciente
Agendamentos /agendamento
MétodoRotaDescriçãoGET/agendamentoLista todos os agendamentosGET/agendamento/{id}Busca por IDGET/agendamento/status/{status}Filtra por statusPOST/agendamentoCria solicitaçãoPUT/agendamentoAtualiza agendamentoPATCH/agendamento/{id}/statusAtualiza statusDELETE/agendamento/{id}Remove agendamento
🛠️ Tecnologias
Backend

Java 17
Quarkus
Oracle Database
JAX-RS (REST)
JDBC

Frontend

React 18
TypeScript
TanStack Router
Tailwind CSS
shadcn/ui

📄 Licença
Projeto acadêmico desenvolvido para a FIAP — 2026.
