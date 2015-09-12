#include "teste.h"

#include "t_palestrante.c"

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
    CU_pSuite suite;

    suite = CU_add_suite("Teste Palestrante", NULL, NULL);

    CU_ADD_TEST(suite, tp_cria);
}