@echo off

set arg1=%1
start cmd /k java -cp .\target\classes com.acme.edu.client_server.ClientReader %arg1%
start cmd /k java -cp .\target\classes com.acme.edu.client_server.ClientWriter %arg1%