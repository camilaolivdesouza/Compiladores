lexer grammar Jander;

// =====================
// Palavras-chave
// =====================

ALGORITMO      : 'algoritmo';
FIM_ALGORITMO  : 'fim_algoritmo';

DECLARE        : 'declare';
TIPO           : 'tipo';
VAR            : 'var';

SE             : 'se';
ENTAO          : 'entao';
SENAO          : 'senao';
FIM_SE         : 'fim_se';

CASO           : 'caso';
SEJA           : 'seja';
FIM_CASO       : 'fim_caso';

PARA           : 'para';
FIM_PARA       : 'fim_para';

ENQUANTO       : 'enquanto';
FIM_ENQUANTO   : 'fim_enquanto';

FACA           : 'faca';
ATE            : 'ate';

PROCEDIMENTO       : 'procedimento';
FIM_PROCEDIMENTO   : 'fim_procedimento';

FUNCAO         : 'funcao';
FIM_FUNCAO     : 'fim_funcao';

RETORNE        : 'retorne';

REGISTRO       : 'registro';
FIM_REGISTRO   : 'fim_registro';

// Tipos
LITERAL    : 'literal';
INTEIRO    : 'inteiro';
REAL       : 'real';
LOGICO     : 'logico';
CONSTANTE  : 'constante';

// IO
LEIA       : 'leia';
ESCREVA    : 'escreva';

// Booleanos
VERDADEIRO : 'verdadeiro';
FALSO      : 'falso';

// Funções auxiliares
COMPRLITERAL : 'comprLiteral';
POSLITERAL   : 'posLiteral';
VALLITERAL   : 'valLiteral';

// =====================
// Operadores
// =====================
OP_ARIT   : '+' | '-' | '*' | '/' | '%';
OP_REL    : '>=' | '<=' | '<>' | '>' | '<' | '=';
OP_LOGICO : 'e' | 'ou' | 'nao';

// =====================
// Símbolos
// =====================
ATRIB       : '<-';
DOIS_PONTOS : ':';
VIRGULA     : ',';
INTERVALO   : '..';

PONTO       : '.';

ABRE_PAR    : '(';
FECHA_PAR   : ')';

ABRE_COL    : '[';
FECHA_COL   : ']';

PONTEIRO    : '&';
ENDERECO    : '^';

// =====================
// Literais
// =====================
CADEIA : '"' ( ~('"' | '\n') )* '"';

CADEIA_NAO_FECHADA : '"' ( ~('"' | '\n') )* '\n';

// =====================
// Números
// =====================
NUM_REAL : DIGIT+ '.' DIGIT+;
NUM_INT  : DIGIT+;

fragment DIGIT : [0-9];

// =====================
// Identificadores
// =====================
IDENT : [a-zA-Z_] [a-zA-Z_0-9]*;

// =====================
// Espaços e comentários
// =====================
WS : [ \t\r\n]+ -> skip;

COMENTARIO : '{' ~('}'|'{')* '}' -> skip;

COMENTARIO_NAO_FECHADO : '{' ~'}'* ;

ERRO : . ;