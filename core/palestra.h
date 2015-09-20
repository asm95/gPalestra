#ifndef PALESTRA_H_INCLUDED
#define PALESTRA_H_INCLUDED

typedef struct palestra {
    char        nome[25];
    char        tema[30];
    char        local[30];
    int         duracao; /*em minutos. Máximo de 22 dias */
    palestrante *lp;

} plestra;


#endif
