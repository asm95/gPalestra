#include <stdio.h>


#include "leia.h"
#include "filtra.h"
#include "calendario.h"

#include "../tools/list.h"
#include "../tools/s_stream.h"
#include "../tools/ext_string.h"


#define BUF_MAX 512

void streamNextLine (s_stream *s);


int leia_palestrante (char *arq, lnode **p){
    int         pal_lidos, num_linha;
    s_stream    *fbuf;
    lnode       *pal_lista;
    palestrante *pal_novo;


    pal_lidos = 0;
    num_linha = 0;
    fbuf = newStream(arq, BUF_MAX);
    pal_lista = list_new();

    while ( !EOStream(fbuf) ){
        pal_novo = palestrante_line_itr(fbuf, &num_linha);

        if (pal_novo){
            list_insert(&pal_lista, pal_novo);
            pal_lidos++;
        }

    }

    *p = pal_lista;

    delStream(fbuf);

    return pal_lidos;
}

palestrante *palestrante_line_itr (s_stream *line, int *lineN){
    int     infoN, dispN;
    palestrante *p;

    char    *name, ec;
    evento  *e;
    lnode   *le;
    char    *day;
    int     dd, mm, aaaa, sh, sm, eh, em;


    streamGetPattern(line, "Nome: %[.]\n", &name);

    if (!name){
        printf("ERRO em: leia_palestrante. Não foi possível obter dados de palestrante na linha %d\n", *lineN);
        streamNextLine(line);
        (*lineN)++;
        return NULL;
    }

    p = novo_palestrante(name, NULL);


    infoN = dispN = 0;
    le = list_new();

    if ( streamGetPattern(line, "Disponibilidade: ") )
        while ( !EOStream(line) ){

            infoN += streamGetPattern(line, "%[,] %d/%d/%d, ", &day, &dd, &mm, &aaaa);
            infoN += streamGetPattern(line, "%d:%d-%d:%d%c", &sh, &sm, &eh, &em, &ec);

            if (infoN != 9){
                printf("Erro em: leia_palestrante. Formato inválido para disponibilidade na linha %d\n", *lineN);
                streamNextLine(line);
                delString(name);
                return p;
            }


            e = novoEvento();

            e->dia.str = day;
            e->dia.info.year = aaaa;
            e->dia.info.mon = mm;
            e->dia.info.mond = dd;

            e->dur.comeco.hour = sh;
            e->dur.comeco.min = sm;
            e->dur.fim.hour = eh;
            e->dur.fim.min = em;

            if ( validaEvento(e) ){
                list_insert(&le, list_cast(e));
                dispN++;
            }
            else
                removeEvento(e);


            if ( ec == '.' ){
                streamNextLine(line);
                break;
            }
        }


    p->l_disp = le;
    delString(name);

    return p;
}

void streamNextLine (s_stream *s){
    while ( streamFindNext(s, '\n') == -1 )
        if ( !stream_read(s, 0) )
            return;

    streamGetChar(s);
}
