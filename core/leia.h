#ifndef LEIA_INCLUDED
#define LEIA_INCLUDED

#include "palestrante.h"
#include "../tools/s_stream.h"

int         leia_palestrante (char *arq, palestrante *p);

palestrante *palestrante_line_itr (s_stream *line, int *lineN);

#endif
