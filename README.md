# ⏱️ Desafio 1: Sistema de Controle de Ponto

<div align="center">

![Java](https://img.shields.io/badge/Java-17-orange?logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.5-brightgreen?logo=spring)
![Angular](https://img.shields.io/badge/Angular-21-DD0031?logo=angular)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue?logo=postgresql)
![TypeScript](https://img.shields.io/badge/TypeScript-5.x-3178C6?logo=typescript)
![Material Design](https://img.shields.io/badge/Material-Design-757575?logo=material-design)

**Sistema de registro de ponto eletrônico para funcionários com controles diferenciados por perfil**

[Tecnologias](#-tecnologias-utilizadas) •
[Funcionalidades](#-funcionalidades) •
[Usuários](#-usuários-de-teste) •
[Instalação](#-instalação) •
[Como Usar](#-como-usar) •
[Fluxos](#-fluxos-de-uso)

</div>

---

## 📖 Sobre o Projeto

Este é um **Sistema de Controle de Ponto Eletrônico** desenvolvido como parte do Desafio 1 do Grupo Moura. A aplicação permite que funcionários registrem seus horários de entrada e saída (check-in/check-out), enquanto gestores podem visualizar todos os registros da equipe em um painel administrativo.

### 🎯 Objetivo

Criar uma solução completa de ponto eletrônico com:
- ✅ Autenticação segura baseada em roles (manager/employee)
- ✅ Registro de check-in e check-out para funcionários
- ✅ Cálculo automático de horas trabalhadas
- ✅ Painel administrativo para gestores visualizarem todos os registros
- ✅ Interface moderna e responsiva

---

## 📋 Índice

- [Sobre o Projeto](#-sobre-o-projeto)
- [Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [Funcionalidades](#-funcionalidades)
- [Usuários de Teste](#-usuários-de-teste)
- [Instalação](#-instalação)
- [Como Usar](#-como-usar)
- [Fluxos de Uso](#-fluxos-de-uso)
  - [Fluxo do Manager](#-fluxo-do-manager-gestor)
  - [Fluxo do Employee](#-fluxo-do-employee-funcionário)
- [Estrutura do Projeto](#-estrutura-do-projeto)
- [API Endpoints](#-api-endpoints)
- [Banco de Dados](#-banco-de-dados)

---

## 🛠️ Tecnologias Utilizadas

### Backend
| Tecnologia | Versão | Descrição |
|-----------|--------|-----------|
| **Java** | 17 | Linguagem de programação |
| **Spring Boot** | 3.5.5 | Framework backend |
| **PostgreSQL** | 15+ | Banco de dados relacional |
| **Flyway** | - | Migrations de banco de dados |
| **JPA/Hibernate** | - | ORM para persistência |
| **Maven** | - | Gerenciamento de dependências |

### Frontend
| Tecnologia | Versão | Descrição |
|-----------|--------|-----------|
| **Angular** | 21 | Framework frontend |
| **TypeScript** | 5.x | Linguagem de programação |
| **Angular Material** | - | Biblioteca de componentes UI |
| **RxJS** | - | Programação reativa |
| **Angular SSR** | - | Server-Side Rendering |

### Ferramentas
| Tecnologia | Uso |
|-----------|-----|
| **Lombok** | Redução de boilerplate code no Java |
| **CORS** | Configuração de segurança cross-origin |
| **LocalStorage** | Armazenamento de sessão no navegador |

---

## ✨ Funcionalidades

### 🔐 Autenticação
- **Login** com validação de credenciais no backend
- **Controle de acesso** baseado em roles (manager/employee)
- **Proteção de rotas** com guards do Angular
- **Página de acesso negado** para tentativas não autorizadas

### ⏰ Registro de Ponto (Employee)
- **Check-in**: Registrar entrada no trabalho
- **Check-out**: Registrar saída do trabalho
- **Feedback visual**: Mensagens de sucesso/erro
- **Validação**: Não permite check-in duplicado ou check-out sem check-in

### 📊 Painel Administrativo (Manager)
- **Visualização completa**: Ver todos os registros de ponto da equipe
- **Tabela com paginação**: Navegação fácil entre registros
- **Informações detalhadas**:
  - Nome do funcionário
  - Email
  - Data/hora de check-in
  - Data/hora de check-out
  - Duração trabalhada (formato HH:mm:ss)
- **Navegação**: Botão para acessar página de check-in

---

## 👥 Usuários de Teste

A aplicação já vem com usuários pré-cadastrados no banco de dados para facilitar os testes:

| Nome | Email | Senha | Role | Descrição |
|------|-------|-------|------|-----------|
| **Admin** | `admin@example.com` | `admin` | `admin` | Acesso administrativo completo |
| **Manager** | `manager@example.com` | `manager` | `manager` | Gestor - pode visualizar todos os registros |
| **Employee** | `employee@example.com` | `password` | `employee` | Funcionário - pode apenas registrar seu ponto |

### 🔑 Como Usar

1. Acesse a tela de login em `http://localhost:4200/logins`
2. Use um dos emails e senhas acima
3. Você será redirecionado automaticamente:
   - **Manager** → `/admins` (Painel Administrativo)
   - **Employee** → `/checkins` (Registro de Ponto)

---

## 📥 Instalação

### Pré-requisitos

| Software | Versão Mínima | Download |
|----------|---------------|----------|
| **Java JDK** | 17+ | [Oracle JDK](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) |
| **Node.js** | 18+ | [Node.js](https://nodejs.org/) |
| **PostgreSQL** | 15+ | [PostgreSQL](https://www.postgresql.org/download/) |
| **Maven** | 3.8+ | [Apache Maven](https://maven.apache.org/download.cgi) |
| **Git** | 2.x+ | [Git](https://git-scm.com/downloads) |

### 1️⃣ Clonar o Repositório

```powershell
git clone https://github.com/seu-usuario/Desafio1.git
cd Desafio1
```

### 2️⃣ Configurar o Banco de Dados

```sql
-- Conectar ao PostgreSQL
psql -U postgres

-- Criar banco de dados
CREATE DATABASE postgres;

-- O Flyway criará as tabelas automaticamente na primeira execução
```

### 3️⃣ Configurar o Backend

```powershell
# Navegar para a pasta do backend
cd Backend

# Editar application.properties (se necessário)
# Backend/src/main/resources/application.properties
# Configurar URL, username e password do PostgreSQL

# Compilar o projeto
mvn clean install

# Executar o backend
mvn spring-boot:run
```

O backend estará disponível em: `http://localhost:8080`

### 4️⃣ Configurar o Frontend

```powershell
# Navegar para a pasta do frontend (em outro terminal)
cd Front

# Instalar dependências
npm install

# Executar o frontend
npm run dev:ssr
```

O frontend estará disponível em: `http://localhost:4200`

---

## 🚀 Como Usar

### Acessando a Aplicação

1. Abra o navegador em `http://localhost:4200`
2. Você será redirecionado automaticamente para `/logins` (tela de login)
3. Use um dos [usuários de teste](#-usuários-de-teste)
4. Após o login, você será direcionado conforme seu perfil:
   - **Manager** → `/admins` (Painel Administrativo)
   - **Employee** → `/checkins` (Registro de Ponto)

### 🔒 Regras de Acesso

| Rota | Público | Employee | Manager |
|------|---------|----------|---------|
| `/logins` | ✅ | ✅ | ✅ |
| `/checkins` | ❌ | ✅ | ✅ |
| `/admins` | ❌ | ❌ | ✅ |
| `/unauthorized` | ✅ | ✅ | ✅ |

---

## 📱 Fluxos de Uso

### 👔 Fluxo do Manager (Gestor)

#### 1. Login
<div align="center">

**Tela de Login**

</div>

- Acesse `http://localhost:4200/logins`
- Digite:
  - **Email**: `manager@example.com`
  - **Senha**: `manager`
- Clique em **"Entrar"**

#### 2. Painel Administrativo
<div align="center">

**Painel Administrativo - Visualização de Todos os Registros**

</div>

Após o login, você será redirecionado para `/admins` onde poderá:

**📊 Visualizar Registros**
- Ver **todos os registros de ponto** da equipe
- Informações exibidas:
  - ✅ **Nome do funcionário**
  - ✅ **Email**
  - ✅ **Data/hora de entrada (check-in)**
  - ✅ **Data/hora de saída (check-out)**
  - ✅ **Duração trabalhada** (formato HH:mm:ss)

**🔄 Funcionalidades**
- **Paginação**: Navegue entre os registros (10 por página)
- **Ordenação**: Clique nos cabeçalhos da tabela para ordenar
- **Botão "Ir para Check-in"**: Acesse sua própria página de registro de ponto

#### 3. Registrar Próprio Ponto (Opcional)
- Clique no botão **"Ir para Check-in"** no painel administrativo
- Você será redirecionado para `/checkins`
- Registre seu próprio check-in/check-out (veja o [Fluxo do Employee](#-fluxo-do-employee-funcionário))

---

### 👨‍💼 Fluxo do Employee (Funcionário)

#### 1. Login
<div align="center">

**Tela de Login**

</div>

- Acesse `http://localhost:4200/logins`
- Digite:
  - **Email**: `employee@example.com`
  - **Senha**: `password`
- Clique em **"Entrar"**

#### 2. Registro de Ponto
<div align="center">

**Tela de Check-in/Check-out**

</div>

Após o login, você será redirecionado para `/checkins` onde poderá:

**✅ Fazer Check-in (Entrada)**
1. Clique no botão **"Check-in"**
2. O sistema registrará a data/hora atual
3. Mensagem de sucesso: ✅ **"Check-in realizado com sucesso!"** (fundo verde)

**❌ Validação de Check-in**
- Se já tiver um check-in aberto, você verá:
  - ❌ **"Erro: Você já tem um check-in ativo. Faça o check-out primeiro."** (fundo vermelho)

**⏰ Fazer Check-out (Saída)**
1. Após ter feito check-in, clique no botão **"Check-out"**
2. O sistema registrará a data/hora de saída
3. O sistema calculará automaticamente a duração trabalhada
4. Mensagem de sucesso: ✅ **"Check-out realizado com sucesso!"** (fundo verde)

**❌ Validação de Check-out**
- Se não tiver um check-in ativo, você verá:
  - ❌ **"Erro: Nenhum check-in ativo encontrado para fazer check-out."** (fundo vermelho)

**🔄 Funcionalidades**
- **Botões sempre habilitados**: Não ficam desabilitados, a validação é feita no backend
- **Mensagens temporárias**: Desaparecem automaticamente após 5 segundos
- **Feedback visual**: Cores diferentes para sucesso (verde) e erro (vermelho)

#### 3. Tentativa de Acesso ao Painel Administrativo
<div align="center">

**Página de Acesso Negado**

</div>

- Se tentar acessar `/admins` diretamente pela URL
- Você será redirecionado para `/unauthorized`
- Mensagem: **"🚫 Acesso Negado - Você não tem permissão para acessar esta página"**
- Botão **"Voltar para Home"** para retornar à página de check-in

---

## 📂 Estrutura do Projeto

```
Desafio1/
├── Backend/                          # Spring Boot Backend
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/br/com/thallysprojetos/backenddesafio1/
│   │   │   │   ├── controllers/      # Controladores REST
│   │   │   │   │   ├── AuthController.java
│   │   │   │   │   └── WorkController.java
│   │   │   │   ├── dtos/             # Data Transfer Objects
│   │   │   │   │   ├── ErrorResponseDTO.java
│   │   │   │   │   ├── LoginRequestDTO.java
│   │   │   │   │   └── LoginResponseDTO.java
│   │   │   │   ├── entities/         # Entidades JPA
│   │   │   │   │   ├── AuthCredential.java
│   │   │   │   │   ├── Employee.java
│   │   │   │   │   └── WorkRecord.java
│   │   │   │   ├── repositories/     # Repositórios JPA
│   │   │   │   │   ├── AuthCredentialRepository.java
│   │   │   │   │   ├── EmployeeRepository.java
│   │   │   │   │   └── WorkRecordRepository.java
│   │   │   │   ├── services/         # Lógica de negócio
│   │   │   │   │   ├── AuthService.java
│   │   │   │   │   └── WorkRecordService.java
│   │   │   │   └── exceptions/       # Exceções customizadas
│   │   │   │       ├── ModelAlreadyExistException.java
│   │   │   │       └── ModelNotFoundException.java
│   │   │   └── resources/
│   │   │       ├── application.properties
│   │   │       └── db/migration/     # Flyway Migrations
│   │   │           ├── V1__scriptInicialized.sql
│   │   │           ├── V2__scriptDropTables.sql
│   │   │           ├── V3__scriptCreateTables.sql
│   │   │           ├── V4__scriptInsert_data.sql
│   │   │           ├── V8__create_auth_credentials_table.sql
│   │   │           └── V11__fix_serial_sequences.sql
│   │   └── test/                     # Testes unitários
│   └── pom.xml                       # Maven dependencies
│
└── Front/                            # Angular Frontend
    ├── src/
    │   ├── app/
    │   │   ├── logins/               # Módulo de Login
    │   │   │   ├── login/
    │   │   │   │   ├── login.ts
    │   │   │   │   ├── login.html
    │   │   │   │   └── login.scss
    │   │   │   ├── services/
    │   │   │   │   └── auth.service.ts
    │   │   │   └── logins-module.ts
    │   │   │
    │   │   ├── checkins/             # Módulo de Check-in
    │   │   │   ├── checkin/
    │   │   │   │   ├── checkin.ts
    │   │   │   │   ├── checkin.html
    │   │   │   │   └── checkin.scss
    │   │   │   ├── services/
    │   │   │   │   └── checkin.service.ts
    │   │   │   └── checkins-module.ts
    │   │   │
    │   │   ├── admins/               # Módulo Administrativo
    │   │   │   ├── admin/
    │   │   │   │   ├── admin.ts
    │   │   │   │   ├── admin.html
    │   │   │   │   └── admin.scss
    │   │   │   ├── services/
    │   │   │   │   └── admin.service.ts
    │   │   │   └── admins-module.ts
    │   │   │
    │   │   ├── unauthorized/         # Página de Acesso Negado
    │   │   │   └── unauthorized.component.ts
    │   │   │
    │   │   ├── guards/               # Guards de Rota
    │   │   │   ├── auth.guard.ts
    │   │   │   └── role.guard.ts
    │   │   │
    │   │   ├── app-routing-module.ts
    │   │   └── app.ts
    │   │
    │   ├── index.html
    │   └── main.ts
    │
    ├── angular.json
    ├── package.json
    └── tsconfig.json
```

---

## 🔌 API Endpoints

### Base URL
```
http://localhost:8080
```

### 🔐 Autenticação

#### POST /auth/login
**Descrição**: Autentica um usuário e retorna seus dados

**Request Body**:
```json
{
  "email": "manager@example.com",
  "password": "manager"
}
```

**Response** (200 OK):
```json
{
  "id": 2,
  "name": "Manager",
  "email": "manager@example.com",
  "role": "manager"
}
```

**Response** (401 Unauthorized):
```json
{
  "message": "Credenciais inválidas"
}
```

---

### ⏰ Registro de Ponto

#### POST /work/checkin
**Descrição**: Registra a entrada (check-in) de um funcionário

**Request Body**:
```json
{
  "employeeId": 3
}
```

**Response** (201 Created):
```json
{
  "id": 10,
  "employee": {
    "id": 3,
    "name": "Employee",
    "email": "employee@example.com"
  },
  "checkInTime": "2025-01-23T08:00:00",
  "checkOutTime": null,
  "duration": null
}
```

**Response** (409 Conflict - Check-in já existe):
```json
{
  "message": "Já existe um check-in ativo para este funcionário"
}
```

---

#### POST /work/checkout
**Descrição**: Registra a saída (check-out) de um funcionário e calcula a duração

**Request Body**:
```json
{
  "employeeId": 3
}
```

**Response** (200 OK):
```json
{
  "id": 10,
  "employee": {
    "id": 3,
    "name": "Employee",
    "email": "employee@example.com"
  },
  "checkInTime": "2025-01-23T08:00:00",
  "checkOutTime": "2025-01-23T17:30:00",
  "duration": "09:30:00"
}
```

**Response** (404 Not Found - Sem check-in ativo):
```json
{
  "message": "Nenhum check-in ativo encontrado"
}
```

---

#### GET /work/list
**Descrição**: Lista todos os registros de ponto (para managers)

**Response** (200 OK):
```json
[
  {
    "id": 1,
    "employee": {
      "id": 1,
      "name": "Admin",
      "email": "admin@example.com"
    },
    "checkInTime": "2025-01-20T08:00:00",
    "checkOutTime": "2025-01-20T17:00:00",
    "duration": "09:00:00"
  },
  {
    "id": 2,
    "employee": {
      "id": 2,
      "name": "Manager",
      "email": "manager@example.com"
    },
    "checkInTime": "2025-01-21T09:00:00",
    "checkOutTime": null,
    "duration": null
  }
]
```

---

## 💾 Banco de Dados

### Estrutura das Tabelas

#### 👤 employee
Armazena informações básicas dos funcionários

| Coluna | Tipo | Descrição |
|--------|------|-----------|
| `id` | BIGSERIAL | Primary Key |
| `name` | VARCHAR(200) | Nome do funcionário |
| `email` | VARCHAR(200) | Email (UNIQUE) |

#### 🔐 auth_credentials
Armazena credenciais de autenticação

| Coluna | Tipo | Descrição |
|--------|------|-----------|
| `id` | BIGSERIAL | Primary Key |
| `employee_id` | BIGINT | Foreign Key → employee(id) |
| `email` | VARCHAR(200) | Email (UNIQUE) |
| `password` | VARCHAR(200) | Senha (texto plano para demo) |
| `role` | VARCHAR(50) | Perfil: 'admin', 'manager', 'employee' |

#### ⏰ work_records
Armazena registros de ponto

| Coluna | Tipo | Descrição |
|--------|------|-----------|
| `id` | BIGSERIAL | Primary Key |
| `employee_id` | BIGINT | Foreign Key → employee(id) |
| `check_in_time` | TIMESTAMP | Data/hora de entrada |
| `check_out_time` | TIMESTAMP | Data/hora de saída (nullable) |
| `duration` | VARCHAR(8) | Duração no formato HH:mm:ss (nullable) |

### Relacionamentos

```
employee (1) ──────── (1) auth_credentials
    │
    │ (1)
    │
    ↓ (N)
work_records
```

### Migrations (Flyway)

| Arquivo | Descrição |
|---------|-----------|
| `V1__scriptInicialized.sql` | Inicialização básica |
| `V2__scriptDropTables.sql` | Drop de tabelas antigas |
| `V3__scriptCreateTables.sql` | Criação das tabelas |
| `V4__scriptInsert_data.sql` | Insere funcionários de teste |
| `V8__create_auth_credentials_table.sql` | Cria tabela de autenticação |
| `V11__fix_serial_sequences.sql` | Corrige sequências do PostgreSQL |

---

## 🎨 Detalhes Técnicos

### Backend

#### CORS Configuration
O backend está configurado para aceitar requisições do frontend:

```java
@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:4200")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}
```

#### Cálculo de Duração
O sistema calcula automaticamente a duração trabalhada no formato HH:mm:ss:

```java
Duration duration = Duration.between(checkInTime, checkOutTime);
long hours = duration.toHours();
long minutes = duration.toMinutesPart();
long seconds = duration.toSecondsPart();
String formattedDuration = String.format("%02d:%02d:%02d", hours, minutes, seconds);
```

#### Tratamento de Exceções
Exceções customizadas para feedback claro:

| Exceção | Status | Descrição |
|---------|--------|-----------|
| `ModelAlreadyExistException` | 409 Conflict | Check-in já existe |
| `ModelNotFoundException` | 404 Not Found | Check-in não encontrado |

### Frontend

#### Guards de Rota

**AuthGuard**: Protege rotas que requerem autenticação
```typescript
canActivate(): boolean {
  if (this.authService.isAuthenticated) {
    return true;
  }
  this.router.navigate(['/logins']);
  return false;
}
```

**RoleGuard**: Protege rotas baseadas em perfil
```typescript
canActivate(route: ActivatedRouteSnapshot): boolean {
  const requiredRole = route.data['role'];
  if (this.authService.hasRole(requiredRole)) {
    return true;
  }
  this.router.navigate(['/unauthorized']);
  return false;
}
```

#### Server-Side Rendering (SSR)
O projeto utiliza Angular Universal para SSR. Guards são protegidos contra erros de `localStorage` no servidor:

```typescript
constructor(@Inject(PLATFORM_ID) private platformId: Object) {}

get isAuthenticated(): boolean {
  if (isPlatformBrowser(this.platformId)) {
    return !!localStorage.getItem('user');
  }
  return false;
}
```

#### Feedback Visual
Mensagens de sucesso/erro com temporização automática:

```typescript
setTimeout(() => {
  this.successMessage = 'Check-in realizado com sucesso!';
  setTimeout(() => this.successMessage = '', 5000);
}, 0);
```

---

## 🚀 Próximas Melhorias

- [ ] **Autenticação JWT**: Implementar tokens JWT no backend
- [ ] **Hash de Senhas**: Usar BCrypt para senhas
- [ ] **Testes Unitários**: Implementar testes no frontend e backend
- [ ] **Relatórios**: Exportar registros em PDF/Excel
- [ ] **Filtros**: Filtrar registros por data, funcionário, etc.
- [ ] **Edição de Registros**: Permitir correção de registros pelo manager
- [ ] **Dashboard**: Gráficos de horas trabalhadas por período
- [ ] **Notificações**: Alertas para funcionários sem check-out
- [ ] **Mobile**: Versão mobile com Ionic ou React Native

---

## 👨‍💻 Autor

**Thallys Cezar**

- GitHub: [@ThallysCezar](https://github.com/ThallysCezar)
- LinkedIn: [Thallys Cezar](https://www.linkedin.com/in/thallyscezar/)

---

<div align="center">

**⭐ Desenvolvido como parte do Desafio 1 - Grupo Moura ⭐**

[⬆ Voltar ao topo](#️-desafio-1-sistema-de-controle-de-ponto)

</div>

## 🔐 IGNORE EVERYTHING BELOW - OLD E-COMMERCE CONTENT

**Funcionalidades**:
- ✅ Validação centralizada de tokens JWT
- ✅ Roteamento inteligente para microserviços via Eureka
- ✅ Propagação de headers de autenticação (`X-User-Id`, `X-User-Role`, `X-User-Email`)
- ✅ Configuração de CORS
- ✅ Load balancing automático
- ✅ Endpoints públicos sem autenticação (`/auth/**`)

**Rotas**:
```
/ms-usuarios/**   → ms-usuarios   (Auth & User Management)
/ms-produtos/**   → ms-produtos   (Product Catalog)
/ms-pedidos/**    → ms-pedidos    (Order Management)
/ms-pagamentos/** → ms-pagamentos (Payment Processing)
```

---

### 2️⃣ ms-usuarios
**Responsabilidade**: Autenticação, autorização e gerenciamento de usuários

**Funcionalidades**:
- 🔐 Login e registro de usuários
- 🎫 Geração de tokens JWT
- 👥 CRUD completo de usuários
- 🛡️ Controle de roles (USER, ADMIN)
- ✅ Ownership validation

**Acesso via API Gateway**:
```
http://localhost:8082/ms-usuarios/**
```

**Endpoints Principais** (via Gateway):
```http
POST   http://localhost:8082/ms-usuarios/auth/register           # Registro (público)
POST   http://localhost:8082/ms-usuarios/auth/login              # Login (público)
GET    http://localhost:8082/ms-usuarios/usuarios                # Listar todos (ADMIN)
GET    http://localhost:8082/ms-usuarios/usuarios/{id}           # Buscar por ID (Owner/ADMIN)
GET    http://localhost:8082/ms-usuarios/usuarios/email/{email}  # Buscar por email (autenticado)
POST   http://localhost:8082/ms-usuarios/usuarios                # Criar usuário (ADMIN)
PUT    http://localhost:8082/ms-usuarios/usuarios/update/{id}    # Atualizar (Owner/ADMIN)
DELETE http://localhost:8082/ms-usuarios/usuarios/delete/{id}    # Deletar (ADMIN)
```

**Comunicação**:
- 📤 Publica `usuarios.criados` no RabbitMQ
- 📤 Publica `usuarios.atualizados` no RabbitMQ

---

### 3️⃣ ms-produtos 
**Responsabilidade**: Gerenciamento de catálogo de produtos

**Funcionalidades**:
- 📦 CRUD de produtos
- 🔍 Listagem pública de produtos
- 📝 Criação em lote (batch)
- 🛡️ Modificações apenas para ADMIN

**Acesso via API Gateway**:
```
http://localhost:8082/ms-produtos/**
```

**Endpoints Principais** (via Gateway):
```http
GET    http://localhost:8082/ms-produtos/produtos              # Listar todos (público)
GET    http://localhost:8082/ms-produtos/produtos/{id}         # Buscar por ID (público)
POST   http://localhost:8082/ms-produtos/produtos              # Criar produto (ADMIN)
POST   http://localhost:8082/ms-produtos/produtos/batch        # Criar múltiplos (ADMIN)
PUT    http://localhost:8082/ms-produtos/produtos/update/{id}  # Atualizar (ADMIN)
DELETE http://localhost:8082/ms-produtos/produtos/delete/{id}  # Deletar (ADMIN)
```

**Regra de Negócio**:
- ✅ **GET**: Acesso público (sem autenticação)
- 🔒 **POST/PUT/DELETE**: Apenas ADMIN

**Comunicação**:
- 📤 Publica `produtos.criados` no RabbitMQ
- 📤 Publica `produtos.atualizados` no RabbitMQ
- 📤 Publica `produtos.deletados` no RabbitMQ

---

### 4️⃣ ms-pedidos
**Responsabilidade**: Gerenciamento de pedidos (orders)

**Funcionalidades**:
- 🛒 Criação de pedidos
- 📋 Listagem com filtro por usuário
- 🔒 Ownership validation complexa
- ✅ Confirmação de pedidos
- 💳 Associação de pagamentos
- ❌ Cancelamento de pedidos

**Acesso via API Gateway**:
```
http://localhost:8082/ms-pedidos/**
```

**Endpoints Principais** (via Gateway):
```http
GET    http://localhost:8082/ms-pedidos/pedidos                        # Listar (USER: próprios | ADMIN: todos)
GET    http://localhost:8082/ms-pedidos/pedidos/{id}                   # Buscar por ID (Owner/ADMIN)
GET    http://localhost:8082/ms-pedidos/pedidos/user/{id}              # Pedidos de um usuário (Owner/ADMIN)
POST   http://localhost:8082/ms-pedidos/pedidos                        # Criar pedido (autenticado)
PUT    http://localhost:8082/ms-pedidos/pedidos/update/{id}            # Atualizar (Owner/ADMIN)
PATCH  http://localhost:8082/ms-pedidos/pedidos/{id}/confirmarPedido   # Confirmar pedido (Owner/ADMIN)
POST   http://localhost:8082/ms-pedidos/pedidos/{id}/pagamento         # Adicionar pagamento (Owner/ADMIN)
DELETE http://localhost:8082/ms-pedidos/pedidos/delete/{id}            # Deletar (Owner/ADMIN)
PATCH  http://localhost:8082/ms-pedidos/pedidos/cancelarPedido/{id}    # Cancelar (Owner/ADMIN)
```

**Regras de Negócio**:
- 🔒 **Ownership Validation**: USER vê apenas seus pedidos
- 👑 **ADMIN Bypass**: ADMIN acessa todos os pedidos
- 🔗 **Feign Client**: Busca dados de usuários via ms-usuarios

**Comunicação**:
- 📤 Publica `pedidos.novos` no RabbitMQ
- 📤 Publica `pedidos.confirmados` no RabbitMQ
- 📤 Publica `pedidos.cancelados` no RabbitMQ
- 🔗 Consome dados de `ms-usuarios` via Feign

---

### 5️⃣ ms-pagamentos (Porta interna: 8086)
**Responsabilidade**: Processamento de pagamentos

**Funcionalidades**:
- 💰 CRUD de pagamentos
- 💳 Tipos de pagamento (BOLETO, PIX, CARTAO, etc.)
- ✅ Confirmação de pagamentos
- 🔒 Acesso exclusivo para ADMIN

**Acesso via API Gateway**:
```
http://localhost:8082/ms-pagamentos/**
```

**Endpoints Principais** (via Gateway):
```http
GET    http://localhost:8082/ms-pagamentos/pagamentos                   # Listar todos (ADMIN)
GET    http://localhost:8082/ms-pagamentos/pagamentos/{id}              # Buscar por ID (ADMIN)
GET    http://localhost:8082/ms-pagamentos/pagamentos/pedido/{idPedido} # Buscar por pedido (ADMIN)
POST   http://localhost:8082/ms-pagamentos/pagamentos/pedido/create     # Criar pagamento (ADMIN)
PUT    http://localhost:8082/ms-pagamentos/pagamentos/update/{id}       # Atualizar (ADMIN)
PATCH  http://localhost:8082/ms-pagamentos/pagamentos/{id}/confirmar    # Confirmar pagamento (ADMIN)
DELETE http://localhost:8082/ms-pagamentos/pagamentos/delete/{id}       # Deletar (ADMIN)
```

**Regra de Negócio**:
- 🔒 **Admin Only**: Todas operações restritas a ADMIN
- 💼 **Razão**: Operações financeiras sensíveis

**Comunicação**:
- 📤 Publica `pagamentos.processados` no RabbitMQ
- 📤 Publica `pagamentos.confirmados` no RabbitMQ

---

### 6️⃣ ms-database
**Responsabilidade**: Persistência de dados e acesso ao banco PostgreSQL

**Funcionalidades**:
- 💾 Persistência de todas as entidades
- 🔄 Listeners RabbitMQ para operações assíncronas
- 📊 Repositories JPA
- 🔗 Endpoints REST para acesso direto (interno)

**Entidades**:
- `Usuarios` (id, nome, email, senha, telefone, role)
- `Produtos` (id, nome, descricao, preco, quantidade)
- `Pedidos` (id, usuario, itens, status, dataCriacao)
- `ItemDoPedido` (id, pedido, produto, quantidade, preco)
- `Pagamento` (id, pedido, valor, tipoPagamento, status)

**RabbitMQ Listeners**:
- `UsuariosListener`: Queues e DLQs.
- `ProdutosListener`: Queues e DLQs.
- `PedidosListener`: Queues e DLQs.
- `PagamentoListener`: Queues e DLQs.

**Comunicação**:
- 📥 Consome mensagens de todos os microserviços
- 💾 Persiste dados no PostgreSQL

---

### 7️⃣ server (Eureka Server - Port 8081)
**Responsabilidade**: Service Discovery e registro de microserviços

**Funcionalidades**:
- 🔍 Descoberta automática de serviços
- ❤️ Health checks de microserviços
- 🔄 Load balancing coordination
- 📊 Dashboard de monitoramento

**Dashboard**: http://localhost:8081

---

### 8️⃣ common-dtos (Biblioteca Compartilhada)
**Responsabilidade**: DTOs compartilhados entre microserviços

**Classes**:
```java
- Role
- UsuariosDTO
- ProdutosDTO
- PedidosDTO
- PagamentoDTO
- Enums
  - StatusPagamento
  - StatusPedidos
  - TipoFormaPagamento
- ItemDoPedidoDTO
- PagamentoPedidoUpdateDTO
- ProdutoIdDTO
- UsuarioIdDTO
- LoginRequestDTO
- LoginResponseDTO
- RegisterRequestDTO
```

**Benefício**: Evita duplicação de código e garante consistência

---

## 📥 Instalação e Configuração

### Pré-requisitos

| Software | Versão Mínima | Download |
|----------|---------------|----------|
| **Java JDK** | 17+ | [Oracle JDK](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) ou [OpenJDK](https://openjdk.org/) |
| **Maven** | 3.8+ | [Apache Maven](https://maven.apache.org/download.cgi) |
| **PostgreSQL** | 15+ | [PostgreSQL](https://www.postgresql.org/download/) |
| **RabbitMQ** | 3.x+ | [RabbitMQ](https://www.rabbitmq.com/download.html) |
| **Git** | 2.x+ | [Git](https://git-scm.com/downloads) |

### 1️⃣ Clonar o Repositório

```bash
git clone https://github.com/ThallysCezar/Project-Ecommerce-Microsservices.git
cd Project-Ecommerce-Microsservices
```

### 2️⃣ Configurar PostgreSQL

```sql
-- Conectar ao PostgreSQL
psql -U postgres

-- Criar banco de dados
CREATE DATABASE postgres;

-- Configurar usuário (opcional)
CREATE USER ecommerce WITH PASSWORD '123456';
GRANT ALL PRIVILEGES ON DATABASE postgres TO ecommerce;
```

### 3️⃣ Configurar RabbitMQ

```bash
# Iniciar RabbitMQ
# Windows
rabbitmq-server.bat

# Linux/Mac
rabbitmq-server

# Acessar dashboard
http://localhost:15672
# Usuário: guest
# Senha: guest
```

### 4️⃣ Configurar Variáveis de Ambiente (Opcional)

Você pode personalizar as configurações criando um arquivo `.env` ou editando os `application.properties`:

```properties
# ms-database/src/main/resources/application.properties
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=USERNAME
spring.datasource.password=PASSWORD

spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest

# Todos os microserviços
jwt.secret=minha-chave-secreta-super-segura-para-jwt-com-minimo-256-bits-de-seguranca
jwt.expiration=86400000
```

### 5️⃣ Compilar Todos os Projetos

```bash
# Compilar common-dtos primeiro (dependência)
cd common-dtos
mvn clean install

# Voltar à raiz
cd ..

# Compilar todos os microserviços
mvn clean package -DskipTests
```

### 6️⃣ Iniciar os Serviços (ORDEM IMPORTANTE)

```powershell
# 1. Eureka Server (Service Discovery)
cd server
java -jar target/server-0.0.1-SNAPSHOT.jar

# 2. ms-database (Database Service)
cd ../ms-database
java -jar target/ms-database-0.0.1-SNAPSHOT.jar

# 3. API Gateway
cd ../api-gateway
java -jar target/api-gateway-0.0.1-SNAPSHOT.jar

# 4. ms-usuarios (Authentication)
cd ../ms-usuarios
java -jar target/ms-usuarios-0.0.1-SNAPSHOT.jar

# 5. ms-produtos
cd ../ms-produtos
java -jar target/ms-produtos-0.0.1-SNAPSHOT.jar

# 6. ms-pedidos
cd ../ms-pedidos
java -jar target/ms-pedidos-0.0.1-SNAPSHOT.jar

# 7. ms-pagamentos
cd ../ms-pagamentos
java -jar target/ms-pagamentos-0.0.1-SNAPSHOT.jar
```

---

## Docker e Containerização

### 📦 Executar com Docker Compose (Recomendado)

A forma mais rápida de rodar toda a aplicação é usando Docker Compose:

```bash
# Na raiz do projeto
docker-compose up -d
```

Este comando irá:
- ✅ Construir as imagens Docker de todos os microsserviços
- ✅ Iniciar PostgreSQL e RabbitMQ automaticamente
- ✅ Configurar a rede entre os containers
- ✅ Aguardar a ordem correta de inicialização (health checks)

### 🔍 Verificar Status dos Containers

```bash
# Ver todos os containers em execução
docker-compose ps

# Ver logs de todos os serviços
docker-compose logs -f

# Ver logs de um serviço específico
docker-compose logs -f ms-usuarios
```

### 🛑 Parar os Serviços

```bash
# Parar todos os containers
docker-compose down

# Parar e remover volumes (limpar banco de dados)
docker-compose down -v
```

### 📋 Estrutura Docker

Cada microsserviço possui seu próprio `Dockerfile`:

```
├── api-gateway/Dockerfile
├── ms-usuarios/Dockerfile
├── ms-produtos/Dockerfile
├── ms-pedidos/Dockerfile
├── ms-pagamentos/Dockerfile
├── ms-database/Dockerfile
├── server/Dockerfile (Eureka)
├── ms-configs/Dockerfile (Config Server)
└── docker-compose.yml (Orquestração completa)
```

### 🌐 Acessar a Aplicação

Após ~2 minutos, todos os serviços estarão disponíveis:

- **API Gateway**: http://localhost:8082
- **Eureka Dashboard**: http://localhost:8081
- **RabbitMQ Management**: http://localhost:15672 (guest/guest)
- **PostgreSQL**: localhost:5432

## 📚 Documentação de Endpoints

### 🔓 Autenticação (Endpoints Públicos)

#### Registrar Novo Usuário

```http
POST http://localhost:8082/ms-usuarios/auth/register
Content-Type: application/json

{
  "nome": "João Silva",
  "email": "joao@example.com",
  "password": "senha123",
  "telefone": "11987654321",
  "role": "USER"
}
```

**Resposta** (201 Created):
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2FvQGV4YW1wbGUuY29tIiwiaWQiOjEsInJvbGUiOiJVU0VSIiwiaWF0IjoxNzM0NzM0MDAwLCJleHAiOjE3MzQ4MjA0MDB9.signature",
  "type": "Bearer",
  "userId": 1,
  "userName": "João Silva",
  "email": "joao@example.com",
  "role": "USER"
}
```

#### Login

```http
POST http://localhost:8082/ms-usuarios/auth/login
Content-Type: application/json

{
  "email": "joao@example.com",
  "password": "senha123"
}
```

**Resposta** (200 OK):
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "type": "Bearer",
  "userId": 1,
  "userName": "João Silva",
  "email": "joao@example.com",
  "role": "USER"
}
```

---

### 👤 Usuários (Autenticação Necessária)

#### Listar Todos os Usuários (ADMIN)

```http
GET http://localhost:8082/ms-usuarios/usuarios
Authorization: Bearer {token}
```

#### Buscar Usuário por ID (Owner/ADMIN)

```http
GET http://localhost:8082/ms-usuarios/usuarios/1
Authorization: Bearer {token}
```

#### Atualizar Usuário (Owner/ADMIN)

```http
PUT http://localhost:8082/ms-usuarios/usuarios/update/1
Authorization: Bearer {token}
Content-Type: application/json

{
  "nome": "João Silva Atualizado",
  "telefone": "11999999999"
}
```

#### Deletar Usuário (ADMIN)

```http
DELETE http://localhost:8082/ms-usuarios/usuarios/delete/1
Authorization: Bearer {token_admin}
```

---

### 📦 Produtos

#### Listar Produtos (Público)

```http
GET http://localhost:8082/ms-produtos/produtos
```

**Resposta**:
```json
[
  {
    "id": 1,
    "nome": "Notebook Dell",
    "descricao": "Notebook Dell Inspiron 15",
    "preco": 3500.00,
    "quantidade": 10
  },
  {
    "id": 2,
    "nome": "Mouse Logitech",
    "descricao": "Mouse sem fio",
    "preco": 150.00,
    "quantidade": 50
  }
]
```

#### Criar Produto (ADMIN)

```http
POST http://localhost:8082/ms-produtos/produtos
Authorization: Bearer {token_admin}
Content-Type: application/json

{
  "nome": "Teclado Mecânico",
  "descricao": "Teclado mecânico RGB",
  "preco": 450.00,
  "quantidade": 20
}
```

#### Criar Múltiplos Produtos (ADMIN)

```http
POST http://localhost:8082/ms-produtos/produtos/batch
Authorization: Bearer {token_admin}
Content-Type: application/json

[
  {
    "nome": "Monitor LG 24''",
    "descricao": "Monitor Full HD",
    "preco": 800.00,
    "quantidade": 15
  },
  {
    "nome": "Webcam Logitech",
    "descricao": "Webcam HD",
    "preco": 250.00,
    "quantidade": 30
  }
]
```

#### Atualizar Produto (ADMIN)

```http
PUT http://localhost:8082/ms-produtos/produtos/update/1
Authorization: Bearer {token_admin}
Content-Type: application/json

{
  "nome": "Notebook Dell Atualizado",
  "preco": 3200.00,
  "quantidade": 5
}
```

#### Deletar Produto (ADMIN)

```http
DELETE http://localhost:8082/ms-produtos/produtos/delete/1
Authorization: Bearer {token_admin}
```

---

### 🛒 Pedidos

#### Listar Pedidos (USER: próprios | ADMIN: todos)

```http
GET http://localhost:8082/ms-pedidos/pedidos
Authorization: Bearer {token}
```

**Resposta (USER)**:
```json
[
  {
    "id": 1,
    "usuario": {
      "id": 1,
      "nome": "João Silva",
      "email": "joao@example.com"
    },
    "itens": [
      {
        "id": 1,
        "produto": {
          "id": 1,
          "nome": "Notebook Dell",
          "preco": 3500.00
        },
        "quantidade": 1,
        "preco": 3500.00
      }
    ],
    "status": "PENDENTE",
    "dataCriacao": "2024-01-20T10:00:00"
  }
]
```

#### Buscar Pedido por ID (Owner/ADMIN)

```http
GET http://localhost:8082/ms-pedidos/pedidos/1
Authorization: Bearer {token}
```

#### Criar Pedido (Autenticado)

```http
POST http://localhost:8082/ms-pedidos/pedidos
Authorization: Bearer {token}
Content-Type: application/json

{
  "usuario": {
    "id": 1
  },
  "itens": [
    {
      "produto": {
        "id": 1
      },
      "quantidade": 2,
      "preco": 3500.00
    },
    {
      "produto": {
        "id": 2
      },
      "quantidade": 1,
      "preco": 150.00
    }
  ]
}
```

**Resposta com HATEOAS**:
```json
{
  "pedido": {
    "id": 1,
    "usuario": {...},
    "itens": [...],
    "status": "PENDENTE"
  },
  "message": "Pedido criado com sucesso!",
  "_links": {
    "processar-pagamento-boleto": {
      "href": "http://localhost:8082/ms-pedidos/pedidos/1/pagamento",
      "title": "POST - Processar pagamento via BOLETO"
    },
    "processar-pagamento-pix": {
      "href": "http://localhost:8082/ms-pedidos/pedidos/1/pagamento",
      "title": "POST - Processar pagamento via PIX"
    },
    "confirmar-pedido": {
      "href": "http://localhost:8082/ms-pedidos/pedidos/1/confirmarPedido",
      "title": "PATCH - Confirmar o pedido"
    }
  }
}
```

#### Confirmar Pedido (Owner/ADMIN)

```http
PATCH http://localhost:8082/ms-pedidos/pedidos/1/confirmarPedido
Authorization: Bearer {token}
```

#### Adicionar Pagamento ao Pedido (Owner/ADMIN)

```http
POST http://localhost:8082/ms-pedidos/pedidos/1/pagamento
Authorization: Bearer {token}
Content-Type: application/json

{
  "valor": 7150.00,
  "tipoPagamento": "BOLETO"
}
```

**Tipos de Pagamento Aceitos**:
- `BOLETO`
- `PIX`
- `CARTAO_CREDITO`
- `CARTAO_DEBITO`

#### Cancelar Pedido (Owner/ADMIN)

```http
PATCH http://localhost:8082/ms-pedidos/pedidos/cancelarPedido/1
Authorization: Bearer {token}
```

---

### 💳 Pagamentos (ADMIN Only)

#### Listar Todos os Pagamentos (ADMIN)

```http
GET http://localhost:8082/ms-pagamentos/pagamentos
Authorization: Bearer {token_admin}
```

#### Buscar Pagamento por ID (ADMIN)

```http
GET http://localhost:8082/ms-pagamentos/pagamentos/1
Authorization: Bearer {token_admin}
```

#### Buscar Pagamentos de um Pedido (ADMIN)

```http
GET http://localhost:8082/ms-pagamentos/pagamentos/pedido/1
Authorization: Bearer {token_admin}
```

#### Confirmar Pagamento (ADMIN)

```http
PATCH http://localhost:8082/ms-pagamentos/pagamentos/1/confirmar
Authorization: Bearer {token_admin}
```

---

## 🔐 Sistema de Autenticação e Autorização

### 🎫 JWT (JSON Web Tokens)

O sistema utiliza **JWT stateless** para autenticação. Cada token contém:

```json
{
  "sub": "joao@example.com",
  "id": 1,
  "role": "USER",
  "iat": 1734734000,
  "exp": 1734820400
}
```

**Configuração**:
- **Algoritmo**: HS256 (HMAC-SHA256)
- **Secret Key**: Compartilhada entre todos os microserviços
- **Expiração**: 24 horas (86400000 ms)

### 👥 Roles e Permissões

| Role | Descrição | Permissões |
|------|-----------|------------|
| **USER** | Usuário comum | • Ver próprios dados<br>• Criar pedidos<br>• Ver próprios pedidos<br>• Ver produtos (público) |
| **ADMIN** | Administrador | • Todas permissões de USER<br>• Gerenciar usuários<br>• Gerenciar produtos<br>• Ver todos pedidos<br>• Gerenciar pagamentos |

### 🔒 Ownership Validation

O sistema implementa validação de **propriedade de recursos**:

```java
@PreAuthorize("@ownershipValidator.isOwnerOrAdmin(#id)")
public ResponseEntity<?> updatePedido(@PathVariable Long id) {
    // Apenas o dono do pedido ou ADMIN pode atualizar
}
```

**Lógica**:
1. Extrai `userId` do token JWT
2. Compara com `pedido.usuario.id`
3. Permite se `userId == pedido.usuario.id` OU `role == ADMIN`

### 📊 Matriz de Permissões

| Operação | Público | USER | ADMIN |
|----------|---------|------|-------|
| **Autenticação** |
| Login | ✅ | ✅ | ✅ |
| Registro | ✅ | ✅ | ✅ |
| **Produtos** |
| Listar produtos | ✅ | ✅ | ✅ |
| Ver detalhes | ✅ | ✅ | ✅ |
| Criar produto | ❌ | ❌ | ✅ |
| Atualizar produto | ❌ | ❌ | ✅ |
| Deletar produto | ❌ | ❌ | ✅ |
| **Pedidos** |
| Listar pedidos | ❌ | ✅ (próprios) | ✅ (todos) |
| Ver pedido | ❌ | ✅ (próprio) | ✅ (todos) |
| Criar pedido | ❌ | ✅ | ✅ |
| Atualizar pedido | ❌ | ✅ (próprio) | ✅ (todos) |
| Confirmar pedido | ❌ | ✅ (próprio) | ✅ (todos) |
| Adicionar pagamento | ❌ | ✅ (próprio) | ✅ (todos) |
| Cancelar pedido | ❌ | ✅ (próprio) | ✅ (todos) |
| **Pagamentos** |
| Todas operações | ❌ | ❌ | ✅ |
| **Usuários** |
| Listar usuários | ❌ | ❌ | ✅ |
| Ver próprio perfil | ❌ | ✅ | ✅ |
| Ver outro perfil | ❌ | ❌ | ✅ |
| Atualizar próprio | ❌ | ✅ | ✅ |
| Atualizar outro | ❌ | ❌ | ✅ |
| Deletar usuário | ❌ | ❌ | ✅ |

---

## 🔄 Fluxo de Dados

### 📝 Ciclo Completo de Operação do E-commerce

```mermaid
graph TD
    A[🏁 Início do Fluxo] --> B[📝 ms-usuarios: Registrar Usuário]
    B --> C[🔑 ms-usuarios: Login Usuário]
    C --> D[🎫 Obter Bearer Token JWT]
    D --> E[📦 ms-produtos: Criar Produtos]
    E --> F[🛒 ms-pedidos: Criar Pedido]
    F --> G[💰 ms-pedidos: Confirmar Pagamento do Pedido]
    G --> H[⏳ Status Pagamento: PROCESSADO]
    H --> I[✅ ms-pagamentos: Confirmar Pagamento ADMIN]
    I --> J[✅ Status Pagamento: CONFIRMADO]
    J --> K[📋 ms-pedidos: Confirmar Pedido]
    K --> L[✅ Status Pedido: CONFIRMADO]
    L --> M[🏁 Fim do Fluxo]
```

## � CI/CD Pipeline

### 🎯 Visão Geral

Este projeto implementa um **pipeline completo de CI/CD** utilizando **GitHub Actions**.

### 📊 Arquitetura do Pipeline

```
┌──────────────────────────────────────────────────────────┐
│                     TRIGGER                              │
│  Push to main | Pull Request | Manual Dispatch          │
└────────────────────┬─────────────────────────────────────┘
                     │
                     ↓
┌──────────────────────────────────────────────────────────┐
│  Job 0: install-common-dtos (~2 min)                     │
│  ├─ Checkout código                                      │
│  ├─ Setup Java 17                                        │
│  ├─ Cache Maven dependencies                             │
│  ├─ Install common-dtos                                  │
│  └─ Upload Maven repository artifact                     │
└────────────────────┬─────────────────────────────────────┘
                     │
                     ↓
┌──────────────────────────────────────────────────────────┐
│  Job 1: build-and-test (~5 min - paralelo)              │
│  ├─ Matrix: 7 microserviços em paralelo                 │
│  ├─ Download Maven repository (com common-dtos)         │
│  ├─ Build de cada microserviço                          │
│  ├─ Execução de testes unitários                        │
│  └─ Upload de JARs como artifacts                       │
└────────────────────┬─────────────────────────────────────┘
                     │
                     ↓
┌──────────────────────────────────────────────────────────┐
│  Job 2: docker-build-push (~8 min - paralelo)           │
│  ├─ Matrix: 7 imagens Docker em paralelo                │
│  ├─ Checkout código                                      │
│  ├─ Setup Docker Buildx                                  │
│  ├─ Login Docker Hub                                     │
│  ├─ Build imagem Docker                                  │
│  ├─ Tag: latest, sha-{commit}                           │
│  └─ Push para Docker Hub                                │
└────────────────────┬─────────────────────────────────────┘
                     │
                     ↓
┌──────────────────────────────────────────────────────────┐
│  Job 3: notify-success (~5 seg)                         │
│  └─ Mensagem de sucesso                                 │
└──────────────────────────────────────────────────────────┘
```

---

### 🚀 Workflow Triggers

| Evento | Descrição | Quando |
|--------|-----------|--------|
| **Push** | `push` to `main` | Código enviado para branch principal |
| **Pull Request** | `pull_request` to `main` | PR aberto/atualizado |
| **Manual** | `workflow_dispatch` | Execução manual via GitHub UI |

### 📝 Configurações de Teste

#### ms-database
- **Profile de Teste:** `test`
- **Database:** H2 (in-memory)
- **RabbitMQ:** Mocked (ConnectionFactory + RabbitTemplate)
- **Eureka:** Disabled
- **Flyway:** Disabled

```properties
# application-test.properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.jpa.hibernate.ddl-auto=create-drop
spring.flyway.enabled=false
eureka.client.enabled=false
```

#### Demais Microserviços
- **Tests:** Spring Boot Test + JUnit 5
- **Mocking:** Mockito para dependências externas
- **Coverage:** Testes de contexto (context loads)

### 📊 Monitoramento

#### GitHub Actions Dashboard
```
https://github.com/ThallysCezar/Project-Ecommerce-Microsservices/actions
```

**Informações Disponíveis:**
- ✅ Status de cada job (success/failure)
- ✅ Tempo de execução de cada step
- ✅ Logs detalhados de build/test
- ✅ Artifacts gerados
- ✅ Histórico de execuções

#### Docker Hub Registry
```
https://hub.docker.com/u/thallyscezar
```

**Informações Disponíveis:**
- ✅ Lista de todas as imagens
- ✅ Tags disponíveis (latest, sha-*)
- ✅ Data de upload
- ✅ Tamanho das imagens
- ✅ Pull count

### 🔄 Fluxo de Desenvolvimento

```
Developer                GitHub Actions              Docker Hub
    │                          │                         │
    │ 1. git push origin main  │                         │
    ├─────────────────────────►│                         │
    │                          │                         │
    │                          │ 2. Build & Test         │
    │                          │    (15-20 min)          │
    │                          │                         │
    │                          │ 3. Docker Build         │
    │                          ├────────────────────────►│
    │                          │                         │
    │                          │ 4. Push Images          │
    │                          ├────────────────────────►│
    │                          │    ✅ Success           │
    │ 5. Notificação           │                         │
    │◄─────────────────────────┤                         │
    │                          │                         │
    │ 6. kubectl apply         │                         │
    │  (atualiza K8s)          │                         │
    │                          │                         │
```

---

### 🎯 Próximas Melhorias

- [ ] Integração com SonarQube para análise de código
- [ ] Code coverage reports
- [ ] Trivy para scan de vulnerabilidades
- [ ] Deploy automático para Kubernetes
- [ ] Notificações Slack/Discord
- [ ] Environments (staging/production)
- [ ] Rollback automático em falhas

---

## 🗺️ Roadmap

### ✅ Concluído

#### Arquitetura & Backend
- [x] Arquitetura de microserviços
- [x] Autenticação JWT
- [x] Controle de acesso por roles
- [x] Ownership validation
- [x] Comunicação assíncrona (RabbitMQ)
- [x] Service Discovery (Eureka)
- [x] API Gateway
- [x] HATEOAS links
- [x] Documentação completa
- [x] Swagger/OpenAPI documentation
- [x] Docker Compose para ambiente completo
- [x] Testes unitários e de integração
- [x] Kubernetes Deployment com K3d
- [x] CI/CD Pipeline (GitHub Actions)

### 📋 Próximas Funcionalidades

- [ ] **Refresh Tokens**
  - Tokens de refresh de longa duração
  - Revogação de tokens
  - Blacklist de tokens

- [ ] **Observabilidade**
  - Logging centralizado (ELK Stack)
  - Distributed tracing (Zipkin/Jaeger)
  - Métricas (Prometheus + Grafana)
  - Alertas automatizados

- [ ] **Resiliência**
  - Circuit Breaker (Resilience4j)
  - Rate Limiting
  - Retry policies
  - Timeout configuration

- [ ] **Cache**
  - Redis para cache distribuído
  - Cache de tokens validados
  - Cache de produtos

- [ ] **OAuth2 Social Login**
  - Login com Google
  - Login com GitHub
  - Login com Facebook

- [ ] **Notificações**
  - Email de confirmação de pedido
  - SMS para pagamentos
  - WebSocket para atualizações em tempo real

- [ ] **Admin Dashboard**
  - Interface web para gerenciamento
  - Visualização de métricas
  - Logs de auditoria

- [ ] **Kubernetes Deployment**
  - Helm charts
  - Auto-scaling
  - Service mesh (Istio)

---

## ☸️ Kubernetes Deployment

Este projeto está completamente containerizado e pronto para deploy em ambientes Kubernetes, incluindo **K3d**, **minikube**, **EKS**, **AKS** ou **GKE**.

### 📊 Arquitetura Kubernetes

```
┌─────────────────────────────────────────────────────────┐
│              KUBERNETES CLUSTER (K3d)                    │
│                 Namespace: ecommerce                     │
├─────────────────────────────────────────────────────────┤
│                                                          │
│  ┌────────────────────────────────────────────────┐    │
│  │      API GATEWAY (LoadBalancer)                │    │
│  │  Image: thallyscezar/ecommerce-api-gateway     │    │
│  │  Container Port: 8082                          │    │
│  │  Service: LoadBalancer → Port 8080             │    │
│  │  Access: kubectl port-forward 8080:8080        │    │
│  └────────────┬───────────────────────────────────┘    │
│               │                                         │
│               ↓ Roteia Requisições via Service Names   │
│               │                                         │
│  ┌────────────┴───────────────────────────────────┐   │
│  │    EUREKA SERVER (Service Discovery)           │   │
│  │    Image: thallyscezar/ecommerce-eureka-server │   │
│  │    Container Port: 8081 → Service Port: 8761   │   │
│  │    Environment: SERVER_PORT=8081               │   │
│  └────────────────────────────────────────────────┘   │
│               ↑ Microservices se registram aqui       │
│               │                                        │
│  ┌────────────┴───────────────────────────────────┐   │
│  │         MICROSERVICES (Port 8080)              │   │
│  ├────────────────────────────────────────────────┤   │
│  │ • ms-database   (Database Management)          │   │
│  │   Image: thallyscezar/ecommerce-ms-database    │   │
│  │                                                 │   │
│  │ • ms-usuarios   (Authentication & Users)       │   │
│  │   Image: thallyscezar/ecommerce-ms-usuarios    │   │
│  │                                                 │   │
│  │ • ms-produtos   (Product Catalog)              │   │
│  │   Image: thallyscezar/ecommerce-ms-produtos    │   │
│  │                                                 │   │
│  │ • ms-pedidos    (Order Management)             │   │
│  │   Image: thallyscezar/ecommerce-ms-pedidos     │   │
│  │                                                 │   │
│  │ • ms-pagamentos (Payment Processing)           │   │
│  │   Image: thallyscezar/ecommerce-ms-pagamentos  │   │
│  └──────────┬────────────────────┬────────────────┘   │
│             │                    │                     │
│             ↓                    ↓                     │
│  ┌──────────────────┐  ┌─────────────────────┐       │
│  │   POSTGRESQL     │  │     RABBITMQ        │       │
│  │  StatefulSet     │  │   StatefulSet       │       │
│  │  Port: 5432      │  │   Ports: 5672/15672 │       │
│  │  PVC: 5Gi        │  │   PVC: 2Gi          │       │
│  │  Image: postgres │  │   Image: rabbitmq   │       │
│  │  :15-alpine      │  │   :3-management     │       │
│  └──────────────────┘  └─────────────────────┘       │
│                                                        │
└────────────────────────────────────────────────────────┘
```

### 🎯 Componentes Kubernetes

#### **📦 Recursos Criados**

| Tipo | Nome | Quantidade | Descrição |
|------|------|------------|-----------|
| **Namespace** | `ecommerce` | 1 | Isolamento lógico de recursos |
| **ConfigMap** | `eureka-config`<br>`database-config`<br>`rabbitmq-config` | 3 | Configurações de aplicação |
| **Secret** | `postgres-secret`<br>`rabbitmq-secret` | 2 | Credenciais sensíveis |
| **StatefulSet** | `postgres`<br>`rabbitmq` | 2 | Infraestrutura com estado |
| **Deployment** | `eureka-server`<br>`api-gateway`<br>`ms-*` (5 microservices) | 7 | Aplicações stateless |
| **Service** | ClusterIP (8x)<br>LoadBalancer (1x) | 9 | Comunicação entre pods |
| **PersistentVolumeClaim** | `postgres-pvc`<br>`rabbitmq-pvc` | 2 | Armazenamento persistente |

### 🗂️ Estrutura de Manifests

```
k8s/
├── namespace.yaml                    # Namespace ecommerce
├── configmaps/
│   └── app-config.yaml              # Configurações (Eureka, DB, RabbitMQ)
├── secrets/
│   └── credentials.yaml             # Senhas PostgreSQL e RabbitMQ
├── infrastructure/
│   ├── postgres/
│   │   └── postgres.yaml            # PostgreSQL StatefulSet + PVC + Service
│   └── rabbitmq/
│       └── rabbitmq.yaml            # RabbitMQ StatefulSet + PVC + Service
├── discovery/
│   └── eureka-server.yaml           # Eureka Server Deployment + Service
├── microservices/
│   ├── ms-database/
│   │   └── deployment.yaml          # ms-database Deployment + Service
│   ├── ms-usuarios/
│   │   └── deployment.yaml          # ms-usuarios Deployment + Service
│   ├── ms-produtos/
│   │   └── deployment.yaml          # ms-produtos Deployment + Service
│   ├── ms-pedidos/
│   │   └── deployment.yaml          # ms-pedidos Deployment + Service
│   └── ms-pagamentos/
│       └── deployment.yaml          # ms-pagamentos Deployment + Service
└── gateway/
    └── api-gateway.yaml             # API Gateway Deployment + LoadBalancer
```

### ⚙️ Configurações Importantes

#### **🔌 Portas Configuradas**

| Serviço | Container Port | Service Port | Tipo | Acesso |
|---------|---------------|--------------|------|--------|
| **Eureka Server** | 8081 | 8761 | ClusterIP | Interno |
| **API Gateway** | 8082 | 8080 | LoadBalancer | Port-forward |
| **ms-database** | 8080 | 8080 | ClusterIP | Interno |
| **ms-usuarios** | 8080 | 8080 | ClusterIP | Interno |
| **ms-produtos** | 8080 | 8080 | ClusterIP | Interno |
| **ms-pedidos** | 8080 | 8080 | ClusterIP | Interno |
| **ms-pagamentos** | 8080 | 8080 | ClusterIP | Interno |
| **PostgreSQL** | 5432 | 5432 | ClusterIP (Headless) | Interno |
| **RabbitMQ** | 5672, 15672 | 5672, 15672 | ClusterIP | Interno |

> 💡 **Nota**: Todos os microservices usam porta 8080, mas não há conflito devido ao isolamento por **pod IP** no Kubernetes.

### 🚀 Deploy no Kubernetes (K3d)

#### **Pré-requisitos**

- **Docker** instalado
- **kubectl** instalado
- **K3d** instalado

```powershell
# Instalar K3d (Windows via Chocolatey)
choco install k3d

# Ou via Scoop
scoop install k3d
```

#### **1️⃣ Criar Cluster K3d**

```powershell
# Criar cluster local
k3d cluster create projectecommercemicro

# Verificar cluster
k3d cluster list
kubectl cluster-info
```

#### **2️⃣ Deploy Manual (Passo a Passo)**

```powershell
# 1. Criar namespace
kubectl apply -f k8s/namespace.yaml

# 2. Aplicar ConfigMaps e Secrets
kubectl apply -f k8s/configmaps/
kubectl apply -f k8s/secrets/

# 3. Deploy da infraestrutura (PostgreSQL e RabbitMQ)
kubectl apply -f k8s/infrastructure/postgres/
kubectl apply -f k8s/infrastructure/rabbitmq/

# Aguardar infraestrutura ficar pronta (pode levar 2-3 minutos)
kubectl wait --for=condition=ready pod -l app=postgres -n ecommerce --timeout=180s
kubectl wait --for=condition=ready pod -l app=rabbitmq -n ecommerce --timeout=180s

# 4. Deploy do Eureka Server
kubectl apply -f k8s/discovery/

# Aguardar Eureka ficar pronto
kubectl wait --for=condition=ready pod -l app=eureka-server -n ecommerce --timeout=120s

# 5. Deploy dos Microservices
kubectl apply -f k8s/microservices/ms-database/
kubectl apply -f k8s/microservices/ms-usuarios/
kubectl apply -f k8s/microservices/ms-produtos/
kubectl apply -f k8s/microservices/ms-pedidos/
kubectl apply -f k8s/microservices/ms-pagamentos/

# Aguardar microservices ficarem prontos (5-10 minutos)
Start-Sleep -Seconds 60

# 6. Deploy do API Gateway
kubectl apply -f k8s/gateway/

# Aguardar Gateway ficar pronto
kubectl wait --for=condition=ready pod -l app=api-gateway -n ecommerce --timeout=120s

# 7. Verificar status final
kubectl get pods -n ecommerce
kubectl get svc -n ecommerce
```

---

### 🔍 Verificação e Monitoramento

#### **Ver Status dos Pods**

```powershell
# Ver todos os pods
kubectl get pods -n ecommerce

# Ver pods com mais detalhes
kubectl get pods -n ecommerce -o wide

# Assistir mudanças em tempo real
kubectl get pods -n ecommerce -w
```

**Saída Esperada:**
```
NAME                             READY   STATUS    RESTARTS   AGE
api-gateway-5b86756856-75qdt     1/1     Running   0          10m
eureka-server-69f78dcb96-zp6gp   1/1     Running   0          12m
ms-database-5d876fd68b-k76p9     1/1     Running   0          10m
ms-pagamentos-7d9f8c5b4d-xyz12   1/1     Running   0          10m
ms-pedidos-8c6d9f7e3a-abc34      1/1     Running   0          10m
ms-produtos-9e7f8d6c2b-def56     1/1     Running   0          10m
ms-usuarios-fdb8dc58b-qwglh      1/1     Running   0          10m
postgres-0                       1/1     Running   0          15m
rabbitmq-0                       1/1     Running   0          15m
```

#### **Ver Logs**

```powershell
# Logs de um serviço específico
kubectl logs -n ecommerce -l app=ms-usuarios --tail=50

# Seguir logs em tempo real
kubectl logs -n ecommerce -l app=ms-usuarios -f

# Logs do API Gateway
kubectl logs -n ecommerce -l app=api-gateway --tail=100

# Logs do Eureka
kubectl logs -n ecommerce -l app=eureka-server --tail=50
```

#### **Descrever Recursos**

```powershell
# Detalhes de um pod
kubectl describe pod <nome-do-pod> -n ecommerce

# Detalhes de um deployment
kubectl describe deployment ms-usuarios -n ecommerce

# Ver eventos do namespace
kubectl get events -n ecommerce --sort-by='.lastTimestamp'
```

#### **Acessar Dashboard do Eureka**

```powershell
# Port-forward do Eureka
kubectl port-forward -n ecommerce svc/eureka-server 8761:8761

# Abrir no navegador
# http://localhost:8761
```

**Você verá todos os microservices registrados:**
- GATEWAY
- MS-DATABASE
- MS-USUARIOS
- MS-PRODUTOS
- MS-PEDIDOS
- MS-PAGAMENTOS

---

### 🌐 Acessando a Aplicação

#### **Via Port-Forward (Recomendado para K3d)**

```powershell
# Port-forward do API Gateway
kubectl port-forward -n ecommerce svc/api-gateway 8080:8080

# Manter o terminal aberto!
```

---

## 🤝 Contribuição

Contribuições são bem-vindas! Para contribuir:

1. **Fork** o projeto
2. Crie uma **branch** para sua feature (`git checkout -b feature/MinhaFeature`)
3. **Commit** suas mudanças (`git commit -m 'feat: Adiciona MinhaFeature'`)
4. **Push** para a branch (`git push origin feature/MinhaFeature`)
5. Abra um **Pull Request**

### 📝 Padrões de Commit

Usei [Conventional Commits](https://www.conventionalcommits.org/):

```
feat: Nova funcionalidade
fix: Correção de bug
docs: Documentação
style: Formatação
refactor: Refatoração
test: Testes
chore: Manutenção
```

### 🐛 Reportar Bugs

Abra uma [Issue](https://github.com/ThallysCezar/Project-Ecommerce-Microsservices/issues) com:

- Descrição clara do problema
- Passos para reproduzir
- Comportamento esperado vs atual
- Screenshots (se aplicável)
- Versão do Java, Spring Boot, etc.

---

## 👨‍💻 Autor

**Thallys Cezar**

- GitHub: [@ThallysCezar](https://github.com/ThallysCezar)
- LinkedIn: [Thallys Cezar](https://www.linkedin.com/in/thallyscezar/)

---

<div align="center">

**⭐ Se este projeto foi útil, considere dar uma estrela! ⭐**

[⬆ Voltar ao topo](#-e-commerce-microservices-platform)

</div>
