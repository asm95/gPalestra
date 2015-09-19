#ifndef PROFESSOR_H_INCLUDED
#define PROFESSOR_H_INCLUDED

#include "calendario.h"

typedef struct palestrante {
    char    *nome;
    evento  *l_disp;
} palestrante;

palestrante     *novo_palestrante   (char *nome, evento *l_disp);
void            remove_palestrante  (palestrante *p);

#endif
