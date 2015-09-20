CC = gcc
CFLAGS = -fno-builtin-strlen #remove warning de "conflicting types for built-in-function"
CLIBS = -L. -lcunit

PRGMA_O = 	core/calendario.o core/palestra.o core/palestrante.o \
			core/filtra.o core/leia.o main.o # lista de arquivos objeto
TOOLS_O = tools/ext_string.o tools/ext_time.o tools/s_stream.o tools/list.o tools/str_tree.o
DEBUG_O = teste/teste.o


all: programa limpa
Debug: programa limpa

programa: $(PRGMA_O) $(TOOLS_O) $(DEBUG_O)
	$(CC) $(PRGMA_O) $(TOOLS_O) $(DEBUG_O) $(CLIBS) -g -o gpalestra


main.o: main.c
	$(CC) -c main.c -DDEBUG #parâmetro -DDEBUG ativa malha de testes


teste/teste.o: teste/teste.c

core/calendario.o: core/calendario.c
core/palestra.o: core/palestra.c
core/palestrante.o: core/palestrante.c
core/filtra.o: core/filtra.c
core/leia.o: core/leia.c

tools/ext_string.o: tools/ext_string.c
tools/ext_time.o: tools/ext_time.c
tools/s_stream.o: tools/s_stream.c
tools/list.o: tools/list.c
tools/str_tree.o: tools/str_tree.c

 
limpa:
	find . -type f -name '*.o' -delete

tar:
	find ./ -name '*.c' -o -name '*.h' -o -name '*.a' -o -name '*.dat' -o -name 'LEIAME' -o -name 'makefile' | tar -cf gpalestra.tar -T -
