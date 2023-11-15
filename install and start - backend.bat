@echo off

set /p username=Digite o nome de usário do MySQL:
set /p password=Digite a senha do MySQL: 

mysqlsh -u %username% -p%password% --sql < createDataBase.sql

if %errorlevel%==0 (
  echo Banco de dados "vetconnect" criado com sucesso.
) else (
  echo Houve um erro ao criar o banco de dados "vetconnect".
)

cd server 

call mvn clean package

echo Servidor instalado com sucesso.

cd target

echo Iniciando servidor.

start cmd /K "call java -jar api-0.0.1-SNAPSHOT.jar"

echo Servido inciado com suceso.

echo Pressione qualquer tecla para fechar esta janela.
pause > nul

