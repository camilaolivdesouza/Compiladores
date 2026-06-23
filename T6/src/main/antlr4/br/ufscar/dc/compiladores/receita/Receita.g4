grammar Receita;

/*
 * =====================
 * Parser Rules
 * =====================
 */

receita
    : cabecalho
      ingredientes
      passos
      FIM EOF
    ;

cabecalho
    : RECEITA STRING
      PORCOES DOIS_PONTOS NUMERO
    ;

ingredientes
    : INGREDIENTES DOIS_PONTOS
      ingrediente+
    ;

ingrediente
    : NUMERO unidade DE STRING substituicao* PONTO_VIRGULA
    ;

substituicao
    : SUBSTITUIR restricao POR STRING PONTO_VIRGULA
    ;

restricao
    : VEGANO
    | VEGETARIANO
    | LACTOSE
    | GLUTEN
    ;

passos
    : PASSOS DOIS_PONTOS
      passo+
    ;

passo
    : acao
      ABRE_PAREN
      argumentos?
      FECHA_PAREN
      PONTO_VIRGULA
    ;

acao
    : MISTURAR
    | ADICIONAR
    | ASSAR
    | BATER
    | REFRIGERAR
    | COZINHAR
    ;

argumentos
    : argumento
      (VIRGULA argumento)*
    ;

argumento
    : STRING
    | NUMERO unidade?
    ;

unidade
    : G
    | KG
    | ML
    | L
    | UN
    | C
    | MIN
    ;

/*
 * =====================
 * Lexer Rules
 * =====================
 */

RECEITA : 'RECEITA';
PORCOES : 'PORCOES';

INGREDIENTES : 'INGREDIENTES';
PASSOS : 'PASSOS';

FIM : 'FIM';

SUBSTITUIR : 'SUBSTITUIR';
POR : 'POR';

DE : 'de';

VEGANO : 'vegano';
VEGETARIANO : 'vegetariano';
LACTOSE : 'lactose';
GLUTEN : 'gluten';

MISTURAR : 'MISTURAR';
ADICIONAR : 'ADICIONAR';
ASSAR : 'ASSAR';
BATER : 'BATER';
REFRIGERAR : 'REFRIGERAR';
COZINHAR : 'COZINHAR';

G : 'g';
KG : 'kg';

ML : 'ml';
L : 'l';

UN : 'un';

C : 'C';
MIN : 'min';

NUMERO
    : [0-9]+
    ;

STRING
    : '"' (~["\r\n])* '"'
    ;

DOIS_PONTOS
    : ':'
    ;

VIRGULA
    : ','
    ;

PONTO_VIRGULA
    : ';'
    ;

ABRE_PAREN
    : '('
    ;

FECHA_PAREN
    : ')'
    ;

WS
    : [ \t\r\n]+
      -> skip
    ;

ERRO_CADEIA
    : '"' (~["\r\n])* EOF
    ;

CARACTERE_INVALIDO
    : .
    ;