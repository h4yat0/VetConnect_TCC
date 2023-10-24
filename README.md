# VET CONNECT

![Banner de Promoção](https://github.com/h4yat0/VetConnect_TCC/blob/main/web/src/assets/imgs/BannerPromo.png?raw=true)

O sistema tem como objetivo o gerenciamento de serviços prestados por uma rede de clínicas veterinárias, visando apenas animais domésticos. O sistema realizará o  gerenciamento de serviços. Dentre os serviços estão: consultas, castrações, banhos, tosas e vacinação. Além do completo gerenciamento das informações dos animais, clientes, funcionários, veterinários e clínicas veterinárias da rede.

## Autores

- [@H4yat0](https://www.github.com/h4yat0)
- [@LFMO1](https://www.github.com/lfmo1)
- [@jovemm0nge](https://www.github.com/jovemm0nge)
- [@YagoSales](https://www.github.com/yagodsales)

## Tecnologias utilizadas

[![My Skills](https://skillicons.dev/icons?i=react,redux,tailwind,vite,java,spring,maven,mysql,docker)](https://skillicons.dev)

## Aprendizados

O que você aprendeu construindo esse projeto? Quais desafios você enfrentou e como você superou-os?

## Apêndice

Coloque qualquer informação adicional aqui

## Executando localmente

Clone o projeto

```bash
git clone https://github.com/h4yat0/VetConnect_TCC.git
```

Entre no diretório do projeto

```bash
cd VetConnect_TCC
```

Baixe e instale a versão LTS do node

```https
https://nodejs.org/pt-br
```

Baixe e instale a versão 20 do java JDK

```https
https://www.oracle.com/java/technologies/javase/jdk20-archive-downloads.html
```

Baixe o Apache Maven

```https
https://maven.apache.org/download.cgi
```

Extraia para uma pasta de sua escolha em seguida crie uma variável de ambiente em seu SO de acordo com o exemplo a seguir (Não se esqueça de trocar o valor da variavel para o caminho para qual você extraiu a pasta anteriormente).

Nome da variável: ```M2_HOME```;
Valor da variável: ```C:/apache-maven-3.9.5```

Agora adicione um novo valor a variável PATH com de acordo com o exemplo a seguir

Novo valor: ```C:\apache-maven-3.9.5\bin```

Execute o arquivo a seguir caso seja a primeira vez que esta executando o projeto.

```text
install and start.bat
```

Você também pode, opcionamente executar os arquivos a seguir separadamente para obter o mesmo resutlado

```text
install.bat 
```

e

```text
start.bat 
```

## Screenshots

![App Screenshot](https://via.placeholder.com/468x300?text=App+Screenshot+Here)

## Variáveis de Ambiente

Para executar esse projeto, você vai precisar adicionar as seguintes variáveis de ambiente no seu .env

`API_KEY`

`ANOTHER_API_KEY`

## Demonstração

Insira um gif ou um link de alguma demonstração

## Documentação de cores

| Cor         | Hexadecimal                                                      |
| ----------- | ---------------------------------------------------------------- |
| Cor exemplo | ![#0a192f](https://via.placeholder.com/10/0a192f?text=+) #0a192f |
| Cor exemplo | ![#f8f8f8](https://via.placeholder.com/10/f8f8f8?text=+) #f8f8f8 |
| Cor exemplo | ![#00b48a](https://via.placeholder.com/10/00b48a?text=+) #00b48a |
| Cor exemplo | ![#00d1a0](https://via.placeholder.com/10/00b48a?text=+) #00d1a0 |

## Documentação da API

### Retorna todos os itens

```http
  GET /api/items
```

| Parâmetro | Tipo     | Descrição                           |
| :-------- | :------- | :---------------------------------- |
| `api_key` | `string` | **Obrigatório**. A chave da sua API |

### Retorna um item

```http
  GET /api/items/${id}
```

| Parâmetro | Tipo     | Descrição                                   |
| :-------- | :------- | :------------------------------------------ |
| `id`      | `string` | **Obrigatório**. O ID do item que você quer |

### add(num1, num2)

Recebe dois números e retorna a sua soma.
