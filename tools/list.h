#ifndef LIST_H_INCLUDED
#define LIST_H_INCLUDED

#define list_cast(f) ( void(*)(void*) )f


typedef struct lnode {
    void            *data;
    struct lnode    *next;
    struct lnode    *prev;
} lnode;


lnode    *list_new       (void);

void    list_clean      (lnode *head, void(*f)(void *) );

int     list_empty      (lnode *head);

lnode   *list_insert    (lnode **head, void *data);

void    list_remove     (lnode **head, lnode *cur, void(*f)(void *) );

void    list_print      (lnode *head, void(*f)(void *) );

#endif /* LIST_H_INCLUDED */
