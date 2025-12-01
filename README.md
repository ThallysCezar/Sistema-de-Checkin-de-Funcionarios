# ⏱️ Sistema de Check-in - Grupo Moura

<div align="center">

![Java](https://img.shields.io/badge/Java-17-orange?logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.5-brightgreen?logo=spring)
![Angular](https://img.shields.io/badge/Angular-21-DD0031?logo=angular)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue?logo=postgresql)
![RabbitMQ](https://img.shields.io/badge/RabbitMQ-3-FF6600?logo=rabbitmq)
![Docker](https://img.shields.io/badge/Docker-Enabled-2496ED?logo=docker)
![TypeScript](https://img.shields.io/badge/TypeScript-5.x-3178C6?logo=typescript)

**Sistema completo de check-in de funcionários com arquitetura moderna e deploy em produção**

[🌐 Demo Online](#-demo-online) •
[🛠️ Tecnologias](#-stack-tecnológico) •
[✨ Funcionalidades](#-funcionalidades) •
[📥 Instalação](#-instalação-local) •
[🐳 Docker](#-docker)

</div>

---

## 🌐 Demo Online

> **⚠️ IMPORTANTE:** O serviço de hospedagem (Render) coloca o site em modo inativo (sleep mode) após um tempo sem acesso. Caso encontre um erro, por favor, aguarde aproximadamente 1-2 minutos para que o servidor seja reativado automaticamente.

### 🔗 Links da Aplicação

- **Backend API:** https://sistema-de-checkin-de-funcionarios.onrender.com
- **Swagger API Docs:** https://sistema-de-checkin-de-funcionarios.onrender.com/swagger-ui.html
- **API Health Check:** https://sistema-de-checkin-de-funcionarios.onrender.com/actuator/health
- **Frontend:** (Em deploy)

### 👥 Usuários de Teste

| Email | Senha | Role | Acesso |
|-------|-------|------|--------|
| `manager@example.com` | `manager` | Manager | Dashboard + Check-in |
| `employee@example.com` | `password` | Employee | Check-in apenas |

---

## 📖 Sobre o Projeto

Sistema completo de **Check-in de Funcionários** desenvolvido para o **Grupo Moura**, permitindo registro de entrada/saída de funcionários com dashboard administrativo para gestores.

### 🎯 Objetivos Alcançados

✅ **Autenticação robusta** com controle de acesso por roles  
✅ **Sistema de check-in/check-out** com cálculo automático de horas  
✅ **Dashboard administrativo** para visualização de registros  
✅ **Arquitetura moderna** com mensageria assíncrona (RabbitMQ)  
✅ **Containerização completa** com Docker e Docker Compose  
✅ **Deploy em produção** no Render.com  
✅ **Interface responsiva** com Material Design e tema Grupo Moura  

---

## 🛠️ Stack Tecnológico

### Backend

| Tecnologia | Versão | Descrição |
|-----------|--------|-----------|
| **Java** | 17 | Linguagem de programação principal |
| **Spring Boot** | 3.5.5 | Framework para desenvolvimento de aplicações |
| **Spring Data JPA** | - | Persistência e ORM (Hibernate) |
| **Spring Boot Actuator** | - | Monitoramento e health checks |
| **Spring AMQP** | - | Integração com RabbitMQ |
| **PostgreSQL** | 15 | Banco de dados relacional |
| **Flyway** | - | Versionamento e migrations de BD |
| **RabbitMQ** | 3 | Message broker para eventos assíncronos |
| **Lombok** | - | Redução de boilerplate code |
| **Swagger/OpenAPI** | 3.0 | Documentação interativa da API |
| **Maven** | 3.9+ | Gerenciamento de dependências e build |

### Frontend

| Tecnologia | Versão | Descrição |
|-----------|--------|-----------|
| **Angular** | 21 | Framework frontend SPA |
| **TypeScript** | 5.x | Linguagem tipada para JavaScript |
| **Angular Material** | 21 | Componentes UI Material Design |
| **RxJS** | - | Programação reativa |
| **Angular SSR** | - | Server-Side Rendering |
| **Nginx** | Alpine | Servidor web para produção |

### DevOps & Infraestrutura

| Tecnologia | Uso |
|-----------|-----|
| **Docker** | Containerização de aplicações |
| **Docker Compose** | Orquestração multi-container |
| **Render.com** | Plataforma de deploy cloud |
| **Git** | Controle de versão |
| **GitHub** | Repositório e CI/CD |

### Arquitetura & Padrões

| Padrão/Conceito | Implementação |
|----------------|---------------|
| **Event-Driven Architecture** | RabbitMQ para eventos de check-in/check-out |
| **Publisher-Subscriber** | Sistema de mensageria assíncrona |
| **RESTful API** | Endpoints padronizados HTTP |
| **DTO Pattern** | Separação de camadas com DTOs |
| **Repository Pattern** | Abstração de acesso a dados |
| **Service Layer** | Lógica de negócio isolada |
| **Multi-stage Docker Build** | Otimização de imagens |
| **Environment-based Config** | Suporte dev/prod |

---

## 🎬 Como Funciona

O sistema possui fluxos diferenciados baseados no perfil do usuário (Manager ou Employee). Veja abaixo como cada funcionalidade opera:

### 1️⃣ Tela de Login

Todos os usuários iniciam pela tela de login, onde devem inserir suas credenciais (email e senha).

<div align="center">

![Tela de Login](https://github.com/ThallysCezar/Sistema-de-Checkin-de-Funcionarios/blob/main/Assets/imagens/login.jpeg)
*Figura 1: Tela de Login*

</div>

**Fluxo:**
- Usuário insere email e senha
- Sistema valida credenciais no backend
- Redireciona conforme o perfil (Manager → Dashboard | Employee → Check-in)

---

### 2️⃣ Fluxo do Manager (Gestor)

Após o login, gestores são redirecionados automaticamente para o **Dashboard Administrativo**, onde podem visualizar todos os registros de ponto da equipe.

<div align="center">

![Dashboard Administrativo](https://github.com/ThallysCezar/Sistema-de-Checkin-de-Funcionarios/blob/main/Assets/imagens/dashboard_admin.jpeg)
*Figura 2: Dashboard Administrativo - Visualização de todos os registros*

</div>

**Funcionalidades disponíveis:**
- ✅ Visualizar lista completa de check-ins/check-outs da equipe
- ✅ Ver detalhes: Nome, horário de entrada, saída e duração trabalhada
- ✅ Acessar página de check-in através do botão "Fazer Check-in"

---

### 3️⃣ Fluxo do Employee (Funcionário)

Funcionários são redirecionados diretamente para a **Tela de Check-in**, onde podem registrar entrada e saída.

<div align="center">

![Tela de Check-in](https://github.com/ThallysCezar/Sistema-de-Checkin-de-Funcionarios/blob/main/Assets/imagens/checkin.jpeg)
*Figura 3: Tela de Check-in com botões de entrada e saída*

</div>

#### 📥 Botão Check-in (Entrada)

<div align="center">

![Check-in Realizado](https://github.com/ThallysCezar/Sistema-de-Checkin-de-Funcionarios/blob/main/Assets/imagens/manager_checkin.jpeg)
*Figura 4: Confirmação de check-in realizado com sucesso*

</div>

**Funcionamento:**
- Usuário clica em **"Check-in"** para registrar entrada
- Sistema valida se já existe check-in ativo (sem check-out)
- ✅ **Sucesso**: Registra horário de entrada e exibe mensagem de confirmação
- ❌ **Validação**: Se tentar fazer check-in duplicado, exibe mensagem de erro:
  > *"Você já possui um check-in ativo. Faça o check-out antes de registrar nova entrada."*

<div align="center">

![Erro Check-in Duplicado](https://github.com/ThallysCezar/Sistema-de-Checkin-de-Funcionarios/blob/main/Assets/imagens/not_checkinDuplicate.jpeg)
*Figura 5: Validação impedindo check-in duplicado*

</div>

---

#### 📤 Botão Check-out (Saída)

<div align="center">

![Check-out com Duração](https://github.com/ThallysCezar/Sistema-de-Checkin-de-Funcionarios/blob/main/Assets/imagens/not_checkoutSucesso.jpeg)
*Figura 6: Check-out realizado exibindo duração total trabalhada*

</div>

**Funcionamento:**
- Usuário clica em **"Check-out"** para registrar saída
- Sistema calcula automaticamente a duração trabalhada (formato HH:mm:ss)
- Exibe mensagem de sucesso com o tempo total:
  > *"Check-out realizado! Duração trabalhada: 08:30:45"*

---

## ✨ Funcionalidades

### 🔐 Autenticação e Autorização
- **Login seguro** com validação de credenciais no backend
- **Controle de acesso por roles** (Manager/Employee)
- **Proteção de rotas** com Angular Guards
- **Sessão persistente** com LocalStorage
- **Página de acesso negado** para tentativas não autorizadas

### ⏰ Registro de Ponto (Employee)
- **Check-in**: Registrar entrada no trabalho
- **Check-out**: Registrar saída do trabalho
- **Cálculo automático** de duração trabalhada (HH:mm:ss)
- **Validação inteligente**: Previne check-in duplicado ou check-out sem check-in
- **Feedback visual imediato** com mensagens de sucesso/erro

### 📊 Dashboard Administrativo (Manager)
- **Visualização completa** de todos os registros da equipe
- **Tabela responsiva** com Material Design
- **Informações detalhadas**:
  - Nome do funcionário
  - Horário de entrada (check-in)
  - Horário de saída (check-out)
  - Duração total trabalhada
- **Navegação integrada** para página de check-in

### 🐰 Mensageria Assíncrona (RabbitMQ)
- **Eventos de Check-in** publicados automaticamente
- **Eventos de Check-out** com informações de duração
- **Consumer assíncrono** para processamento de eventos
- **Preparado para integrações futuras**:
  - Envio de notificações
  - Geração de relatórios
  - Integração com sistemas de RH/folha
  - Cálculo de horas extras

### 🎨 Interface Visual
- **Tema customizado** com cores do Grupo Moura
  - Azul primário: `#0d4e96`
  - Cyan secundário: `#00bcd4`
- **Design responsivo** adaptável a dispositivos móveis
- **Material Design** para UX moderna e profissional

---

## 📋 Arquitetura do Sistema

### 🔄 Fluxo de Mensageria RabbitMQ

```
┌─────────────────────────────────────────────────────────┐
│                  FRONTEND (Angular)                      │
│  Usuario clica em "Check-in" ou "Check-out"            │
└────────────────┬────────────────────────────────────────┘
                 │ HTTP POST /work/checkin ou /checkout
                 ↓
┌─────────────────────────────────────────────────────────┐
│              BACKEND (Spring Boot)                       │
│  ┌─────────────────────────────────────────────────┐   │
│  │ 1. WorkRecordController                          │   │
│  │    - Recebe requisição                           │   │
│  └────────────────┬────────────────────────────────┘   │
│                   ↓                                     │
│  ┌─────────────────────────────────────────────────┐   │
│  │ 2. WorkRecordServiceImpl                         │   │
│  │    - Salva registro no PostgreSQL ✅              │   │
│  │    - Chama EventPublisher                        │   │
│  └────────────────┬────────────────────────────────┘   │
│                   ↓                                     │
│  ┌─────────────────────────────────────────────────┐   │
│  │ 3. WorkRecordEventPublisher                      │   │
│  │    - publishCheckInEvent()                       │   │
│  │    - publishCheckOutEvent()                      │   │
│  │    - Envia evento para RabbitMQ                  │   │
│  └────────────────┬────────────────────────────────┘   │
└───────────────────┼──────────────────────────────────────┘
                    ↓
┌─────────────────────────────────────────────────────────┐
│              RABBITMQ (Message Broker)                   │
│  ┌─────────────────────────────────────────────────┐   │
│  │ Queue: "work-record-events" (durable)           │   │
│  │                                                  │   │
│  │ Mensagem JSON:                                   │   │
│  │ {                                                │   │
│  │   "eventType": "CHECK_IN" | "CHECK_OUT",       │   │
│  │   "employeeId": 1,                              │   │
│  │   "workRecordId": 15,                           │   │
│  │   "timestamp": "2025-11-30T18:00:00",           │   │
│  │   "duration": "08:30:00" (apenas CHECK_OUT)    │   │
│  │ }                                                │   │
│  └────────────────┬────────────────────────────────┘   │
└───────────────────┼──────────────────────────────────────┘
                    ↓
┌─────────────────────────────────────────────────────────┐
│          BACKEND CONSUMER (Assíncrono)                   │
│  ┌─────────────────────────────────────────────────┐   │
│  │ WorkRecordEventConsumer                          │   │
│  │ @RabbitListener(queues = "work-record-events")  │   │
│  │                                                  │   │
│  │ Processa evento:                                 │   │
│  │ ✅ Loga informações                              │   │
│  │ ✅ Preparado para notificações                   │   │
│  │ ✅ Preparado para relatórios                     │   │
│  │ ✅ Preparado para cálculo de horas extras        │   │
│  └──────────────────────────────────────────────────┘   │
└──────────────────────────────────────────────────────────┘
```

### 🔄 Benefícios da Arquitetura Assíncrona

✅ **Desacoplamento**: Controller não espera processamento adicional  
✅ **Performance**: Resposta imediata ao usuário  
✅ **Escalabilidade**: Múltiplos consumers podem processar eventos  
✅ **Confiabilidade**: Mensagens persistentes (durável)  
✅ **Rastreabilidade**: Todos eventos logados  
✅ **Extensibilidade**: Fácil adicionar novos processamentos  

---

## 📥 Instalação Local

### Pré-requisitos

| Software | Versão Mínima | Download |
|----------|---------------|----------|
| **Java JDK** | 17+ | [Oracle JDK](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) |
| **Node.js** | 20+ | [Node.js](https://nodejs.org/) |
| **PostgreSQL** | 15+ | [PostgreSQL](https://www.postgresql.org/download/) |
| **RabbitMQ** | 3+ | [RabbitMQ](https://www.rabbitmq.com/download.html) |
| **Maven** | 3.8+ | [Apache Maven](https://maven.apache.org/download.cgi) |
| **Git** | 2.x+ | [Git](https://git-scm.com/downloads) |

### 1️⃣ Clonar Repositório

```powershell
git clone https://github.com/ThallysCezar/Sistema-de-Checkin-de-Funcionarios.git
cd Sistema-de-Checkin-de-Funcionarios
```

### 2️⃣ Configurar PostgreSQL

```sql
-- Conectar ao PostgreSQL
psql -U postgres

-- Criar banco (se não existir)
CREATE DATABASE postgres;

-- Flyway criará as tabelas automaticamente
```

### 3️⃣ Configurar RabbitMQ

```powershell
# Iniciar RabbitMQ (Windows)
rabbitmq-server.bat

# Acessar dashboard
# URL: http://localhost:15672
# User: guest | Password: guest
```

### 4️⃣ Executar Backend

```powershell
cd Backend

# Compilar
mvn clean install

# Executar
mvn spring-boot:run
```

**Backend disponível em:** `http://localhost:8080`

### 5️⃣ Executar Frontend

```powershell
cd Front

# Instalar dependências
npm install

# Executar em modo dev
npm run dev

# Ou com SSR
npm run dev:ssr
```

**Frontend disponível em:** `http://localhost:4200`

---

## 🐳 Docker

### Executar com Docker Compose (Recomendado)

```powershell
# Na raiz do projeto
docker-compose up -d
```

**O que isso faz:**
- ✅ PostgreSQL (porta 5433)
- ✅ RabbitMQ (portas 5673, 15673)
- ✅ Backend Spring Boot (porta 8081)
- ✅ Frontend Angular/Nginx (porta 80)

### Acessar Aplicação

- **Frontend:** http://localhost
- **Backend API:** http://localhost:8081
- **RabbitMQ Dashboard:** http://localhost:15673 (guest/guest)
- **PostgreSQL:** localhost:5433

### Parar Serviços

```powershell
# Parar containers
docker-compose down

# Parar e limpar volumes (limpa BD)
docker-compose down -v
```

---

## 📂 Estrutura do Projeto

```
Desafio1/
├── Backend/                          # Spring Boot Backend
│   ├── src/main/java/.../
│   │   ├── configs/                  # Configurações (RabbitMQ, Flyway, CORS)
│   │   ├── controllers/              # REST Controllers
│   │   ├── dtos/                     # Data Transfer Objects
│   │   ├── events/                   # WorkRecordEvent
│   │   ├── messaging/                # RabbitMQ Publisher & Consumer
│   │   ├── models/                   # JPA Entities
│   │   ├── repositories/             # Spring Data Repositories
│   │   └── services/                 # Business Logic
│   ├── src/main/resources/
│   │   ├── application.properties              # Config dev
│   │   ├── application-prod.properties         # Config prod
│   │   └── db/migration/             # Flyway SQL Scripts
│   ├── Dockerfile                    # Backend Docker image
│   └── pom.xml                       # Maven dependencies
│
├── Front/                            # Angular Frontend
│   ├── src/app/
│   │   ├── admins/                   # Dashboard module
│   │   ├── checkins/                 # Check-in module
│   │   ├── logins/                   # Login module
│   │   ├── guards/                   # Route guards
│   │   └── unauthorized/             # 403 page
│   ├── src/environments/
│   │   ├── environment.ts            # Dev config
│   │   └── environment.prod.ts       # Prod config
│   ├── nginx.conf                    # Nginx config for SPA
│   ├── Dockerfile                    # Frontend Docker image
│   └── package.json                  # npm dependencies
│
├── docker-compose.yml                # Multi-container orchestration
├── .env.example                      # Environment variables template
└── README.md                         # Este arquivo
```

---

## 🔌 API Endpoints

### Base URL (Produção)
```
https://sistema-de-checkin-de-funcionarios.onrender.com
```

### 🔐 Autenticação

#### POST /auth/login
**Descrição**: Autentica um usuário

**Request:**
```json
{
  "email": "manager@example.com",
  "password": "manager"
}
```

**Response (200):**
```json
{
  "id": 1,
  "name": "Manager",
  "email": "manager@example.com",
  "role": "manager"
}
```

### ⏰ Registro de Ponto

#### POST /work/checkin
**Descrição**: Registra entrada do funcionário

**Request:**
```json
{
  "employeeId": 2
}
```

**Response (201):**
```json
{
  "id": 10,
  "employeeId": 2,
  "employeeName": "Employee",
  "checkInTime": "2025-11-30T08:00:00",
  "checkOutTime": null,
  "duration": null
}
```

#### POST /work/checkout
**Descrição**: Registra saída e calcula duração

**Request:**
```json
{
  "employeeId": 2
}
```

**Response (200):**
```json
{
  "id": 10,
  "employeeId": 2,
  "employeeName": "Employee",
  "checkInTime": "2025-11-30T08:00:00",
  "checkOutTime": "2025-11-30T17:30:00",
  "duration": "09:30:00"
}
```

#### GET /work/list
**Descrição**: Lista todos os registros (Manager)

**Response (200):**
```json
[
  {
    "id": 1,
    "employeeId": 1,
    "employeeName": "Manager",
    "checkInTime": "2025-11-30T08:00:00",
    "checkOutTime": "2025-11-30T17:00:00",
    "duration": "09:00:00"
  }
]
```

### 🏥 Health Check

#### GET /actuator/health
**Response (200):**
```json
{
  "status": "UP",
  "components": {
    "db": { "status": "UP" },
    "diskSpace": { "status": "UP" },
    "ping": { "status": "UP" },
    "rabbit": { "status": "UP" }
  }
}
```

---

## 💾 Banco de Dados

### Estrutura das Tabelas

#### 👤 employee
| Coluna | Tipo | Descrição |
|--------|------|-----------|
| `id` | BIGSERIAL | Primary Key |
| `name` | VARCHAR(200) | Nome do funcionário |
| `email` | VARCHAR(200) | Email |

#### 🔐 auth_credentials
| Coluna | Tipo | Descrição |
|--------|------|-----------|
| `id` | BIGSERIAL | Primary Key |
| `employee_id` | BIGINT | FK → employee(id) |
| `email` | VARCHAR(200) | Email (UNIQUE) |
| `password` | VARCHAR(200) | Senha |
| `role` | VARCHAR(50) | manager/employee |

#### ⏰ work_records
| Coluna | Tipo | Descrição |
|--------|------|-----------|
| `id` | BIGSERIAL | Primary Key |
| `employee_id` | BIGINT | FK → employee(id) |
| `check_in_time` | TIMESTAMP | Data/hora entrada |
| `check_out_time` | TIMESTAMP | Data/hora saída |
| `duration` | VARCHAR(20) | HH:mm:ss |

### Migrations Flyway

| Arquivo | Descrição |
|---------|-----------|
| `V1__scriptInicialized.sql` | Inicialização |
| `V2__scriptDropTables.sql` | Limpeza |
| `V3__scriptCreateTables.sql` | Criação das tabelas |
| `V4__scriptInsert_data.sql` | Seed de funcionários |
| `V8__create_auth_credentials_table.sql` | Credenciais + seed |
| `V11__fix_serial_sequences.sql` | Correção de sequences |

---

## 🚀 Deploy no Render.com

### 📋 Configurações

#### Backend (Web Service)
- **Build Command:** `mvn clean package -DskipTests`
- **Start Command:** `java -jar target/backenddesafio1-0.0.1-SNAPSHOT.jar`
- **Environment Variables:**
  ```
  SPRING_PROFILES_ACTIVE=prod
  DATABASE_URL=<PostgreSQL URL do Render>
  DATABASE_USERNAME=<username>
  DATABASE_PASSWORD=<password>
  RABBITMQ_HOST=<CloudAMQP hostname>
  RABBITMQ_PORT=5672
  RABBITMQ_USERNAME=<username>
  RABBITMQ_PASSWORD=<password>
  ```

#### Frontend (Static Site)
- **Build Command:** `npm install && npm run build`
- **Publish Directory:** `dist/Front/browser`
- **Build Arguments:**
  ```
  API_URL=https://sistema-de-checkin-de-funcionarios.onrender.com
  ```

#### PostgreSQL
- Usar serviço **PostgreSQL** gerenciado do Render
- Ou CloudDBaaS (Supabase, Neon, etc.)

#### RabbitMQ
- Usar **CloudAMQP** (addon gratuito)
- Plano: Little Lemur (Free)

---

## 🔒 Segurança

### Implementações de Segurança

✅ **CORS Configurado**: Permite apenas origins autorizados  
✅ **Validação de Input**: DTOs com validações  
✅ **Proteção de Rotas**: Guards no frontend  
✅ **Environment Variables**: Secrets não commitados  
✅ **Health Checks**: Monitoramento de serviços  
✅ **Error Handling**: Mensagens genéricas para usuário  

### Melhorias Futuras

- [ ] JWT Tokens para autenticação stateless
- [ ] Hash de senhas com BCrypt
- [ ] Rate limiting no API Gateway
- [ ] HTTPS obrigatório em produção
- [ ] Logs de auditoria
- [ ] Refresh tokens

---

## 🎯 Roadmap

### ✅ Implementado

- [x] Sistema completo de check-in/check-out
- [x] Dashboard administrativo
- [x] Autenticação e autorização
- [x] Mensageria assíncrona (RabbitMQ)
- [x] Containerização (Docker)
- [x] Deploy em produção (Render)
- [x] Migrations de banco (Flyway)
- [x] Health checks (Spring Actuator)
- [x] Material Design customizado

### 📋 Próximas Funcionalidades

- [ ] **Relatórios**: Exportar em PDF/Excel
- [ ] **Dashboard com Gráficos**: Visualização de horas trabalhadas
- [ ] **Filtros**: Por data, funcionário, período
- [ ] **Notificações**: Email/SMS de confirmação
- [ ] **Edição de Registros**: Correção pelo manager
- [ ] **Horas Extras**: Cálculo automático
- [ ] **Mobile App**: Ionic ou React Native
- [ ] **Reconhecimento Facial**: Biometria para check-in
- [ ] **Integração RH**: API para folha de pagamento

---

## 👨‍💻 Autor

**Thallys Cezar**

- GitHub: [@ThallysCezar](https://github.com/ThallysCezar)
- LinkedIn: [Thallys Cezar](https://www.linkedin.com/in/thallyscezar/)

---

## 📄 Licença

Este projeto foi desenvolvido como parte do **Desafio 1 - Grupo Moura**.

---

<div align="center">

**⭐ Sistema de Controle de Ponto - Grupo Moura ⭐**

Desenvolvido com 💙 usando Spring Boot, Angular e RabbitMQ

[⬆ Voltar ao topo](#️-sistema-de-controle-de-ponto---grupo-moura)

</div>
