#ifdef DEBUG
    #include "teste/teste.h"
#endif

#include <stdio.h>
#include <stdlib.h>

#include "tools/list.h"
#include "core/palestrante.h"
#include "core/leia.h"

#define loc_arq_pal "Palestrantes.txt"

int main (void){
    lnode   *lp;
    int     pal_lidos;

    /* malha de testes */
    #ifdef DEBUG
        int cue_return;
        cue_return = cunit_init();

        if ( CUE_SUCCESS != cue_return ){
            printf("Erro Fatal de CUinit: %d.\n", cue_return);
            exit(1);
        }

    #endif

    pal_lidos = leia_palestrante (loc_arq_pal, &lp);

    printf("Foram lidos %d palestrantes de %s\n", pal_lidos, loc_arq_pal);

    list_clean(lp, list_cast(remove_palestrante));

	return 0;
}
