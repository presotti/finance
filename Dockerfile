# Estágio de Build
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

# Instala utilitários necessários
RUN apt-get update && apt-get install -y dos2unix

# Copia os arquivos de estrutura do Gradle
COPY gradlew .
COPY gradle gradle
COPY build.gradle.kts .
COPY settings.gradle.kts .

# Resolve problemas de compatibilidade de caracteres e permissões
RUN dos2unix gradlew && chmod +x gradlew

# Copia o código-fonte
COPY src src

# No Gradle 9+, o uso de --no-daemon é altamente recomendado em ambientes CI/Docker
# O parâmetro --configuration-cache pode acelerar builds futuros se você configurar volumes
RUN ./gradlew bootJar -x test --no-daemon

# Estágio Final
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copia o JAR gerado (Note que o Gradle 9 pode mudar o nome para incluir a versão)
COPY --from=build /app/build/libs/*.jar app.jar

# Configuração regional (Contagem/MG)
ENV TZ=America/Sao_Paulo

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]