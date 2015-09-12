#include <stdio.h>
#include <stdlib.h>

#include "list.h"


lnode *list_new (void){
    return NULL;
}


void list_clean (lnode *head, void (* f)(void *)){
    lnode *cur, *tmp;

    cur = head;
    while (cur != NULL){
        tmp = cur->next;

        (*f)(cur->data);
        free(cur);

        cur = tmp;
    }
}

int list_empty(lnode *head){
    return (!head);
}

lnode *list_insert(lnode **head, void *data){
    lnode *cur, *new;

    new = malloc(sizeof (lnode));

    if (!new)
        return NULL;

    new->data = data;
    new->next = NULL;

    if ( !*head ){
        new->prev = NULL;
        *head = new;

        return new;
    }

    cur = *head;
    while (1){
        if (cur->next != NULL)
            cur = cur->next;
        else
            break;
    }
    cur->next = new;
    new->prev = cur;


    return new;
}

void list_remove(lnode **head, lnode *cur, void (* f)(void *)){
    if (!cur->prev)
        *head = cur->next;
    else
        (cur->prev)->next = (cur->next);

    if (cur->next)
        (cur->next)->prev = (cur->prev);

    if (f != NULL)
        (*f)(cur->data);

    free(cur);
}

void list_print(lnode *head, void(*f)(void *)){
    lnode *cur;

    printf("\nlist_print of %p\n", head);

    cur = head;
    while (cur != NULL){
        (*f)(cur->data);

        cur = cur->next;
    }
}
