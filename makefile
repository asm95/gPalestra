CC = gcc
CFLAGS = -fno-builtin-strlen #remove warning de "conflicting types for built-in-function"
OBJS = ext_string.o palestrante.o teste.o main.o # lista de arquivos objeto


all: programa limpa

programa: $(OBJS)
	$(CC) $(OBJS) -L. -lcunit -o gpalestra

main.o: main.c
	$(CC) -c main.c -DDEBUG #parâmetro -DDEBUG ativa malha de testes

teste.o: teste/teste.c
	$(CC) -c teste/teste.c

palestrante.o: palestrante.c
	$(CC) -c palestrante.c

ext_string.o: tools/ext_string.c
	$(CC) -c tools/ext_string.c
 
limpa:
	rm *.o

tar:
	find ./ -name '*.c' -o -name '*.h' -o -name '*.a' -o -name '*.dat' -o -name 'LEIAME' -o -name 'makefile' | tar -cf gpalestra.tar -T -
