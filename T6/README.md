# Compilador de Receitas Culinárias

---

## Disciplina

Compiladores — DC/UFSCar  
Professor: Daniel Lucrédio

### Alunas

- Camila Oliveira de Souza — 800361  
- Beatriz Ferreira Martins — 820565  
- Yara dos Santos Rodrigues — 821774  

---

## Descrição do Projeto

Este projeto consiste no desenvolvimento de um compilador para uma linguagem específica de domínio (DSL) voltada à descrição de receitas culinárias.

A linguagem permite representar receitas de forma estruturada, contendo:

- nome da receita;
- quantidade de porções;
- lista de ingredientes;
- etapas de preparo;
- regras opcionais de substituição para restrições alimentares.

A partir de uma receita válida, o compilador será capaz de:

1. analisar e validar a estrutura da receita;
2. gerar uma visualização formatada da receita em HTML;
3. sugerir adaptações para restrições alimentares quando disponíveis.

As adaptações alimentares são definidas na própria receita por meio de regras opcionais de substituição de ingredientes.

---

## Funcionamento Geral

Fluxo esperado da compilação:

```text
Receita
 ↓
Análise Léxica
 ↓
Análise Sintática
 ↓
Análise Semântica
 ↓
Adaptação Alimentar (opcional)
 ↓
Geração de HTML
```

---

## Exemplo de Entrada

```text
RECEITA "Bolo de Chocolate"

PORCOES: 8

INGREDIENTES:

200 g de "farinha de trigo"
    SUBSTITUIR gluten POR "farinha de arroz";

300 ml de "leite"
    SUBSTITUIR lactose POR "leite vegetal";

3 un de "ovo"
    SUBSTITUIR vegano POR "linhaça";

100 g de "chocolate";

PASSOS:

MISTURAR("farinha de trigo", "leite");
ADICIONAR("ovo");
ADICIONAR("chocolate");
ASSAR(180 C, 40 min);

FIM
```

Observações:

- substituições são opcionais;
- ingredientes podem não possuir adaptações;
- uma mesma receita pode oferecer múltiplas adaptações.

---

## Dependências

- Java JDK 24  
- Maven 3.9.x ou superior  
- ANTLR 4.7.2  

---

## Compilação

No diretório onde se encontra o arquivo `pom.xml`, execute:

```bash
mvn clean package
```

Ao final será gerado um `.jar` contendo todas as dependências necessárias.

---

## Execução

O programa deve ser executado com:

1. caminho do arquivo de entrada;
2. caminho do arquivo de saída;
3. restrição alimentar (**opcional**).

Quando nenhuma restrição alimentar for informada, a receita será processada normalmente.

### Receita sem adaptação alimentar

```bash
java -jar target/recipe-compiler-1.0-SNAPSHOT-jar-with-dependencies.jar entrada.txt saida.html
```

### Receita com adaptação alimentar

```bash
java -jar target/recipe-compiler-1.0-SNAPSHOT-jar-with-dependencies.jar entrada.txt saida.html vegano
```

### Via Maven

Sem restrição:

```bash
mvn exec:java \
-Dexec.mainClass=br.ufscar.dc.compiladores.receita.Principal \
-Dexec.args="entrada.txt saida.html"
```

Com restrição:

```bash
mvn exec:java \
-Dexec.mainClass=br.ufscar.dc.compiladores.receita.Principal \
-Dexec.args="entrada.txt saida.html vegano"
```

---

## Entrada

Arquivo texto contendo uma receita escrita na linguagem definida pelo projeto.

---

## Saída

Caso a compilação seja concluída com sucesso:

- geração da receita formatada em HTML;
- aplicação das adaptações alimentares quando solicitadas.

Exemplo:

```text
Substituições aplicadas:
"ovo" → "linhaça"
```

Caso nenhuma restrição alimentar seja informada:

```text
Receita processada sem adaptações alimentares.
```

Caso exista uma restrição solicitada, mas nenhuma substituição correspondente:

```text
Receita não possui adaptações para a restrição alimentar solicitada.
```

Caso a restrição alimentar informada seja inválida:

```text
Restrição alimentar "abc" não reconhecida.
Fim da compilacao
```

Caso ocorram erros durante o processamento:

- erros léxicos;
- erros sintáticos;
- erros semânticos.

Ao final da execução será exibido:

```text
Fim da compilacao
```

---

## Escopo do Projeto

### Análise Léxica e Sintática

- definição da linguagem;
- reconhecimento da estrutura da receita;
- validação da gramática.

### Análise Semântica

- validação das regras de substituição;
- validação das restrições alimentares;
- aplicação das adaptações quando possível.

### Geração de Código

- geração de HTML para visualização da receita.