#include "palestrante.h"
#include "tools/ext_string.h"

#include <stdlib.h>

palestrante *cria_palestrante (char *nome, int matricula){
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
    p->matricula = matricula;

    return p;
}

void *remove_palestrante (palestrante *p){
    free(p->nome);
    free(p);
}
