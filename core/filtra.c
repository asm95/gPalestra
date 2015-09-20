#include "filtra.h"
#include "calendario.h"

int validaEvento (evento *e){
    struct tmd  *dia;
    struct tmh  *a, *b;
    int         difh;

    dia = &e->dia.info;

    if ( dia->year < 0 || ( dia->mon < 1 || dia->mon > 12 ) || ( dia->mond < 1 || dia->mond > 31 ) )
        return 0;


    a = &e->dur.comeco;
    b = &e->dur.fim;

    difh = b->hour - a->hour;
    if ( difh < 0 || ( difh==0 && (b->min - a->min) < 0 ) )
        return 0;

    return 1;
}
