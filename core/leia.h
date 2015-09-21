#ifndef LEIA_INCLUDED
#define LEIA_INCLUDED

#include "palestrante.h"
#include "palestra.h"
#include "../tools/s_stream.h"

int         leia_palestrante (char *arq, lnode **p);
palestrante *palestrante_line_itr (s_stream *line, int *lineN);

int         leia_palestra (char *arq, lnode **p);
palestra    *palestra_line_itr (s_stream *line);

#endif
