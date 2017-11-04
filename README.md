# README #

O README é normalmente utilizado para explicar os passos necessários que irá manter o projeto atualizado e funcionando.  

### Informações sobre o projeto ###

* Nome: Vet Mobile
* Versão: 1.0
* Repositório: https://limadeveloper@bitbucket.org/limadeveloper/android-vetmobile.git

**Dependencias Utilizadas**
https://developer.android.com/studio/build/dependencies.html

* Material DateTime Picker `https://github.com/wdullaer/MaterialDateTimePicker`
* Realm `https://realm.io/docs/java/latest/#installation`
* CompactCalendarView `https://github.com/SundeepK/CompactCalendarView`

### Como contribuir? ###

Para contribuir, é necessário clonar a branch develop, e partir dela criar sua própria branch para fazer as modificações.  
Cada branch deve ser a representação de um job, porque isso facilita o code review.  
O nome da branch deve ser todo em minúsculo.  

Exemplo:  
Job: Trocar o nome da aplicação  
branch: update/troca_nome_do_app  

`Antes de iniciar qualquer modificação no projeto, deve-se pegar os últimos commits da branch develop.`  

Prefixos que podem ser utilizados em uma branch:  

* `fix`: representa a correção de algum bug  
* `hotfix`: representa a correção de bug urgente  
* `update`: atualiza alguma funcionalidade  
* `feature`: implementa uma nova funcionalidade  

Após a criação da branch e do término das modificações, é necessário fazer o commit e depois o push.  
Se o push foi feito corretamente, basta acessar o repositório para fazer o pull request com a sua branch e a develop.  

### Passos para configurar o Git ###

Alguns softwares que podem ser utilizados para trabalhar com o Git:

* Linha de comando
* Sourcetree
* VSCode

Para instalar o Git, acesse o site: https://git-scm.com/downloads.  
Após a instalação, seguir os passos abaixo, respectivamente, para realizar a configuração corretamente.  

**Identidade**  
A primeira coisa que você deve fazer ao instalar Git é configurar seu nome de usuário e endereço de e-mail. Isto é importante porque cada comprometimento (commits) usa esta informação, e ela é imutavelmente carimbada nos commits que você faz.  

Para configurar, abra o terminal e digite os comandos abaixo:  
`git config --global user.name "Fulano de Tal`  
`git config --global user.email fulanodetal@exemplo.br`

Para verificar se deu certo:  
`git config user.name`
`git config user.email`

Referência: https://git-scm.com/book/pt-br/v2/Começando-Configuração-Inicial-do-Git  

**Iniciando o git para um novo projeto**  
No terminal, utilize o comando `cd` para navegar até o diretório do projeto. Após isso utilize os comandos abaixo:  

* `git init`: inicia o git;
* `git add .`: adiciona todos os arquivos do diretório ao git;
* `git commit -m "Descrição do commit"`: faz o commit das modificações feitas no projeto;
* `git remote add origin url_do_respositorio`: adiciona o repositório para o qual as modificações serão enviadas;
* `git push origin nome_da_branch`: envia os commits para o repositório online;

**Trabalhando em um projeto com repositório existente**  
Primeiramente é necessário clonar o repositório. Para isso, abra o terminal e navegue até o diretório para o qual o projeto será clonado.

* `git clone url_do_repositorio`: clona o projeto na pasta selecionada;
* `git checkout -b nome_da_branch`: cria uma nova branch que representará o job a ser feito;
* `git commit -m "Descrição do commit"`: faz o commit das modificações feitas no projeto;
* `git push origin nome_da_branch`: envia os commits para o repositório online;  

`As descrições dos commits devem estar no infinitivo, ex.: altera, adiciona, ajusta...`

**Links**

* https://www.youtube.com/watch?v=UMhskLXJuq4&t=37s