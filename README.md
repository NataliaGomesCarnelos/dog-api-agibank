# Dog API Test Automation

Projeto de automação de testes para validar a integração com a **Dog API**.

A aplicação permite consultar informações sobre raças de cães e imagens associadas.

Este projeto implementa testes automatizados para garantir que os endpoints da API respondam corretamente e retornem dados no formato esperado.

---

# Tecnologias utilizadas

* Java 17
* JUnit 5
* RestAssured
* Gradle
* GitHub Actions (CI)

---

# Estrutura do projeto

```
src
 └── test
      ├── java
      │    └── com.agibank
      │         ├── client
      │         │    └── DogApiClient.java
      │         ├── config
      │         │    └── BaseTest.java
      │         └── tests
      │              ├── RandomImageTest.java
      │              ├── BreedsTest.java
      │              └── BreedImagesTest.java
      │
      └── resources
           └── schemas
                └── random-image-schema.json
```

---

# Estratégia de Testes

Os testes automatizados validam:

* Status Code das respostas
* Tempo de resposta da API
* Estrutura do JSON retornado (JSON Schema Validation)
* Conteúdo do payload
* Cenários negativos
* Integridade das imagens retornadas

---

# Endpoints testados

### GET /breeds/list/all

Valida:

* retorno de todas as raças
* estrutura do JSON
* presença de algumas raças esperadas

---

### GET /breeds/image/random

Valida:

* retorno de imagem aleatória
* formato da URL
* schema da resposta

---

### GET /breed/{breed}/images

Valida:

* retorno de lista de imagens da raça
* integridade das URLs
* verificação de acessibilidade das imagens

---

# Executar testes localmente

Clone o repositório:

```
git clone https://github.com/seuusuario/dog-api-agibank.git
```

Execute:

```
./gradlew test
```

---

# Relatório de testes

Após execução local:

```
build/reports/tests/test/index.html
```

---

# CI Pipeline

O projeto possui pipeline configurado no **GitHub Actions**, que executa automaticamente os testes a cada push.

Pipeline:

```
.github/workflows/api-tests.yml
```

---

# Autor

Natália Gomes Carnelós
