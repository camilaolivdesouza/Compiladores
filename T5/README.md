**T5** 

---

## **Disciplina**
Compiladores — DC/UFSCar
Professor: Daniel Lucrédio

### Alunas:
- Camila Oliveira de Souza 800361
- Beatriz Ferreira Martins 820565
- Yara dos Santos Rodrigues 821774

---

## **Descrição do Projeto**
Este projeto consiste na implementação do estágio final do compilador para a linguagem LA: o **gerador de código**, utilizando ANTLR.

O programa unifica todas as fases anteriores da compilação. Ele lê um arquivo de entrada contendo um programa em LA e realiza as análises léxica, sintática e semântica.
* Se houver qualquer erro nessas etapas, o programa reporta a lista de erros e encerra a execução.
* Caso o código fonte esteja correto e livre de erros, o compilador percorre a Árvore Sintática Abstrata (AST) e traduz as instruções, gerando um código equivalente em **linguagem C**.

A saída gerada pelo programa (seja a lista de erros ou o código C final) é gravada diretamente no arquivo texto de saída especificado.

### **Dependências**:

- Java JDK 24
- Maven 3.9.x ou superior
- ANTLR 4.7.2
- GCC

### **Compilação**:

No diretório onde está o arquivo pom.xml, execute: ```mvn clean package```. Isso irá compilar o projeto e gerar o .jar com dependências.

### Execução

A utilização prática do compilador ocorre em duas fases principais: a tradução do código LA para C, e a compilação do arquivo C resultante para um programa executável.

**Passo 1: Tradução de LA para C (Gerador de Código)**

O programa deve ser executado via linha de comando passando **DOIS argumentos obrigatórios**:
1. **Caminho do arquivo de entrada:** O arquivo de texto contendo o código fonte escrito na linguagem LA.
2. **Caminho do arquivo de saída:** O arquivo de destino onde o código C será gerado.

Você pode executar o tradutor de duas maneiras:

- **Opção A: Via JAR**

Após rodar o comando de compilação do Maven, um arquivo `.jar` executável estará disponível na pasta `target`.
```
java -jar target/jander-1.0-SNAPSHOT-jar-with-dependencies.jar caminho/para/entrada.alg caminho/para/saida.c
```
- **Opção B: Via Maven**

Você também pode rodar diretamente pelo Maven na raiz do projeto:
```
mvn exec:java "-Dexec.mainClass=br.ufscar.dc.compiladores.jander.Principal" -Dexec.args="caminho/para/entrada.alg caminho/para/saida.c"
```

**Passo 2: Compilação e Execução do Código C**

Caso o código LA seja válido, o arquivo de saída gerado (ex: saida.c) conterá um código C válido e estruturado. Para transformá-lo em um programa nativo, você precisará de um compilador C, como o GCC.

1. Compilando o arquivo gerado (via GCC):
   Abra o terminal na pasta onde o arquivo .c foi gerado e execute:
```
gcc saida.c -o meu_programa
```
Isso irá ler o código C gerado e criar um arquivo executável chamado meu_programa.

2. Executando o programa final:
   Agora basta rodar o executável gerado para interagir com o seu algoritmo (ler variáveis, ver impressões, etc.):

- No Windows:
```
meu_programa.exe
```

- No Linux / macOS:
```
./meu_programa
```

**Entrada e Saída**

Entrada: Arquivo de texto contendo um algoritmo escrito na linguagem LA.

Saída:
- Em caso de erros: Um arquivo texto contendo as mensagens correspondentes aos erros encontrados durante as fases de compilação (léxico, sintático ou semântico). O programa pode identificar: erros léxicos (conforme T1), erros sintáticos (conforme T2) e erros semânticos (conforme T3 e T4). Após as mensagens, o programa imprime a linha: Fim da compilacao.
- Em caso de sucesso: Um arquivo texto contendo o código equivalente traduzido para a linguagem C, pronto para ser compilado via GCC.
