## Introdução
Este README apresenta uma visão geral da arquitetura utilizada no desenvolvimento do aplicativo ImgurCats.
O intuito é capturar e transmitir as decisões significativas do ponto de vista da arquitetura que foram tomadas em relação ao app.

## Propósito
Este documento foi criado para dar uma visão de alto nível da solução técnica seguida, enfatizando os componentes e frameworks que foram utilizados e desenvolvidos, além das interfaces e integrações entre os mesmos.

## Público Alvo
Esse documento destina-se a equipe de avaliação.

## Funcionalidades
1. **Listar imagens de gatos vindas da API Imgur :** Os usuários podem visualizar uma lista completa de imagens de gatos. Essa lista contém apenas imagens.

2. **Cache de Dados para Utilização Offline:** O aplicativo utiliza um mecanismo de cache para armazenar as imagens dos animais. Isso permite que os usuários acessem as informações mesmo quando estão sem conexão com a internet. O cache garante uma experiência mais contínua e rápida, mesmo em condições de conectividade intermitente.

## Arquitetura
Essa arquitetura é baseada na Clean Architecture proposta por Robert C. Martin no livro “Arquitetura Limpa: O Guia do Artesão para Estrutura e Design de Software”, mais o desing patter MVVM proposto pela google no seu [Guia de Arquitetura do App](https://developer.android.com/jetpack/guide?hl=pt-br).

- Camadas da Clean Architecture + MVVM

   1. Presentation
      - A camada de apresentação é responsavel por lidar com a interface do usuário e apresentar os dados ao usuário final. Essa camada é dividida em duas partes principais View e ViewModel.
   2. View
      - A camada View é a interface do usuário, que exibe informações e interações com o usuário. Ela é responsavel por receber eventros e repassá-los para a ViewModel correspondente, bem como exibir os dados fornecidos pela ViewModel. A view não deve conter lógica de negócio, apenas manipulação da interface do usuário. Ela pode ser implementeada utilizando tecnologias especificas, como um Fragment ou uma Activity no Android.
   3. ViewModel
      - A camada ViewModel é responsável por fornecer dados e comportamentos para a View. Ela atua como um intermediário entre a View e a camada de domínio. A ViewModel recebe os eventos da View, executa a lógica de negócio necessária e atualiza os dados que serão exibidos na View. Ela também pode fornecer comandos que são acionados pelas View para executar ações especificas. Na ViewModel não deve conter lógica de apresentação, como formatação de dados para exibição. Deve ser implementada como uma classe separada, geralmente usando ligação de dados utilizando o desing pattern LiveData para atualização dos estados presentes na View do usuário. Nesta camada iremos realizar a comunicação com os casos de uso presentes na camada de Domain.
   4. Domain
      - A camada de domínio contém a lógica de negócio principal do aplicativo. Ela encapsula as regras e as operações que definem o comportamento central da aplicação. A camada de domínio é independente de qualquer tecnologia específica e é composta por entidades, casos de uso e interfaces.    
   5. Data
      - Camada responsável por acomodar os repositórios da aplicação, nele iremos solicitar as informações necessárias da API através de métodos e captar as informações vinda da API através de um response. Nela também deve ficar as fontes de dados.

## Boas Práticas
S.O.L.I.D

## Visão do aplicativo

<p align="center">
<img width="200" height="400" alt="image" src="https://github.com/user-attachments/assets/b1388513-b7db-4c0d-a8f2-58d669f1cf4d">
</p>


## Roadmap para futuras implementações

1. Adicionar o comando de "Swipe to refresh" na tela de listagem de imagens.

2. Adicionar "Searchbar" para procura de animais especificos e adicionar opção para ordenação por nome.

## Raciocinio para desenvolvimento do projeto

O aplicativo foi estruturado conforme a Clean Arc. No módulo App ficam todas as classes e padrões de projeto relacionado a camada de domain, data e presentation; como por exemplo: fonte de dados, além do repositório, models, injeção de dependência, implementação do repositório, objetos de response, view model e screen.

## Referências

- [1] MARTIN, Robert C. The Clean Architecture Artigo web. Ano: 2012. Disponível em: https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html
- [2] Paging library overview. Disponível em: https://developer.android.com/topic/libraries/architecture/paging/v3-overview
