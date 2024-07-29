
Modificações feitas:

MultiTCPServer.java
Criação de um conjunto clientWriters para armazenar os fluxos de saída de todos os clientes conectados.
Criação de um conjunto clientSockets para armazenar os sockets de todos os clientes conectados.
Cada cliente é tratado em uma thread separada através da classe ClientHandler.
Adição de uma thread para ler a entrada do servidor e transmitir a mensagem para todos os clientes.

SimpleTCPClient.java
Criação de duas threads: uma para enviar mensagens digitadas pelo usuário e outra para receber mensagens do servidor.
O cliente se conecta ao servidor
