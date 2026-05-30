**T5** (ATUALIZAR)

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
Este projeto consiste na implementação de um analisador semântico para a linguagem LA, similar ao trabalho 3, mas agora envolvendo também ponteiros, registros e funções, utilizando ANTLR.

O programa lê um arquivo de entrada contendo um programa em LA e verifica a existência de erros sintáticos e semânticos, indicando a linha e o lexema próximos ao ponto em que o erro foi detectado.

Além disso, os erros léxicos já tratados nos trabalhos anteriores continuam sendo identificados.

A saída do programa é gravada em um arquivo texto.

### **Dependências**:

- Java JDK 24
- Maven 3.9.x ou superior
- ANTLR 4.7.2

### **Compilação**:

No diretório onde está o arquivo pom.xml, execute: ```mvn clean package```. Isso irá compilar o projeto e gerar o .jar com dependências.

### **Execução**:

O programa deve ser executado com DOIS argumentos obrigatórios: _**Caminho do arquivo de entrada**_ e **_Caminho do arquivo de saída_**

Exemplo na prática: 


- **Via jar**:
```
    java -jar "c:\compilador\meu-compilador.jar" "c:\casos-de-teste\entrada.txt" "c:\temp\saida.txt"
```

- **Via maven**:
```
mvn exec:java "-Dexec.mainClass=br.ufscar.dc.compiladores.jander.Principal" -Dexec.args="entrada.txt saida.txt"
```

**Entrada:**
Arquivo texto contendo código na linguagem LA.

**Saída:**
- Arquivo texto contendo a mensagem correspondente ao primeiro erro encontrado durante a compilação.
- O programa pode identificar: erros léxicos, conforme especificado no T1; erros sintáticos, conforme especificado no T2; erros semânticos, conforme especificado no T3.
- Após a mensagem de erro, o programa imprime: ```Fim da compilacao```

