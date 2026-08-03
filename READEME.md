🏢 PortariaWeb
O PortariaWeb é uma aplicação web desenvolvida em Java com Spring Boot para gerenciamento e controle de fluxo de portarias residenciais ou empresariais.

A aplicação permite registrar a entrada/saída de prestadores de serviço e o recebimento/entrega de encomendas de forma simples e centralizada.

 Funcionalidades
👷 Gestão de Prestadores de Serviço
Cadastro de Entrada: Registro de novos prestadores no sistema.

Listagem Ativa: Exibição apenas dos prestadores que ainda estão no local (dataSaida pendente).

Registro de Saída: Marcação do horário de saída do prestador com persistência do histórico.

📦 Gestão de Encomendas
Cadastro de Recebimento: Registro de novas encomendas recebidas pela portaria.

Listagem Pendente: Exibição apenas das encomendas pendentes de retirada (dataEntregaMorador pendente).

Registro de Entrega: Confirmação da entrega da encomenda ao morador/destinatário final.

🛠️ Tecnologias Utilizadas
Linguagem: Java (17+)

Framework Backend: Spring Boot (Spring MVC, Spring Data JPA)

Template Engine: Thymeleaf (ou HTML/Bootstrap)

Banco de Dados: H2 Database / MySQL / PostgreSQL

Gerenciador de Dependências: Maven / Gradle
