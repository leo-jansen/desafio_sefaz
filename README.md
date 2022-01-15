# Desafio Sefaz :computer:
Desafio tecnico 
* [Enunciado do teste](https://docs.google.com/document/d/1ajKeHSZIJtJeN7UsyTuCSRHZmer8Q9840-bU2vZnPzg/edit?usp=sharing)

## Tecnologias :toolbox:
Tecnologias utilizadas no projeto:
* [Java 11](https://www.oracle.com/br/java/technologies/javase-downloads.html)
* [H2 Database](http://www.h2database.com/html/download.html)
* [Maven](https://maven.apache.org/download.cgi)
* [Tomcat 9](https://tomcat.apache.org/download-90.cgi)
* [Docker](https://docs.docker.com/desktop/)

## Instalação :hammer_and_wrench:
Clone o repositorio do projeto 
```bash 
  git clone https://github.com/leo-jansen/desafio_sefaz.git
  cd desafio_sefaz
```
Build a imagem do projeto
```bash 
  docker build -t sefaz .
```
Com a imagem do projeto pronta, rode ela  
```bash 
  docker run -dp 8080:8080 sefaz 
```

### Necessários para a instalação :warning:
Programas necessários para o projeto rodar:
* Docker