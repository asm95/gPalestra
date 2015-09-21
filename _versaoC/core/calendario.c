#include <stdlib.h>
#include "calendario.h"

evento *novoEvento (){
    evento *e = NULL;

    e = malloc ( sizeof(evento) );

    return e;
}

void removeEvento (evento *e){
    free(e->dia.str);
    free(e);
}
