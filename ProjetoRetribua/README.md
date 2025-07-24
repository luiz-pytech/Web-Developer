<div align="center">
  <img src="https://github.com/luiz-pytech/Web-Developer/blob/main/ProjetoRetribua/public/logobranca.png" alt="Logo Retribua" width="450"/>
</div>

# Projeto Retribua

![Java](https://img.shields.io/badge/Java-11+-orange)
![Spring](https://img.shields.io/badge/Spring-Boot-green)
![Status](https://img.shields.io/badge/status-concluído-green)
![Licença](https://img.shields.io/badge/licença-MIT-yellow)

Plataforma web full-stack desenvolvida em **Java** e **Spring Framework** com o objetivo de conectar doadores a comunidades, facilitando a solidariedade e o ato de "retribuir". O sistema gerencia usuários, doações e permite diferentes níveis de acesso para usuários comuns e administradores.

---

## 📖 Tabela de Conteúdos
- [Visão Geral do Projeto](#-visão-geral-do-projeto)
- [✨ Funcionalidades](#-funcionalidades)
- [🛠️ Tecnologias Utilizadas](#️-tecnologias-utilizadas)
- [🚀 Pré-requisitos e Instalação](#-pré-requisitos-e-instalação)
- [✍️ Autores](#️-autores)
- [📄 Licença](#-licença)

---

## 🌟 Visão Geral do Projeto

O **Retribua** foi concebido como uma solução tecnológica para um desafio social: como organizar e incentivar a ajuda mútua em comunidades de forma transparente e eficiente. A plataforma permite que usuários se cadastrem, realizem doações e acompanhem seu impacto através de um sistema de ranking, enquanto administradores gerenciam o fluxo de informações e garantem a integridade do sistema.

A arquitetura em um backend robusto com Spring Boot, que gerencia toda a lógica de negócio, autenticação de usuários e persistência de dados, servindo uma interface dinâmica para o usuário final.

---

## ✨ Funcionalidades

O sistema possui dois níveis de acesso com funcionalidades distintas:

<details>
<summary><b>👤 Para Todos os Usuários</b></summary>
<br>

| Login e Cadastro | Página Inicial |
| :---: | :---: |
| _Sistema seguro de autenticação para acesso à plataforma._ | _Dashboard principal com acesso rápido a todas as funcionalidades._ |
| ![Tela de Login](https://github.com/luiz-pytech/Web-Developer/blob/main/ProjetoRetribua/public/post13.png) | ![Home](https://github.com/luiz-pytech/Web-Developer/blob/main/ProjetoRetribua/public/post1.png) |

| Perfil do Usuário | Sobre o Projeto |
| :---: | :---: |
| _Área para gerenciar informações pessoais, foto e ver o ranking de doações._ | _Seção informativa que detalha a missão e o impacto do projeto._ |
| ![Perfil do Usuário](https://github.com/luiz-pytech/Web-Developer/blob/main/ProjetoRetribua/public/post9.png) | ![Sobre](https://github.com/luiz-pytech/Web-Developer/blob/main/ProjetoRetribua/public/post4.png) |

</details>

<details>
<summary><b>🔒 Para Administradores</b></summary>
<br>

| Gerenciamento de Usuários | Gerenciamento de Doações |
| :---: | :---: |
| _Acesso a uma lista completa de usuários com opções de CRUD (Criar, Ler, Atualizar, Deletar)._ | _Visualização de todas as doações realizadas na plataforma._ |
| ![Gerenciar Usuários](https://github.com/luiz-pytech/Web-Developer/blob/main/ProjetoRetribua/public/post10.png) | ![Gerenciar Doações](https://github.com/luiz-pytech/Web-Developer/blob/main/ProjetoRetribua/public/post11.png) |

</details>

---

## 🛠️ Tecnologias Utilizadas

| Categoria | Tecnologia |
| :--- | :--- |
| **Backend** | Java 11, Spring Boot, Spring MVC, Spring Data JPA, Spring Security |
| **Frontend** | HTML5, CSS3, JavaScript, Thymeleaf |
| **Banco de Dados** | MySQL |
| **Build Tool** | Maven |
| **Ambiente** | Spring Tool Suite (STS), IntelliJ IDEA, Eclipse |

---

## 🚀 Pré-requisitos e Instalação

Para executar este projeto localmente, você precisará ter as seguintes ferramentas instaladas em sua máquina:
* [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/downloads/) - Versão 11 ou superior.
* [Apache Maven](https://maven.apache.org/download.cgi) - Gerenciador de dependências.
* [MySQL Server](https://dev.mysql.com/downloads/mysql/) - Banco de dados.
* Um IDE de sua preferência (ex: [Spring Tool Suite](https://spring.io/tools), [IntelliJ IDEA](https://www.jetbrains.com/idea/)).

Siga os passos abaixo para configurar e rodar o projeto:

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/luiz-pytech/ProjetoRetribua.git](https://github.com/luiz-pytech/ProjetoRetribua.git)
    cd ProjetoRetribua
    ```

2.  **Configure o Banco de Dados:**
    * Crie um novo banco de dados no seu MySQL.
      ```sql
      CREATE DATABASE retribua_db;
      ```
    * Abra o arquivo `src/main/resources/application.properties`.
    * **Edite** as seguintes propriedades com as suas credenciais do MySQL:
      ```properties
      spring.datasource.url=jdbc:mysql://localhost:3306/retribua_db
      spring.datasource.username=SEU_USUARIO_MYSQL
      spring.datasource.password=SUA_SENHA_MYSQL
      ```

3.  **Execute o projeto:**
    * Pelo terminal, na raiz do projeto, execute o comando Maven para iniciar a aplicação Spring Boot:
      ```bash
      mvn spring-boot:run
      ```
    * Alternativamente, você pode importar o projeto no seu IDE e executá-lo como uma "Spring Boot App".

4.  **Acesse a aplicação:**
    * Abra seu navegador e acesse: `http://localhost:8080`

---

## ✍️ Autores

-   **Luiz Felipe de Souza Silva** - [GitHub](https://github.com/luiz-pytech)
-   **Ariane Selli Melo de Souza** - [GitHub](https://github.com/Arianeselli)
---

## 📄 Licença

Este projeto é distribuído sob a licença MIT.
