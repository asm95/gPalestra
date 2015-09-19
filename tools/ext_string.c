#include <stdlib.h>
#include "ext_string.h"

char *cloneString (char *s){
    return strcpy (s, (char *)malloc(strlen(s) * sizeof(char)) );
}

void delString(char *s){
    free(s);
}

char *strcpy (char *orig, char *dest)
{
    int i=0;
    while( (dest[i] = orig[i]) )
        i++;
    return dest;
}

int strlen (const char *s)
{
    int i=0;
    while(s[i] != '\0')
        i++;
    return i;
}

int strfind (char *s, char c)
{
    int i;
    for(i=0; s[i]!='\0'; i++)
        if (s[i] == c)
            return i;
    return -1;
}

int strfindN (char *s, char c, int pos)
{
    int i;
    for(i=0; s[i] != '\0'; i++)
        if (s[i] == c){
            if (!pos)
                return i;
            else
                pos--;
        }
    return -1;
}

int strcmp (char *s1, char *s2)
{
    int i=0;
    while (s1[i] != '\0' && s1[i] == s2[i])
        i++;
    return ( (unsigned char)s1[i] - (unsigned char)s2[i] ); /* some compiles may adopt signed integers to represent chars */
}

char *strin (char *s1, char *s2)
{
    int i, j;

    for(i=j=0; s1[i]!='\0'; i++)
        if (s1[i] == s2[j]){
            while (s2[j]){
                if (s1[i] != s2[j])
                    break;
                i++; j++;
            }

            if (!s2[j])
                return s1 + i - j;
            else
                j=0;
        }

    return NULL;
}

char *strjoin (const char *s1, const char *s2)
{
    char    *r;
    int     ss1, ss2;

    ss1 = strlen(s1);
    ss2 = strlen(s2);

    r = malloc(ss1 + ss2 + 1);

    memcpy (r, s1, ss1);
    memcpy (r+ss1, s2, ss2+1);

    return r;
}

void* memcpy(void* dest, const void* src, size_t count)
{
    char* dst8 = (char*)dest;
    char* src8 = (char*)src;

    while (count--) {
        *dst8++ = *src8++;
    }
    return dest;
}

int strcountc(const char *s, char c)
{
    int i, j;
    for(i=j=0; s[i] != '\0'; i++)
        if (s[i] == c) j++;
    return j;
}
