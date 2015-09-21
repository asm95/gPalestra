#include <stdarg.h>

#include "filtra.h"
#include "calendario.h"


int validaEvento (evento *e){
    struct tmd  *dia;
    struct tmh  *a, *b;
    int         difh;

    dia = &e->dia.info;

    if ( dia->year < 0 || ( dia->mon < 1 || dia->mon > 12 ) || ( dia->mond < 0 || dia->mond > 31 ) )
        return 0;


    a = &e->dur.comeco;
    b = &e->dur.fim;

    difh = b->hour - a->hour;
    if ( difh < 0 || ( difh==0 && (b->min - a->min) < 0 ) )
        return 0;

    return 1;
}

void filtraNome (char *n, int max){
    n[max] = '\0'; /* haha, sem tempo :S */
}

void filtraLabels (int max, int qtd, ...){
    char    *str;
    va_list ap;

    va_start(ap, qtd);

    while (qtd--){
        str = va_arg(ap, char*);

        str[max] = '\0';
    }
}

int filtraHH_MM(int d1, int d2){
    if ( d1 < 0 || d2 < 0 || d1 > 23 || d2 > 59 )
        return -1;

    return (d1*60)+d2;
}

void filtraMM_HH(int mm, int *d1, int *d2){
    *d1 = mm/60;
    *d2 = mm - (*d1)*60;
}
