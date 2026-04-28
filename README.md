# 🌦️ Sistema de Previsão Meteorológica Distribuído com gRPC e Spring Boot

## 📌 Descrição

Este projeto implementa um sistema distribuído de previsão meteorológica utilizando **gRPC** e **Spring Boot**.

A aplicação funciona como uma API REST que recebe requisições HTTP e as encaminha para um servidor gRPC interno, responsável por processar os dados meteorológicos.

---

## 🏗️ Arquitetura do Sistema

```text
Cliente (Postman / HTTP)
        ↓
REST Controller (Spring Boot)
        ↓
gRPC Client
        ↓
gRPC Server
        ↓
Dados em memória (WeatherData)
```

---

## 🚀 Tecnologias Utilizadas

* Java 17
* Spring Boot 2.7
* gRPC
* Protocol Buffers (Protobuf)
* Maven

---

## 📁 Estrutura do Projeto

```text
src/main/java/br/ifg/urutai/weathergrpcsystem

├── controller
│   └── WeatherController.java        # API REST
│
├── service
│   └── WeatherGrpcClient.java        # Cliente gRPC
│
├── grpc
│   └── WeatherServiceImpl.java       # Servidor gRPC
│
├── data
│   └── WeatherData.java              # Dados em memória
│
└── WeatherGrpcSystemApplication.java # Classe principal
```

```text
src/main/proto/weather.proto          # Definição do gRPC
```

---

## 📄 Arquivo `.proto`

O arquivo `weather.proto` define o contrato de comunicação entre cliente e servidor gRPC.

### 🔹 Service

```proto
service WeatherService {
  rpc GetCurrentTemperature (CityRequest) returns (TemperatureResponse);
  rpc GetFiveDayForecast (CityRequest) returns (ForecastResponse);
  rpc ListCities (Empty) returns (CityListResponse);
  rpc AddCity (AddCityRequest) returns (GenericResponse);
  rpc GetStatistics (CityRequest) returns (StatisticsResponse);
}
```

### 🔹 Messages

* **CityRequest** → nome da cidade
* **TemperatureResponse** → temperatura atual
* **ForecastResponse** → lista de temperaturas (5 dias)
* **CityListResponse** → lista de cidades
* **StatisticsResponse** → média, mínima e máxima
* **AddCityRequest** → cadastro de cidade
* **GenericResponse** → mensagem de retorno

---

## ⚙️ Como o `.proto` gera código

O plugin `protobuf-maven-plugin`:

1. Lê o arquivo `.proto`
2. Gera automaticamente:

   * Classes Java (DTOs)
   * Classe `WeatherServiceGrpc`
3. Os arquivos são gerados em:

```text
target/generated-sources/protobuf
```

---

## 🔄 Fluxo da Requisição

1. O cliente envia uma requisição HTTP (Postman)
2. O `WeatherController` recebe
3. O `WeatherGrpcClient` chama o gRPC
4. O `WeatherServiceImpl` processa
5. Os dados são buscados em memória
6. A resposta retorna via gRPC → REST → cliente

---

## 🌐 Endpoints REST

### ➕ Cadastrar cidade

```http
POST /cidade
```

Body:

```json
{
  "nome": "Brasília",
  "temperatura": 30
}
```

---

### 📋 Listar cidades

```http
GET /cidades
```

---

### 🌡️ Temperatura atual

```http
GET /temperatura?cidade=Urutai
```

---

### 📅 Previsão 5 dias

```http
GET /previsao?cidade=Goiânia
```

---

### 📊 Estatísticas

```http
GET /estatisticas?cidade=São Paulo
```

---

## 🗃️ Dados Simulados

O sistema utiliza dados em memória (sem banco de dados), contendo várias cidades brasileiras como:

* Urutai
* Goiânia
* São Paulo
* Rio de Janeiro
* Curitiba
* Salvador
* Manaus

---

## ▶️ Como Executar

### 1. Clonar o repositório

```bash
git clone <URL_DO_REPOSITORIO>
cd weather-grpc-system
```

---

### 2. Compilar o projeto

```bash
./mvnw clean install
```

(Windows)

```bash
.\mvnw clean install
```

---

### 3. Executar

```bash
./mvnw spring-boot:run
```

---

## 🔌 Portas

* REST: `http://localhost:8080`
* gRPC: `localhost:9090`

---

## 🧪 Testes com Postman

Utilize os endpoints REST para testar o sistema.

Post:

<img width="1373" height="648" alt="Captura de tela 2026-04-28 092438" src="https://github.com/user-attachments/assets/0c7088d8-33e2-4c8b-add8-cda05e6e3743" />

Get Cidades:

<img width="1365" height="825" alt="image" src="https://github.com/user-attachments/assets/a39a2e3b-bb46-4358-8012-817cc31b3a5e" />

Get Temperatura:

<img width="1376" height="624" alt="image" src="https://github.com/user-attachments/assets/1d13bc3f-fdeb-4275-b3b4-60056fc77a15" />

Get Previsão:

<img width="1371" height="726" alt="image" src="https://github.com/user-attachments/assets/e02f6ed6-84de-4f64-acff-b7f3d0521559" />

Get Estatisticas:

<img width="1364" height="655" alt="image" src="https://github.com/user-attachments/assets/cadf0461-4985-46ea-b6f0-68c5b63dd413" />

---

## 💡 Observações

* O sistema não utiliza banco de dados (dados simulados)
* gRPC é usado para comunicação interna
* REST é usado para comunicação externa

---

## 📚 Conclusão

Este projeto demonstra a integração entre:

* APIs REST (Spring Boot)
* Comunicação eficiente com gRPC
* Arquitetura distribuída simplificada

---

## 👨‍💻 Autor

Cristian Martins Fernandes

Projeto acadêmico - Instituto Federal Goiano - Campus Urutaí
