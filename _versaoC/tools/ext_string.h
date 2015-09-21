#ifndef MYSTRING_H_INCLUDED
#define MYSTRING_H_INCLUDED

typedef unsigned long size_t; /* stddef.h */

char    *cloneString    (char *s);
void    delString       (char *s);
char    *cpystr         (char *orig, char *dest);
int     lenstr          (const char *s);
int     strfind         (char *s, char c);
int     strfindN        (char *s, char c, int pos);
int     strcmp          (char *s1, char *s2);
char    *strin          (char *s1, char *s2);
char    *strjoin        (const char *s1, const char *s2);
void    *memcpy         (void* dest, const void* src, size_t count);
int     strcountc       (const char *s, char c);

#endif
