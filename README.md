# Dog API Automation

Automação de testes para a [Dog API](https://dog.ceo/dog-api/documentation) utilizando Java 17, Gradle, JUnit 5 e Rest Assured.

## Pré-requisitos

- Java 17 instalado
- Gradle 8+ (ou wrapper incluído)
- Internet ativa para acessar a Dog API

## Estrutura do projeto

```
src/main/java     -> helpers ou utils
src/test/java     -> testes
build.gradle.kts  -> configuração do Gradle
```

## Como executar os testes

Via Gradle:

```bash
./gradlew test        # Linux/Mac
gradlew.bat test      # Windows
```

Os relatórios de teste padrão do JUnit serão gerados em `build/reports/tests/test/index.html`.

## Boas práticas incluídas

- Testes independentes
- Uso de Rest Assured para validação de endpoints
- Preparação do ambiente via `@BeforeAll`
- Validação de status code e payload

## Próximos passos

- Adicionar testes de cenários negativos
- Gerar relatórios HTML/Allure detalhados
- Parametrizar raças para testes dinamicamente
