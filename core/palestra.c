#include "palestra.h"
#include "../tools/ext_string.h"
#include "../tools/list.h"
#include "filtra.h"

#include <stdio.h>

#include <stdlib.h>

palestra *nova_palestra (char *nome, char *local, char *tema, char *pal, int duracao){
    palestra	*p;

    p = malloc (sizeof(palestra));

    if (!p)
        return NULL;

    cpystr(nome, p->nome);
    cpystr(local, p->local);
    cpystr(tema, p->tema);
    cpystr(pal, p->spal);

    p->duracao = duracao;

    return p;
}

void remove_palestra (palestra *p){
    free(p);
}

void imprime_palestra (palestra *p){
    int d1, d2;

    filtraMM_HH(p->duracao, &d1, &d2);

    printf("Palestra %s:\n", p->nome);
    printf("Tema: %s\nPalestrante: %s\nLocal: %s\nDuração: %d:%dh.\n", p->tema, p->spal, p->local, d1, d2);
}

void lpp_imprime_lista (lnode *lp){
    int         i;
    palestra    *pp;

    i=0;
    while ( ( pp=list_get(lp) ) ){

        imprime_palestra(pp);

        list_next(&lp); /* para maior facilidade em usar listas teria de inventar uma ext_list */
        i++;
    }
    printf("\nImpressas %d palestras\n", i);

}
