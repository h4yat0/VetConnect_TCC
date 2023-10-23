@echo off

set /p username=Digite o nome de usário do MySQL:
set /p password=Digite a senha do MySQL: 

mysqlsh -u %username% -p%password% --sql < createDataBase.sql

if %errorlevel%==0 (
  echo Banco de dados "vetconnect" criado com sucesso.
) else (
  echo Houve um erro ao criar o banco de dados "vetconnect".
)

echo Iniciando instalação do servidor back-end.

cd server 

call mvn clean package

echo Servidor instalado com sucesso.

cd target

echo Servido inciado com suceso.

echo Inciando intalação do servidor de desenvolvimento front-end.

cd ..
cd ../web 

call npm install

echo Servidor de desenvolvimento intalado com sucesso.

echo Pressione qualquer tecla para fechar esta janela.
pause > nul

