#ifndef PROFESSOR_H_INCLUDED
#define PROFESSOR_H_INCLUDED

typedef struct palestrante {
    char    *nome;
    int    matricula;
} palestrante;

palestrante     *cria_palestrante     (char *nome, int matricula);
void            *remove_palestrante   (palestrante *p);

#endif
