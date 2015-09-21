#ifndef PALESTRA_H_INCLUDED
#define PALESTRA_H_INCLUDED

#define PAL_MAX_LABEL 30

#include "palestrante.h"

typedef struct palestra {
    char        nome[PAL_MAX_LABEL];
    char        tema[PAL_MAX_LABEL];
    char        local[PAL_MAX_LABEL];
    char        spal[PAL_MAX_LABEL];
    int         duracao; /*em minutos. Máximo de 22 dias */
    palestrante *lp;

} palestra;

palestra    *nova_palestra      (char *nome, char *local, char *tema, char *pal, int duracao);
void        remove_palestra     (palestra *p);
void        imprime_palestra    (palestra *p);
void        lpp_imprime_lista   (lnode *lp);


#endif
