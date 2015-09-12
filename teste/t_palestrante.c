#include "../palestrante.h"

void tp_cria (void){
    palestrante *p;
    char        nome[10] = "Genaina";
    int         mat;

    mat = 150051010;

    p = cria_palestrante(nome, mat);

    CU_ASSERT( p->nome == nome );
    CU_ASSERT( p->matricula == mat);

    remove_palestrante(p);

    CU_ASSERT_PTR_NULL(p);
}
