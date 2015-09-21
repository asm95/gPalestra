#include <stdio.h>
#include <stdlib.h>
#include <stdarg.h>

#include "s_stream.h"
#include "ext_string.h"

#define STR_END(s, pos) (s[pos] == '\0')

#define isNumber(c)  (c >= '0' && c <= '9')
#define isSign(c)    (c == '-' || c == '+')


int stream_str_in_call (s_stream *s, char *pattern);


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

    buf[0] = '\0';

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

    from = s->bread - s->bpos;

    fseek(s->in, -from + offset, SEEK_CUR);

    elem = fread(s->self, sizeof(char), s->bsize-1, s->in);

    s->self[elem+1] = '\0';
    s->bpos = 0;
    s->bread = elem;

    return elem;
}

int streamGetPattern (s_stream *s, const char *pattern, ...){
    char    *s_pat, *bs_pat; /* multable pattern pointer */
    int     argsN, foundN;
    int     result, pos;
    va_list ap;


    argsN = strcountc(pattern, '%');
    bs_pat = s_pat = cloneString((char*)pattern);


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

            if (result == STREAM_NOT_FOUND || result == STREAM_READ_ERROR)
                return foundN;
            else if ( result != PATTERN_EMPTY )
                foundN++;

            s_pat += pos+1;
            switch ( *s_pat ){
                case 'd':
                case 'D':
                    {
                        int *arg, num;

                        arg = va_arg(ap, int*);

                        if ( streamGetNumber(s, &num) ){
                            *arg = num;
                            foundN++;
                        }
                        else{
                            delString(bs_pat);
                            return foundN;
                        }
                    }
                    break;

                case 'c':
                case 'C':
                    {
                        char *arg, c;

                        arg = va_arg(ap, char*);

                        if ( streamGetChar(s, &c) ){
                            *arg = c;
                            foundN++;
                        }
                        else{
                            delString(bs_pat);
                            return foundN;
                        }
                    }
                    break;

                case 'f':
                case 'F':
                    {
                    /* _not_implemented_ */
                    }
                    break;

                case '[':
                    if ( s_pat[2] == ']' ){
                        int     bpos;
                        char    *snew, **arg;


                        stream_read(s, 0);
                        bpos = strfind(s->self, s_pat[1]);

                        if (bpos != -1){
                            snew = malloc ( sizeof(char) * (bpos - (s->bpos) ) );

                            if (snew){
                                arg = va_arg(ap, char**);

                                s->self[bpos] = '\0';
                                cpystr(s->self, snew);
                                s->self[bpos] = s_pat[1];

                                *arg = snew;
                                foundN++;
                            }
                            else{
                                delString(bs_pat);
                                return foundN;
                            }

                            s->bpos = bpos+1;
                        }
                        else{
                            delString(bs_pat);
                            return foundN;
                        }

                        s_pat += 2;
                    }
            }
            s_pat++;
        }

        va_end(ap);
    }
    else
    {
        return stream_str_in_call(s, s_pat);
    }

    result = stream_str_in_call(s, s_pat);

    if ( result ){
        delString(bs_pat);
        return foundN + ( (result!=PATTERN_EMPTY)?1:0 );
    }
    else{
        delString(bs_pat);
        return 0;/* we are just indicating that the pattern does not match, but
        the previous variables were already set. Should fix this. */
    }

}

int stream_str_in_call (s_stream *s, char *pattern){
    /* find simple pattern in buffer */
    int     i, k;
    char    *buf;

    if (*pattern == '\0')
        return PATTERN_EMPTY;

    buf = s->self;
    i = s->bpos;

    if ( buf[i] != pattern[0] )
        return STREAM_NOT_FOUND;

	for(k=1; ; k++){

		if ( STR_END(pattern, k) ){
			s->bpos = i+k;
			return s->bpos;
		}

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
	}

    s->bpos = i;
    return STREAM_NOT_FOUND;
}

int streamFindNext (s_stream *s, char c){
    int pos;

    pos = strfind(s->self + s->bpos, c);

    if ( pos != -1 ){
        s->bpos += pos;
        return s->bpos;
    }

    return STREAM_NOT_FOUND;
}

int streamGetChar (s_stream *s, char *c){

    s->bpos += 1;

    if ( s->bpos == s->bsize ){
        if ( !stream_read(s, 0) )
            return 0;

        s->bpos = 1;
    }

    *c = s->self[s->bpos-1];

    return 1;
}

int streamGetNumber (s_stream *s, int *num){
    int     i, res;
    char    *pat;

    pat = s->self;
    i = s->bpos;

    if ( !isNumber(pat[i]) )
        return 0;

    if ( isSign(pat[i]) ){

        if ( !isNumber(pat[i+1]) )
            return 0;
        else
            i = i+1;
    }

    res = 0;
    while ( 1 ){
        if ( pat[i] == '\0' )
            stream_read(s, 0);

        if ( pat[i] < '0' || pat[i] > '9' )
            break;

        res = res*10 + pat[i]-'0';

        i++;
    }

    res = res * ( (pat[s->bpos] == '-') ? -1 : 1 );

    s->bpos = i;

    *num = res;

    return 1;
}

int EOStream(s_stream *s){
    return ( s->self[s->bpos] == '\0' && feof(s->in) );
}

int streamGetPos (s_stream *s){
    return s->bpos;
}

int streamJump (s_stream *s, int i){
    if ( i == STREAM_SIZE ){
        s->bpos = s->bread+1;
        return 1;
    }

    s->bpos = (s->bpos+i)%(s->bsize-1);

    if (s->bpos < 0)
        s->bpos = 0;
    return 1;
}

char streamFindChars (s_stream *s, char *vec, int max){
    char    *buf;
    int     i, j, bmax;

    buf = s->self;
    i = s->bpos;
    bmax = s->bread;

    for (; i<bmax; i++)
        for(j=0; j<max; j++)
            if ( buf[i] == vec[j] ){
                s->bpos = i;
                return vec[j];
            }

    return '\0';
}
