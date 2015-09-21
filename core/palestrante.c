#include "palestrante.h"
#include "../tools/ext_string.h"

#include <stdlib.h>

palestrante *novo_palestrante (char *nome, lnode *l_disp){
    char		*n;
    palestrante	*p;

    p = malloc (sizeof(palestrante));

    if (!p)
        return NULL;

    n = cloneString(nome);

    if (!n){
        free(p);
        return NULL;
    }

    p->nome = n;
    p->l_disp = l_disp;
    p->n_disp = 0;

    return p;
}

void remove_palestrante (palestrante *p){
    free(p->nome);
    free(p);
}

int lp_soma_disponibilidade (lnode *lp){
    int         soma;
    palestrante *p;

    soma = 0;

    while ( ( p=list_get(lp) ) ){

        soma += p->n_disp;

        list_next(&lp); /* para maior facilidade em usar listas teria de inventar uma ext_list */
    }

    return soma;
}
