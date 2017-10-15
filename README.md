# README #

O README é normalmente utilizado para explicar os passos necessários que irá manter o projeto atualizado e funcionando.

### Informações sobre o projeto ###

* Nome: Vet Mobile
* Versão: 1.0
* Repositório: https://limadeveloper@bitbucket.org/limadeveloper/android-vetmobile.git

### Como contribuir? ###

Para contribuir, é necessário clonar a branch develop, e partir dela criar sua própria branch para fazer as modificações.</br>
Cada branch deve ser a representação de um job, porque isso facilita o code review.</br>
O nome da branch deve ser todo em minúsculo.</br>

Exemplo:</br> 
Job: Trocar o nome da aplicação</br>
branch: update/troca_nome_do_app</br>

Antes de iniciar qualquer modificação no projeto, deve-se pegar os últimos commits da branch develop.</br>

Prefixos que podem ser utilizados em uma branch:</br>
- `fix`: representa a correção de algum bug</br>
- `hotfix`: representa a correção de bug urgente</br>
- `update`: atualiza alguma funcionalidade</br>
- `feature`: implementa uma nova funcionalidade</br>

Após a criação da branch e do término das modificações, é necessário fazer o commit e depois o push.</br>
Se o push foi feito corretamente, basta acessar o repositório para fazer o pull request com a sua branch e a develop.</br>
Ao finalizar o pull request, clicar no botão merge.

### Passos para configurar o Git ###

Alguns softwares que podem ser utilizados para trabalhar com o Git:
* Linha de comando;
* Sourcetree
* VSCode

Para instalar o Git, acesse o site: https://git-scm.com/downloads.</br>
Após a instalação, seguir os passos abaixo, respectivamente, para realizar a configuração corretamente.</br>

<b>Identidade</b></br>
A primeira coisa que você deve fazer ao instalar Git é configurar seu nome de usuário e endereço de e-mail. Isto é importante porque cada comprometimento (commits) usa esta informação, e ela é imutavelmente carimbada nos commits que você faz.</br>

Para configurar, abra o terminal e digite os comandos abaixo:</br>
`git config --global user.name "Fulano de Tal`</br>
`git config --global user.email fulanodetal@exemplo.br`

Para verificar se deu certo:</br>
`git config user.name`
`git config user.email`

Referência: https://git-scm.com/book/pt-br/v2/Começando-Configuração-Inicial-do-Git</br>

<b>Iniciando o git para um novo projeto</b></br>
No terminal, utilize o comando `cd` para navegar até o diretório do projeto. Após isso utilize os comandos abaixo:</br>

- `git init`: inicia o git;
- `git add .`: adiciona todos os arquivos do diretório ao git;
- `git commit -m "Descrição do commit"`: faz o commit das modificações feitas no projeto;
- `git remote add origin url_do_respositorio`: adiciona o repositório para o qual as modificações serão enviadas;
- `git push origin nome_da_branch`: envia os commits para o repositório online;

<b>Trabalhando em um projeto com repositório existente</b></br>
Primeiramente é necessário clonar o repositório. Para isso, abra o terminal e navegue até o diretório para o qual o projeto será clonado.

* `git clone url_do_repositorio`: clona o projeto na pasta selecionada;
* `git checkout -b nome_da_branch`: cria uma nova branch que representará o job a ser feito;
* `git commit -m "Descrição do commit"`: faz o commit das modificações feitas no projeto;
* `git push origin nome_da_branch`: envia os commits para o repositório online;