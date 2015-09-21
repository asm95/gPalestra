#ifndef PROFESSOR_H_INCLUDED
#define PROFESSOR_H_INCLUDED

#include "calendario.h"

#include "../tools/list.h"

typedef struct palestrante {
    char    *nome;
    int     n_disp;
    lnode   *l_disp;
} palestrante;

palestrante     *novo_palestrante       (char *nome, lnode *l_disp);
void            remove_palestrante      (palestrante *p);

int             lp_soma_disponibilidade (lnode *lp);

#endif
