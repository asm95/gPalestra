#ifdef DEBUG
    #include "teste/teste.h"
#endif

#include <stdio.h>
#include <stdlib.h>

int main (void){

    /* malha de testes */
    #ifdef DEBUG
        int cue_return;
        cue_return = cunit_init();

        if ( CUE_SUCCESS != cue_return ){
            printf("Erro Fatal de CUinit: %d.\n", cue_return);
            exit(1);
        }

    #endif

	return 0;
}
