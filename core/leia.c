#include <stdio.h>


#include "leia.h"
#include "filtra.h"
#include "calendario.h"

#include "../tools/list.h"
#include "../tools/ext_string.h"


#define BUF_MAX 512

int     streamNextLine  (s_stream *s);


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


    name = NULL;
    streamGetPattern(line, "Nome: %[.]\n", &name);

    if (!name){
        /*printf("ERRO em: leia_palestrante. Não foi possível obter dados de palestrante na linha %d\n", *lineN);*/

        streamNextLine(line);
        return NULL;
    }

    p = novo_palestrante(name, NULL);


    infoN = dispN = 0;
    le = list_new();

    if ( streamGetPattern(line, "Disponibilidade: ") )
        while ( 1 ){

            infoN += streamGetPattern(line, "%[,] %d/%d/%d, ", &day, &dd, &mm, &aaaa);
            infoN += streamGetPattern(line, "%d:%d-%d:%d", &sh, &sm, &eh, &em);

            if ( infoN == 15 ){
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
            }
            else{
                printf("\nErro em: leia_palestrante. Formato inválido para disponibilidade de palestrante %s\n", name);
                delString(name);
            }


            ec = streamFindChars(line, ";\n", 2);

            if ( ec == ';' )
                streamJump(line,1);
            else if ( ec == '\n' ){
                (*lineN) += 1;
                streamJump(line,1);
                break;
            }
            else{
                streamJump(line, STREAM_SIZE); /* provavelmente acabou o arquivo */
                break;
            }


            infoN = 0;
        }


    p->l_disp = le;
    p->n_disp = dispN;
    delString(name);

    return p;
}

int leia_palestra (char *arq, lnode **p){
    int         pal_lidas;
    s_stream    *fbuf;
    lnode       *pal_lista;
    palestra    *pal_nova;


    pal_lidas = 0;

    fbuf = newStream(arq, BUF_MAX);
    if (!fbuf){
        printf("Erro em: leia_palestra. Arquivo de palestras %s inválido.\n", arq);
        return pal_lidas;
    }

    pal_lista = list_new();

    while ( !EOStream(fbuf) ){
        pal_nova = palestra_line_itr(fbuf);

        if (pal_nova){
            list_insert(&pal_lista, pal_nova);
            pal_lidas++;
        }

    }

    *p = pal_lista;

    delStream(fbuf);

    return pal_lidas;
}

palestra    *palestra_line_itr (s_stream *l){
    int         infoN, max;
    palestra    *p = NULL;

    char    *nome, *pal, *tema, *loc;
    int     d1, d2, mm;

    infoN = 0;
    nome = pal = tema = loc = NULL;

    infoN += streamGetPattern(l, "Nome: %[.]\n", &nome);
    infoN += streamGetPattern(l, "Palestrante: %[.]\n", &pal);
    infoN += streamGetPattern(l, "Tema: %[.]\n", &tema);
    infoN += streamGetPattern(l, "Local: %[.]\n", &loc);
    infoN += streamGetPattern(l, "Duracao: %d:%dh.\n", &d1, &d2);

    if ( infoN != 17 ){
        delString(nome); delString(pal); delString(tema); delString(loc);
        streamNextLine(l);
        return NULL;
    }

    max = PAL_MAX_LABEL;

    filtraNome(nome, max);
    filtraLabels(max, 3, pal, tema, loc);

    mm = filtraHH_MM(d1, d2);

    if ( mm != -1 )
        p = nova_palestra(nome, loc, tema, pal, mm );

    delString(nome); delString(pal); delString(tema); delString(loc);

    return p;
}

int streamNextLine (s_stream *s){

    while ( streamFindNext(s, '\n') == -1 )
        if ( !stream_read(s, 0) ){ /* chegou ao final do arquivo */
            streamJump(s, STREAM_SIZE); /* setar EOS */
            return 0;
        }

    streamJump(s, 1);
    return 1;
}
