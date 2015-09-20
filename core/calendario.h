#ifndef CALENDARIO_H_INCLUDED
#define CALENDARIO_H_INCLUDED

#include "../tools/ext_time.h"


typedef struct dia {
    char        *str;
    struct tmd  info;
    struct dia  *prox;
} dia;

typedef struct duracao {
    struct tmh  comeco;
    struct tmh  fim;
} duracao;

typedef struct evento {
    dia             dia;
    duracao         dur;
    struct evento   *prox;
} evento;

evento  *novoEvento     ();
void    removeEvento    (evento *e);

#endif
