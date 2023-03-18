
![Logo](link Do logo)


# VET CONNECT

O sistema tem como objetivo o gerenciamento de serviços prestados por uma rede de clínicas veterinárias, visando apenas animais domésticos. O sistema realizará o  gerenciamento de serviços. Dentre os serviços estão: consultas, castrações, banhos, tosas e vacinação. Além do completo gerenciamento das informações dos animais, clientes, funcionários, veterinários e clínicas veterinárias da rede.

## Autores

- [@jovemm0nge](https://www.github.com/jovemm0nge)
- [@Hayato](https://www.github.com/jovemm0nge)
- [@LF](https://www.github.com/jovemm0nge)
- [@YagoSales](https://www.github.com/jovemm0nge)

## Aprendizados

O que você aprendeu construindo esse projeto? Quais desafios você enfrentou e como você superou-os?


## Apêndice

Coloque qualquer informação adicional aqui


## Instalação

Instale my-project com npm

```bash
  npm install my-project
  cd my-project
```
    
## Rodando localmente

Clone o projeto

```bash
  git clone https://link-para-o-projeto
```

Entre no diretório do projeto

```bash
  cd my-project
```

Instale as dependências

```bash
  npm install
```

Inicie o servidor

```bash
  npm run start
```


## Screenshots

![App Screenshot](https://via.placeholder.com/468x300?text=App+Screenshot+Here)


## Variáveis de Ambiente

Para rodar esse projeto, você vai precisar adicionar as seguintes variáveis de ambiente no seu .env

`API_KEY`

`ANOTHER_API_KEY`


## Demonstração

Insira um gif ou um link de alguma demonstração

## Documentação de cores

| Cor               | Hexadecimal                                                |
| ----------------- | ---------------------------------------------------------------- |
| Cor exemplo       | ![#0a192f](https://via.placeholder.com/10/0a192f?text=+) #0a192f |
| Cor exemplo       | ![#f8f8f8](https://via.placeholder.com/10/f8f8f8?text=+) #f8f8f8 |
| Cor exemplo       | ![#00b48a](https://via.placeholder.com/10/00b48a?text=+) #00b48a |
| Cor exemplo       | ![#00d1a0](https://via.placeholder.com/10/00b48a?text=+) #00d1a0 |


## Documentação da API

#### Retorna todos os itens

```http
  GET /api/items
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `api_key` | `string` | **Obrigatório**. A chave da sua API |

#### Retorna um item

```http
  GET /api/items/${id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `string` | **Obrigatório**. O ID do item que você quer |

#### add(num1, num2)

Recebe dois números e retorna a sua soma.

