grammar Jander;

// LÉXICO

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

OP_E   : 'e';
OP_OU  : 'ou';
OP_NAO : 'nao';

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

// =====================
// SINTÁTICO
// =====================

programa
    : declaracoes ALGORITMO corpo FIM_ALGORITMO EOF
    ;

declaracoes
    : (decl_local_global)*
    ;

decl_local_global
    : declaracao_local | declaracao_global
    ;

declaracao_local
    : DECLARE variavel
    | CONSTANTE IDENT DOIS_PONTOS tipo_basico OP_REL valor_constante
    | TIPO IDENT DOIS_PONTOS tipo
    ;

variavel
    : identificador (VIRGULA identificador)* DOIS_PONTOS tipo
    ;

identificador
    : IDENT (PONTO IDENT)* dimensao
    ;

dimensao
    : (ABRE_COL exp_aritmetica FECHA_COL)*
    ;

tipo
    : registro | tipo_estendido
    ;

tipo_basico
    : LITERAL | INTEIRO | REAL | LOGICO
    ;

tipo_basico_ident
    : tipo_basico | IDENT
    ;

tipo_estendido
    : (ENDERECO)? tipo_basico_ident
    ;

valor_constante
    : CADEIA | NUM_INT | NUM_REAL | VERDADEIRO | FALSO
    ;

registro
    : REGISTRO (variavel)* FIM_REGISTRO
    ;

declaracao_global
    : PROCEDIMENTO IDENT ABRE_PAR (parametros)? FECHA_PAR (declaracao_local)* (cmd)* FIM_PROCEDIMENTO
    | FUNCAO IDENT ABRE_PAR (parametros)? FECHA_PAR DOIS_PONTOS tipo_estendido (declaracao_local)* (cmd)* FIM_FUNCAO
    ;

parametro
    : (VAR)? identificador (VIRGULA identificador)* DOIS_PONTOS tipo_estendido
    ;

parametros
    : parametro (VIRGULA parametro)*
    ;

corpo
    : (declaracao_local)* (cmd)*
    ;

cmd
    : cmdLeia | cmdEscreva | cmdSe | cmdCaso | cmdPara | cmdEnquanto
    | cmdFaca | cmdAtribuicao | cmdChamada | cmdRetorne
    ;

cmdLeia
    : LEIA ABRE_PAR (ENDERECO)? identificador (VIRGULA (ENDERECO)? identificador)* FECHA_PAR
    ;

cmdEscreva
    : ESCREVA ABRE_PAR expressao (VIRGULA expressao)* FECHA_PAR
    ;

cmdSe
    : SE expressao ENTAO (cmd)* (SENAO (cmd)*)? FIM_SE
    ;

cmdCaso
    : CASO exp_aritmetica SEJA selecao (SENAO (cmd)*)? FIM_CASO
    ;

cmdPara
    : PARA IDENT ATRIB exp_aritmetica ATE exp_aritmetica FACA (cmd)* FIM_PARA
    ;

cmdEnquanto
    : ENQUANTO expressao FACA (cmd)* FIM_ENQUANTO
    ;

cmdFaca
    : FACA (cmd)* ATE expressao
    ;

cmdAtribuicao
    : (ENDERECO)? identificador ATRIB expressao
    ;

cmdChamada
    : IDENT ABRE_PAR (expressao (VIRGULA expressao)*)? FECHA_PAR
    ;

cmdRetorne
    : RETORNE expressao
    ;

selecao
    : (item_selecao)*
    ;

item_selecao
    : constantes DOIS_PONTOS (cmd)*
    ;

constantes
    : numero_intervalo (VIRGULA numero_intervalo)*
    ;

numero_intervalo
    : (op_unario)? NUM_INT (INTERVALO (op_unario)? NUM_INT)?
    ;

op_unario
    : '-'
    ;

exp_aritmetica
    : termo (op1 termo)*
    ;

termo
    : fator (op2 fator)*
    ;

fator
    : parcela (op3 parcela)*
    ;

op1 : '+' | '-' ;
op2 : '*' | '/' ;
op3 : '%' ;

parcela
    : (op_unario)? parcela_unario
    | parcela_nao_unario
    ;

parcela_unario
    : (ENDERECO)? identificador
    | IDENT ABRE_PAR (expressao (VIRGULA expressao)*)? FECHA_PAR
    | NUM_INT
    | NUM_REAL
    | ABRE_PAR expressao FECHA_PAR
    ;

parcela_nao_unario
    : PONTEIRO identificador | CADEIA
    ;

exp_relacional
    : exp_aritmetica (op_relacional exp_aritmetica)?
    ;

op_relacional
    : OP_REL
    ;

expressao
    : termo_logico (OP_OU termo_logico)*
    ;

termo_logico
    : fator_logico (OP_E fator_logico)*
    ;

fator_logico
    : (OP_NAO)? parcela_logica
    ;

parcela_logica
    : VERDADEIRO
    | FALSO
    | exp_relacional
    ;

op_logico_1 : OU ;
op_logico_2 : E ;