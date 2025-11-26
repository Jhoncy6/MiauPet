# 🐾 MiauPet

Sistema de gestão para uma clínica veterinária desenvolvido em **Java**, utilizando **MySQL** como banco de dados.

---

## 📝 Visão Geral do Projeto

O **MiauPet** é um sistema que facilita o gerenciamento das principais entidades de uma clínica veterinária:

- **Clientes**
- **Animais**
- **Veterinários**
- **Serviços**
- **Consultas**

---

# 📊 Como alterar as branchs?

O projeto possui duas versões principais, cada uma numa branch diferente:

- `master` → foco no **Cliente / Receção**
- `adm` → foco na **Gestão / Administração**

## Verificar a branch atual

`git branch` 

## Alterar de branch 

-  **master → adm** *→* `git checkout adm`
-  **adm → master** *→* `git checkout master`



# 🌿 Branch `master` (Foco no Cliente / Receção)

## Funcionalidades Principais

- **Login e Cadastro**
  - O usuario pode criar uma conta nova ou entrar com o seu CPF.

- **Gestão Própria**
  - Após logado, o cliente pode cadastrar os seus próprios animais.

- **Agendamento**
  - Permite marcar novas consultas para os animais cadastrados.
  - É possível escolher serviços disponíveis (banho, tosa, vacinação, ....).

- **Histórico**
  - O cliente pode visualizar o histórico de consultas e serviços realizados nos seus pets.

---

# 🌿 Branch `adm` (Foco na Gestão / Administração)
### Funcionalidades Principais

- **Visão Global**
  - Em vez de fazer login como um cliente específico, o administrador vê a lista de **todos os clientes** cadastrados no banco.

- **Navegação Hierárquica**
  - Fluxo em níveis:
    1. Seleciona-se um **Cliente** ou o menu de **Serviços**.
    2. 
       - Se for escolhido um **Cliente**: seleciona-se um dos seus **Animais**.
       - Se for escolhido **Serviços**: é possível **adicionar** novos serviços,**editar** ou **remover** serviços já existentes.

    3. Dentro de um **Animal**, o administrador pode executar ações específicas, como:
       - editar dados do animal;
       - remover o animal;
       - consultar e gerir as **consultas** do pet;
       - associar **serviços** às consultas, quando aplicável.



 

# Tabelas do Banco de Dados

### Cliente

| Campo      | Tipo          |
| :--------- | :------------ |
| `id`       | `int`         |
| `nome`     | `varchar(45)` |
| `telefone` | `varchar(20)` |
| `endereco` | `varchar(45)` |
| `email`    | `varchar(45)` |
| `cpf`      | `varchar(14)` |

---

### Animal

| Campo       | Tipo          |
| :---------- | :------------ |
| `id`        | `int`         |
| `nome`      | `varchar(45)` |
| `especie`   | `varchar(45)` |
| `raca`      | `varchar(45)` |
| `idCliente` | `int`         |

> `idCliente` é chave estrangeira referenciando `Cliente(id)`.

---

### Veterinario

| Campo          | Tipo          |
| :------------- | :------------ |
| `id`           | `int`         |
| `nome`         | `varchar(45)` |
| `especialidade`| `varchar(45)` |
| `crmv`         | `varchar(45)` |

---

### Consulta

| Campo         | Tipo           |
| :------------ | :------------- |
| `id`          | `int`          |
| `dia`         | `datetime`     |
| `motivo`      | `varchar(45)`  |
| `comentarios` | `varchar(300)` |
| `idAnimal`    | `int`          |
| `idVeterinario` | `int`        |

> `idAnimal` referencia `Animal(id)` e `idVeterinario` referencia `Veterinario(id)`.

---

### Servico

| Campo         | Tipo          |
| :------------ | :------------ |
| `id`          | `int`         |
| `preco`       | `decimal`     |
| `nomeServico` | `varchar(45)` |

---

### Consulta_has_Servico

| Campo       | Tipo |
| :---------- | :--- |
| `idConsulta`| `int`|
| `idServico` | `int`|

> Chave primária composta por (`idConsulta`, `idServico`).  
> `idConsulta` referencia `Consulta(id)` e `idServico` referencia `Servico(id)`.

# Desenvolvedores do Projeto


## Projeto de BD ( Banco de Dados )
 
- [João Carlos de Oliveira Mexias - matricula 475](https://www.github.com/Jhoncy6)
- [Rodrigo Guimarães Pinheiro Pascolato - matircula 388 ](https://github.com/kiwizinho91)


## Projeto de POO ( Programação orientada a objetos )

- [João Carlos de Oliveira Mexias - 475](https://www.github.com/Jhoncy6)
- [Rodrigo Guimarães Pinheiro Pascolato - 388 ](https://github.com/kiwizinho91)
- [João Guilherme Machado](https://github.com/joaodias02)
