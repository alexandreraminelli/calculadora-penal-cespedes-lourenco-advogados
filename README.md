# Calculadora Penal Jurídica

Projeto extensionista da disciplina **Desenvolvimento de Aplicativos com a Linguagem Kotlin (CC3505)** em parceria com a [**Cespedes Lourenço Advogados**](https://cespedeslourencoadvogados.com.br).

O aplicativo tem como objetivo realizar o cálculo de benefícios penais jurídicos para auxiliar o cidadão comum que não possui conhecimento jurídico sobre o complexo sistema penal brasileiro, além de promover o escritório de advocacia parceiro.

## Arquitetura do Sistema

Este projeto foi desenvolvido como um aplicativo **Android nativo e standalone**.
Todo o motor de cálculo é implementado em Kotlin, utilizando a **arquitetura MVVM (Model-View-ViewModel)** para garantir uma separação clara entre a lógica de negócios e a interface do usuário.

## Funcionalidades Principais

- Coleta de dados para o cálculo da pena:
  - Pena Total
  - Data de Início do Cumprimento da Pena
  - Tempo de Detração
  - Tipo de Crime: Comum ou Hediondo/Equiparado
  - Status do Apenado: Primário ou Reincidente
- Exibição dos resultados:
  - Data Prevista para a Progressão ao Regime Semiaberto
  - Data Prevista para a Progressão ao Regime Aberto
  - Data Prevista para o Livramento Condicional

## Tecnologias Utilizadas

<div style="display: flex; flex-direction: row; gap: 8px; flex-wrap: wrap; padding-bottom: 12px;">
  <!-- Kotlin -->
  <img src="https://img.shields.io/badge/Kotlin-7F52FF?logo=kotlin&logoColor=white&style=for-the-badge" height="40" alt="kotlin logo"  />
  <!-- Android Studio -->
  <img src="https://img.shields.io/badge/Android Studio-3DDC84?logo=androidstudio&logoColor=black&style=for-the-badge" height="40" alt="androidstudio logo"  />
  <!-- Jetpack Compose -->
  <img src="https://img.shields.io/badge/Jetpack%20Compose-4285F4.svg?style=for-the-badge&logo=Jetpack-Compose&logoColor=white" height="40" alt="jetpack compose logo"  />
</div>

## Requisitos

- Android 7.0 (API 24) ou superior

## Referências

- Lei nº 7.210/1984 – Lei de Execução Penal (LEP)
- Documentação [Android Developers](https://developer.android.com)
- Material de apoio jurídico interno da empresa

## Equipe e Autoria

- **Desenvolvedor:** Alexandre Raminelli (RA: 24.01625-0)
- **Idealizador do Projeto & Consultoria Jurídica:** [Cespedes Lourenço Advogados](https://cespedeslourencoadvogados.com.br)
- **Disciplina:** Desenvolvimento de Aplicativos com a Linguagem Kotlin (CC3505)
