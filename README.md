# 🗓️ wise-schedule-backend

### :star2: **Visão Geral**

O **Serviço de Agendamento** lida com os dados relacionados ao **agendamento de consultas médicas**. Ele inclui *
*pacientes**, **médicos**, **enfermeiros** e as **consultas agendadas**, além de gerenciar o **status** dessas
consultas (agendadas, realizadas ou canceladas). Este serviço permite **agendar novas consultas**, **atualizar o status
** e **atribuir médicos e enfermeiros** às consultas dos pacientes.

Ele interage com o **Serviço de Autenticação** para validar as credenciais dos usuários e com o **Serviço de Histórico**
para mover consultas concluídas ou canceladas para o histórico médico do paciente, assim temos controle total sobre os
dados e divisão de domínios por responsabilidade dos serviços.

---

### **Esquema de Banco de Dados 🗃️**

O esquema de banco de dados para o **Serviço de Agendamento** contém várias tabelas essenciais, como mostrado abaixo:

![Scheduling Service Database Schema](https://github.com/user-attachments/assets/7cea9201-9c4b-4d63-89cd-42ea414ee8f2)

---

### **Principais Funcionalidades 🚀**

- **Gerenciamento de Consultas**:
    - Agendar novas consultas, associando ao médico e ao enfermeiro responsável pela especialidade.
    - Atualizar o status das consultas (ex: **agendada**, **realizada**, **cancelada**).

- **Gerenciamento de Pacientes**:
    - Armazenar informações do **paciente**, como dados de contato e contatos de emergência.

- **Gerenciamento de Médicos e Enfermeiros**:
    - Associar consultas a **médicos** e **enfermeiros** específicos.
  
