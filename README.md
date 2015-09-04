#gPalestra

*Este programa poderá ser reutilizado para qualquer fim não lucrativo*
*Favor, citar autores: Cristiano Cardoso, Marcos Moura, Márcia Andréa e*

####0. ATENÇÃO####

####0.1 Como instalar o Git Hub no Ubuntu
Para instalar o Git, você precisa ter as seguintes bibliotecas que o Git depende: curl, zlib, openssl, expat e libiconv. Pelo apt-get do Ubuntu, você pode utilizar estes comandos para instalar todas as dependências:

	$ sudo apt-get update
	$ sudo apt-get install libcurl4-gnutls-dev libexpat1-dev gettext libz-dev libssl-dev

!eu precisei usar "sudo" antes do "apt-get"

Instalando
Se você quiser instalar o Git no Linux via um instalador binário, você pode fazê-lo com a ferramenta de gerenciamento de pacotes (packages) disponível na sua distribuição. Se você estiver em uma distribuição baseada no Debian, como o Ubuntu, use o apt-get:

	$ apt-get install git

!não precisa usar o "sudo"

Para verificar se o git foi instalado e as dependência estão OK, use o comando:

	$ sudo apt-get check git

!se tudo estiver ok retornará "Pronto"

####1. Como usar####
! No terminal de sua máquina, use a seguinte sintaxe:
	gpalestra


####1.1 Como baixar o repositório
	$ git clone https://github.com/asm95/gPalestra.git


! O programa automaticamente lerá o arquivo ...

####2. Como Compilar####
! Recomenda-se usar o gcc nesta versão:
cristian@Jasmine-VM:~$ gcc --version
gcc (Ubuntu/Linaro 4.8.1-10ubuntu9) 4.8.1

! Use o makefile


####3. Versões####
0.1 - 	Primeira versão do gPalestra! Só tem a implementação básica dos módulos


####4. Informações Extras####
Considera-se que o 'Trabalho Prático 1' de Programação Sistemática ...

