#ifndef STR_TREE_H_INCLUDED
#define STR_TREE_H_INCLUDED

typedef struct str_node {
    char            *self;	/* string em si */
    struct str_node *left;	/* nó esquerdo */
    struct str_node *right;	/* nó direito */
} str_node;

str_node	*dic_new 	();

str_node 	*dic_add 	(str_node **node, char str[]);

void		dic_destroy	(str_node *node);

int 		dic_empty 	(str_node *node);

str_node 	*dic_find 	(str_node *node, char str[]);

#endif
