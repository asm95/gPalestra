CC = gcc
CFLAGS = -fno-builtin-strlen #remove warning de "conflicting types for built-in-function"
CLIBS = -L. -lcunit

PRGMA_O = calendario.o palestrante.o leia.o main.o # lista de arquivos objeto
TOOLS_O = ext_string.o s_stream.o list.o str_tree.o
DEBUG_O = teste.o


all: programa limpa

programa: $(PRGMA_O) $(TOOLS_O) $(DEBUG_O)
	$(CC) $(PRGMA_O) $(TOOLS_O) $(DEBUG_O) $(CLIBS) -o gpalestra


main.o: main.c
	$(CC) -c main.c -DDEBUG #parâmetro -DDEBUG ativa malha de testes


teste.o: teste/teste.c

calendario.o: core/calendario.c
palestrante.o: core/palestrante.c
leia.o: core/leia.c

ext_string.o: tools/ext_string.c
s_stream.o: tools/s_stream.c
list.o: tools/list.c
str_tree.o: tools/str_tree.c

 
limpa:
	rm *.o

tar:
	find ./ -name '*.c' -o -name '*.h' -o -name '*.a' -o -name '*.dat' -o -name 'LEIAME' -o -name 'makefile' | tar -cf gpalestra.tar -T -
