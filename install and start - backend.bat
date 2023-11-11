@echo off

cd server 

call mvn clean package

echo Servidor instalado com sucesso.

cd target

echo Iniciando servidor.

start cmd /K "call java -jar api-0.0.1-SNAPSHOT.jar"

echo Servido inciado com suceso.

echo Pressione qualquer tecla para fechar esta janela.
pause > nul

