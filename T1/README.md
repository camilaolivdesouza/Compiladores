# **T1**

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
Este projeto consiste na implementação de um analisador léxico para a linguagem LA (Linguagem Algorítmica).

O analisador lê um arquivo de entrada contendo um programa na linguagem LA e gera como saída uma lista de tokens identificados, conforme especificado pelo trabalho.

Em caso de erro léxico, o analisador interrompe a execução e reporta o erro correspondente.

### **Dependências**:

- Java JDK 24
- Maven 3.9.x ou superior
- ANTLR 4.7.2

### **Compilação**:

- No diretório onde está o arquivo pom.xml, execute: ```mvn clean package```. Isso irá compilar o projeto e gerar o .jar com dependências.

### **Execução**:

- O programa deve ser executado com DOIS argumentos obrigatórios: _**Caminho do arquivo de entrada**_ e **_Caminho do arquivo de saída_**
- Exemplo na prática: 


- **Via jar**:
```
    java -jar "c:\compilador\meu-compilador.jar" "c:\casos-de-teste\arquivo1.txt" "c:\temp\saida.txt"
```

- **Via maven**:
```
mvn exec:java "-Dexec.mainClass=br.ufscar.dc.compiladores.jander.Principal" -Dexec.args="entrada.txt saida.txt"
```

**Entrada:**
- Arquivo texto contendo código na linguagem LA.

**Saída:**
- Arquivo texto contendo a lista de tokens no formato:

```
<'algoritmo','algoritmo'>

<'declare','declare'>

<'x',IDENT>

...
```

