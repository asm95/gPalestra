#ifndef S_STREAM_H_INCLUDED
#define S_STREAM_H_INCLUDED

#include <stdio.h>

/** s_stream is a simple set of functions to make code clear while reading text files
 ** please use up stream functions, otherwise it may confuse its engine
 **/

#define STREAM_NOT_FOUND -1
#define STREAM_READ_ERROR -2
#define STREAM_NO_MEMORY -3

typedef struct s_stream {
    int     bpos;
    FILE    *in;
    char    *self;
    int     bsize;
    int     bread;
} s_stream;

s_stream    *newStream          (char *f_loc, size_t buf_size);
void        delStream           (s_stream *s);
size_t      stream_read         (s_stream *s, int offset);
int         streamGetPattern    (s_stream *s, const char *pattern, ...);
int         streamFindNext      (s_stream *s, char c);
char        streamGetChar       (s_stream *s);
int         streamGetNumber     (s_stream *s);
int         EOStream            (s_stream *s);
int         streamGetPos        (s_stream *s);

#endif
