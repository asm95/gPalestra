#include "../core/palestrante.h"

#define loc_arq_pal "Palestrantes.txt"

void tp_cria (void){
    palestrante *p;
    char        nome[10] = "Genaina";

    p = novo_palestrante(nome, NULL);

    CU_ASSERT( !strcmp(p->nome, nome) );

    remove_palestrante(p);

}

void tp_leia (void){
    lnode   *lp;
    int     pal_lidos;


    putchar('\n');
    pal_lidos = leia_palestrante (loc_arq_pal, &lp);

    printf("\nForam lidos %d palestrantes de %s\n\n", pal_lidos, loc_arq_pal);

    CU_ASSERT( pal_lidos == 2 );

    if (pal_lidos == 0)
        return;

    CU_ASSERT( lp_soma_disponibilidade(lp) == 4 );

    list_clean(lp, list_cast(remove_palestrante));

}

void tp_escreve(void){

}
