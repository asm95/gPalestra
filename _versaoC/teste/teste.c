#include "teste.h"
#include "../core/leia.h"

#include "t_palestrante.c"
#include "t_palestra.c"

int cunit_init (){
	if (CUE_SUCCESS != CU_initialize_registry())
    	return CU_get_error();

   	adicionar_testes();

	CU_basic_set_mode(CU_BRM_VERBOSE);

	CU_basic_run_tests();

	CU_cleanup_registry();

	return CU_get_error();
}

void adicionar_testes (){
    char name[20] = "Teste Palestrante";
    CU_pSuite suite;

    suite = CU_add_suite(name, NULL, NULL);

    CU_ADD_TEST(suite, tp_cria);
    CU_ADD_TEST(suite, tp_leia);

    CU_ADD_TEST(suite, tp1_cria);
    CU_ADD_TEST(suite, tp1_leia);
}
