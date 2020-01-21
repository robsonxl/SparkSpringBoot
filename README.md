# SparkSpringBoot
SparkSpringBoot

#### Sobre o Conjunto de Dados: Conjunto de dados que possui  todas as requisições HTTP  do mes de agosto do ano de 1995 para o servidor da NASA Kennedy Space Center WWW na Flórida  para um período específico.

#### Fonte: 
    
    # https://github.com/robsonxl/SparkSpringBoot/blob/master/NASA-HTTP%20-%20Two%20Months%20of%20HTTP%20Logs%20from%20the%20KSC-NASA%20WWW%20Server.html
    
#### Link Arquivo de dados: 

    # ftp://ita.ee.lbl.gov/traces/NASA_access_log_Aug95.gz
  

 Para executar a aplicão, é necessário ter o docker instalado na máquina.

 Execute o comando abaixo para subir a aplicação

    # sudo docker run --name kennedy-space -it -p 8080:8080 robsonxlima/nasa-kennedy
    
Exemplos de Chamadas:  

Quantidade de Requisições agrupados por Hosts 

    # http://localhost:8080/uniqueGroupHosts

Total de  de hosts únicos.    

    # http://localhost:8080/uniqueHosts

Quantidade de requisições  de um determinado httpStatusCode agrupado por por dia 

    # http://localhost:8080/httpStatusCodeDate?httpStatusCode=200
    
Top x requisições  de um determinado httpStatusCode agrupados por URL 

    # http://localhost:8080/topUrl?httpStatusCode=200&topNumber=50

Total  de bytes retornados agupados por hostname 
   
    # http://localhost:8080/totalBytes



### Contador de Palavaras  (executar coleção no postman)
    
    # https://www.getpostman.com/collections/ab6b99d9b0675029b8f3
