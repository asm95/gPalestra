#include "../core/palestra.h"

#define loc_arq_pal2 "Palestras.txt"

void tp1_cria (void){
    palestra    *p;
    char    nome[] = "NomePalestra99";
    char    npal[] = "Dilma";
    char    tema[] = "Impostos: os benefícios.";
    char    local[] = "Desconhecido";
    int     dur = 1439;


    p = nova_palestra(nome, local, tema, npal, dur);

    putchar('\n');
    imprime_palestra(p);

    CU_ASSERT( !strcmp(nome, p->nome) );

    remove_palestra(p);

}

void tp1_leia (void){
    lnode   *lp;
    int     pal_lidas;


    pal_lidas = leia_palestra(loc_arq_pal2, &lp);

    printf("\n\nForam lidas %d palestras de %s\n", pal_lidas, loc_arq_pal2);

    CU_ASSERT( pal_lidas == 2 );

    if (pal_lidas == 0)
        return;

    lpp_imprime_lista(lp);

    putchar('\n');
    list_clean(lp, list_cast(remove_palestra));

}

void tp1_escreve(void){

}
