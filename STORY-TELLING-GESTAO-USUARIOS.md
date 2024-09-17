Domain Storytelling - Sistema de Gestão de Usuários e Perfis

1) Cenário Inicial:

O contexto do problema negocial consiste em criar um sistema para gerenciar o acesso e as permissões dos colaboradores, centralizando as informações de usuários em um só local. O sistema deverá permitir que um administrador crie, edite e atribua perfis a diferentes usuários, garantindo que cada pessoa tenha acesso apenas às informações e funcionalidades que correspondem ao seu papel na organização.

2) Personagens principais:

2.1) Administrador: Responsável pela criação, edição e exclusão de usuários, além de atribuir ou modificar seus perfis de acesso.

2.2) Usuário Comum: Um colaborador que utiliza o sistema para visualizar e gerenciar seus próprios dados pessoais (exceto a atribuição de perfil).


3) Objetivos principais:

3.1) Administrador: Gerenciar eficientemente os usuários e seus perfis, garantindo que os dados estejam organizados e que cada pessoa tenha acesso às informações certas.

3.2) Usuário comum: Acessar e editar suas próprias informações de forma fácil e segura, sem o risco de modificar permissões críticas (como o perfil atribuído).


4) Estrutura de Relacionamento:

1. Perfil de Usuário – Cada usuário é associado a um perfil. Um perfil pode estar vinculado a vários usuários.
2. Administrador – Tem o poder de criar usuários, atribuir perfis e modificar perfis existentes.
3. Usuário Comum – Pode visualizar e alterar suas informações pessoais, mas não tem permissão para modificar seu perfil.


Fluxo de Histórias:

História 1: Criação de Usuário e Atribuição de Perfil

1. O administrador acessa o sistema e navega até a área de gerenciamento de usuários.
2. Ele seleciona a opção para adicionar um novo usuário.
3. O administrador insere informações como nome, e-mail e senha.
4. Em seguida, é escolhido o perfil adequado para o usuário, como "Usuário Comum" ou "Administrador".
5. O usuário é então adicionado ao sistema, já vinculado ao perfil definido.


História 2: Modificação de Perfil de Usuário Existente

1. O administrador acessa a lista de usuários e seleciona um usuário para editar.
2. Ele altera o perfil do usuário, ajustando as permissões conforme necessário.
3. Após salvar as alterações, o perfil do usuário é atualizado.


História 3: Usuário Comum Edita Suas Informações

1. O usuário comum faz login no sistema.
2. Ele acessa a seção "minhas informações" onde visualiza seus dados pessoais.
3. O usuário pode atualizar dados como endereço ou telefone.
4. No entanto, o campo de perfil permanece bloqueado, garantindo que ele não possa fazer alterações nessa área.
5. Após realizar as mudanças permitidas, o usuário salva as alterações.

Regras de Negócio:

1. Cada perfil pode ser associado a vários usuários, mas um usuário só pode ter um único perfil.
2. Apenas o administrador tem permissões para criar e modificar perfis de usuários.
3. Usuários comuns não têm acesso para modificar seus próprios perfis, mas podem manter suas informações pessoais atualizadas.