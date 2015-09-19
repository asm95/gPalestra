#include <stdio.h>


#include "leia.h"
#include "../tools/list.h"
#include "../tools/s_stream.h"
#include "../tools/ext_string.h"


#define BUF_MAX 512

void streamNextLine (s_stream *s);


int leia_palestrante (char *arq, palestrante *p){
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

        if (p){
            list_insert(&pal_lista, pal_novo);
            pal_lidos++;
        }

    }

    return pal_lidos;
}

palestrante *palestrante_line_itr (s_stream *line, int *lineN){
    int     pos;
    int     infoN, dispN;

    palestrante *p;
    char    *name;
    char    dia[3];
    int     dd, mm, aaaa, sh, sm, eh, em, ec;


    streamGetPattern(line, "Nome: %[\n]", &name);

    if (!name){
        printf("ERRO em: leia_palestrante. Não foi possível obter dados de palestrante na linha %d\n", *lineN);
        streamNextLine(line);
        (*lineN)++;
        return NULL;
    }

    p = novo_palestrante(name, NULL);

    infoN = dispN = 0;
    pos = streamGetPos(line);
    /* loop para disponibilidade */
    if ( streamGetPattern(line, "Disponibilidade: ") )
        while ( !EOStream(line) ){
            if ( streamGetPattern(line, ";\n") )
                break;

            infoN += streamGetPattern(line, "%[,] %d/%d/%d, ", dia, &dd, &mm, &aaaa);
            infoN += streamGetPattern(line, "%d:%d-%d:%d;%c", &sh, &sm, &eh, &em, &ec);

            if (infoN != 9){
                printf("Erro em: leia_palestrante. Formato inválido para disponibilidade na linha %d\n", *lineN);
                streamNextLine(line);
                delString(name);
                return NULL;
            }
        }


    delString(name);

    return p;
}

void streamNextLine (s_stream *s){
    while ( streamFindNext(s, '\n') == -1 )
        if ( !stream_read(s, 0) )
            return;

    streamGetChar(s);
}
