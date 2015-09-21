#ifndef FILTRA_H_INCLUDED
#define FILTRA_H_INCLUDED

#include "calendario.h"

int validaEvento        (evento *e);

void    filtraNome      (char *n, int max);
void    filtraLabels    (int max, int qtd, ...);
int     filtraHH_MM     (int d1, int d2);
void    filtraMM_HH     (int mm, int *d1, int *d2);

#endif
