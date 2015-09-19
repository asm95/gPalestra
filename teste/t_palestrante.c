#include "../core/palestrante.h"

void tp_cria (void){
    palestrante *p;
    char        nome[10] = "Genaina";

    p = novo_palestrante(nome, NULL);

    CU_ASSERT( p->nome == nome );

    remove_palestrante(p);

    CU_ASSERT_PTR_NULL(p);
}
