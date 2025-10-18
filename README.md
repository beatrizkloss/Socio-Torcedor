# 🖥️ Sistema de Gerenciamento para Sócio Torcedor

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Swing](https://img.shields.io/badge/Swing-blue?style=for-the-badge)
![MVC](https://img.shields.io/badge/Architecture-MVC-green?style=for-the-badge)
![DAO](https://img.shields.io/badge/Pattern-DAO-orange?style=for-the-badge)
![Observer](https://img.shields.io/badge/Pattern-Observer-purple?style=for-the-badge)

Este projeto foi desenvolvido com o objetivo e a oportunidade de aplicar na prática os conceitos de Programação Orientada a Objetos e arquitetura de software em um sistema simples e funcional. 
A aplicação foi construída em Java com a biblioteca Swing, seguindo a arquitetura MVC para garantir a organização do código. 
O processo envolveu a implementação de padrões como DAO, para a persistência de dados em arquivos, e Observer, para criar uma interface reativa e moderna.

## 📝 Sobre o Projeto

Este projeto é um sistema simples para a gestão interna de um clube de sócios-torcedores. A aplicação permite que um administrador gerencie as entidades essenciais do clube:

* **Planos:** Criação, edição, listagem e exclusão dos planos de sócio.
* **Torcedores:** Cadastro completo de novos sócios, associando-os a um plano existente.
* **Pagamentos:** Registro do histórico de pagamentos de cada sócio.

Toda a persistência de dados é realizada em arquivos locais (`.dat`), garantindo que as informações sejam mantidas entre as execuções do programa.

## 🏛️ Arquitetura e Padrões de Projeto

* **MVC (Model-View-Controller):** 
    * `Model`: Representa os dados (classes `Plano`, `Torcedor`, `Pagamento`).
    * `View`: As telas (`JFrame`) construídas com Swing, responsáveis apenas pela interface.
    * `Controller`: O cérebro que contém a lógica de negócio e faz a ponte entre a View e o Model/DAO.

* **DAO (Data Access Object):** A camada de acesso a dados abstrai a persistência. As classes DAO são as únicas que sabem como ler e escrever nos arquivos `.dat` usando a serialização de objetos Java.

* **Observer:** Para garantir uma interface reativa, foi implementado o padrão Observer. As telas (Views) se "inscrevem" em uma `ObservableList` (no Controller) e são notificadas automaticamente para atualizar as tabelas (`JTable`) sempre que um dado é alterado.

### Diagrama de Componentes

O diagrama abaixo ilustra a arquitetura do sistema e o fluxo de comunicação entre as camadas.

<img width="800" height="578" alt="Captura de tela 2025-10-18 141445" src="https://github.com/user-attachments/assets/88ff700d-5bf8-4257-b691-e7beda8c00f7" />

## ✨ Telas da Aplicação


<img src="https://github.com/user-attachments/assets/ec12c374-3dd2-4b6e-9c19-e2caefa690d1" width="240" alt="Tela Principal">
<img src="https://github.com/user-attachments/assets/a3a975ac-51fe-4c43-9da6-cfef7685331b" width="240" alt="Gerenciamento de Planos">
<br>
<img src="https://github.com/user-attachments/assets/7058754a-4600-4c43-9c17-8ed0821a3eac" width="240" alt="Gerenciamento de Torcedores">
<img src="https://github.com/user-attachments/assets/5927f983-072f-485a-af84-146ed994d30b" width="240" alt="Registro de Pagamentos">


