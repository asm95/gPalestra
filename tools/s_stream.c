#include <stdio.h>
#include <stdlib.h>
#include <stdarg.h>

#include "s_stream.h"
#include "ext_string.h"

#define STR_END(s, pos) (s[pos] == '\0')

s_stream *newStream (char *f_loc, size_t buf_size){
    FILE        *in;
    char        *buf = NULL;
    s_stream    *new = NULL;


    in = fopen(f_loc, "r");
    if (!in)
        return NULL;

    new = malloc( sizeof(s_stream) );
    buf = malloc( sizeof (char)*buf_size + 1 ); /* \0 */

    if (!new){
        fclose(in);
        free(new); free(buf);

        return NULL;
    }

    new->in = in;
    new->self = buf;
    new->bsize = buf_size+1;
    new->bpos = 0;

    return new;
}

void delStream (s_stream *s){
    fclose(s->in);
    free(s->self);
    free(s);
}

size_t stream_read (s_stream *s, int offset){
    int     from;
    size_t  elem;

    from = s->bsize - s->bpos;

    fseek(s->in, -from + offset, SEEK_CUR);

    elem = fread(s->self, sizeof(char), s->bsize-1, s->in);

    s->self[elem+1] = '\0';
    s->bpos = 0;

    return elem;
}

int streamGetPattern (s_stream *s, const char *pattern, ...){
    char    *s_pat;                 /* multable pattern pointer */
    int     argsN, foundN;
    int     result, pos;
    va_list ap;


    argsN = strcountc(pattern, '%');
    s_pat = pattern;


    /* check if we didn't stopped at end of stream buffer, otherwise it'll not read again */
    if (s->self[s->bpos] == '\0')
        stream_read(s, 0);


    if (argsN > 0){
        va_start(ap, argsN);
        foundN = 0;


        while ( argsN-- ){
            pos = strfind(s_pat, '%');

            s_pat[pos] = '\0';
            result = stream_str_in_call(s, s_pat);
            s_pat[pos] = '%';

            if (result == STREAM_NOT_FOUND || result == STREAM_READ_ERROR)
                return foundN;

            s_pat += pos+1;
            switch ( *s_pat ){
                case 'd':
                case 'D':
                    {
                        int *arg;

                        arg = va_arg(ap, int*);
                        *arg = streamGetNumber(s);
                    }
                    break;

                case 'c':
                case 'C':
                    {
                        char *arg;

                        arg = va_arg(ap, char*);
                        *arg = streamGetChar(s);
                    }
                    break;

                case 'f':
                case 'F':
                    /* _not_implemented_ */
                case '[':
                    if ( s_pat[2] == ']' ){
                        int     bpos;
                        char    *snew, *arg;


                        stream_read(s, -1);
                        bpos = streamFindNext(s, s_pat[1]);

                        if (bpos){
                            snew = malloc ( sizeof(char) * (bpos - (s->bpos) ) );

                            if (snew){
                                arg = va_arg(ap, char**);
                                *arg = snew;
                            }else
                                return foundN;
                        }

                        s_pat++;
                    }
            }
            s_pat++;
        }

        va_end(ap);
    }


    return stream_str_in_call(s, s_pat);
}

int stream_str_in_call (s_stream *s, const char *pattern){
    /* find simple pattern in buffer */
    int     i, k, bsize;
    char    *buf;

    if (pattern == '\0')
        return 0;

    bsize = s->bsize;
    buf = s->self;
    i = s->bpos;

    /* COMPLICATED! */
    while ( i<bsize ){
        if ( buf[i] == pattern[0] )
            for(k=1; ; k++){
                if ( STR_END(buf, i+k) ){
                    /* read more from buffer and continue from where it stopped */
                    if ( !stream_read(s, -k-1) ){
                        printf("Error in: stream_str_in_call. Could not read from file.\n");
                        return STREAM_READ_ERROR;
                    }
                    i = 0;
                }

                if ( buf[i+k] != pattern[k] ){
                    s->bpos = i+k;
                    return STREAM_NOT_FOUND;
                }

                if ( STR_END(pattern, k+1) ){
                    s->bpos = i+k;
                    return s->bpos;
                }

            }
        i++;
    }

    s->bpos = i;
    return STREAM_NOT_FOUND;
}

int streamFindNext (s_stream *s, char c){
    int pos;

    pos = strfind(s->self + s->bpos, c);

    s->bpos += pos;

    return (pos!=-1) ? (s->bpos) : -1;
}

char streamGetChar (s_stream *s){

    s->bpos += 1;

    if ( s->bpos == s->bsize ){
        stream_read(s, 0);
        s->bpos = 1;
    }

    return s->self[s->bpos-1];
}

int streamGetNumber (s_stream *s){
    int i, res;

    char *s_pat;

    s_pat = s->self;
    i = s->bpos;

    if (s_pat[i] == '-' || s_pat[i] == '+')
        i = i+1;

    res = 0;
    while ( 1 ){
        if ( s_pat[i] == '\0' )
            stream_read(s, 0);

        if ( s_pat[i] < '0' || s_pat[i] > '9' )
            break;

        res = res*10 + s_pat[i]-'0';

        i++;
    }

    res *= (s_pat[s->bpos] == '-') ? -1 : 1;

    s->bpos += i;

    return res;
}

int EOStream(s_stream *s){
    return feof(s->in);
}

int streamGetPos (s_stream *s){
    return s->bpos;
}
