![image](https://user-images.githubusercontent.com/89386377/216011827-c0e539bb-d78a-42c5-b3a3-184d0d10761f.png)
# Inhoudsopgave
1. [Inleiding](#Inleiding)
2. [Benodigdheden web service ](#paragraph1)
    1. [Client localhost Windows of MacOs](#subparagraph1)
    2. [IDE en JDK](#subparagraph2)
    3. [PgAdmin en PostgreSQL](#subparagraph3)
    4. [Postman](#subparagraph4)
    5. [Web service configureren](#subparagraph5)
3. [Gebruikers rollen](#paragraph2)
4. [REST-Endpoints](#paragraph3)
    1. [REST-Endpoint auth/role](#sub1)
    2. [REST-Endpoint /customers](#sub2)
    3. [REST-Endpoint /goals](#sub3)
    4. [REST-Endpoint /diets](#sub4)
    5. [REST-Endpoint /recipes](#sub5)
    6. [REST-Endpoint /products](#sub6)
    7. [REST-Endpoint /single/uploadDb/](#sub7)

## Inleiding <a name="Inleiding"></a>
Dit document geeft instructies voor het opstarten van de Bro’chef web service en de
applicaties die benodigd zijn voor een soepele start. Bro’chef web service draait lokaal met
een eigen database.

## Benodigdheden web service <a name="paragraph1"></a>
### Client localhost Windows of MacOs <a name="subparagraph1"></a>
Onze web service is gemaakt op te werken op een clients localhost. Localhost kan je
omschrijven als ‘deze computer’. Zodra je een URL intypt in jouw browser, maak je
verbinding met een ‘host’ elders in de wereld. Die ‘host’ zorgt ervoor dat de webpagina die jij
hebt opgevraagd, bekeken kan worden.
### IDE en JDK <a name="subparagraph2"></a>
Een JDK heb je nodig om code te compilen, JDK kort voor Java development kit gebruikt
voor het ontwikkelen van de bro’chef webservice. Er is gekozen voor Java 17 vanwege de
belofte van long term support vanuit Java.

Een IDE (Integrated Development Environment) is een softwaretoepassing waarmee
ontwikkelaars code binnen één programma kunnen maken, bewerken en compileren. Deze
toepassingen hebben vaak ingebouwde tools, zoals syntaxis accentuering en automatische
aanvulling, waardoor codering eenvoudiger wordt voor programmeurs. Voor het ontwikkelen
van de web service heb ik gebruik gemaakt van Intellij.

Bij het downloaden van de applicatie als zip bestand, pak deze uit en open het project.
Intellij is slim genoeg om te vragen voor het downloaden van een gewenste JDK.

Je kan de applicatie ook binnen halen vanuit een URL

![image](https://user-images.githubusercontent.com/89386377/216021842-927c469f-334c-4f78-82b1-a9faf454d245.png)

Download: https://www.jetbrains.com/idea/download

Instructie: voor het installeren van Intellij zijn er geen extra instructies nodig
### PgAdmin en PostgreSQL <a name="subparagraph3"></a>
Wij hebben een database nodig om data op te slaan. Hiervoor hebben we gebruik gemaakt
van PostgreSql.

PostgreSQL is een open-source object-relationeel database management systeem wereld.
Die ‘host’ zorgt ervoor dat de webpagina die jij hebt opgevraagd, bekeken kan worden. De
belangrijkste functies ervan zijn het veilig opslaan en opvragen van data, terwijl de huidige
beste praktijken op computergebied worden ondersteund.

Download: https://www.postgresql.org/download/

Instructies:

![image](https://user-images.githubusercontent.com/89386377/216022201-319c6261-6b22-4287-85b9-bf5e7204c402.png)

PgAdmin is een database management-tool voor PostgreSQL. Het biedt een grafische
gebruikersinterface waardoor je gemakkelijk kunt werken met jouw database.
PGAdmin wordt geïnstalleerd met PostgreSQL.

Mocht je geen PgAdmin geïnstalleerd hebben gekregen.

Download: https://www.pgadmin.org/download/

Instructie: ‘Next,’Next,...’Finish’. Geen verdere installatie instructies nodig.
### Postman <a name="subparagraph4"></a>
Postman is een handige tool wanneer je RESTful API’s wilt creëren, delen, testen en
documenteren. Het biedt een moderne gebruikersinterface, waarbij er geen gedoe is met het
schrijven van lastige code, maar waarbij gebruik kan worden gemaakt van het eenvoudige
dataformaat JSON. Voor het versturen van een simple request is het binnen Postman dan
ook alleen maar nodig om de URL in te vullen, de verplichte headers en de HTTP method.

REST-endpoints voor de bro’chef web service wordt verder in dit document toegelicht

Je kan op 2 manieren gebruik maken van Postman. Ik adviseer lokaal zodat je makkelijk ook
imports kunt maken.

Download: https://www.postman.com/downloads/

Instructie: ‘Next,’Next,...’Finish’. Geen verdere installatie instructies nodig.

![image](https://user-images.githubusercontent.com/89386377/216022803-1c9d6cde-f09c-4f0e-9790-becae57bbde2.png)

### Web service configureren <a name="subparagraph5"></a>
We zijn er bijna, de laatste configuratie moeten nog ingeregeld worden en dan kan eindelijk
de applicatie web service opgestart worden.
### 5.1 Database creëren
1. Database creëren voor de webservice, let op de benaming en sla het wachtwoord op
dat is ingesteld voor de ‘Superuser’.
2. Open PgAdmin en na het inloggen, rechts klik op databases ‘Create’ en kies
‘Database’
3. Geef het database een unieke naam of neem die over van het project
’brochef-webservicedb’ vervolgens ‘Save’.
### 5.2 Applicatie starten.
1. Start Intellij, bij het eerste keer opstarten kies ‘Open’ om het uitgepakte zip project te
openen. Of open vanuit het Git url.
2. klik op ‘File’ en kies ‘Project Structure’ of op windows ctrl+alt+shift+s shortkey,
Controleer of openjdk-17 is geselecteerd. Als de SDK al is geïnstalleerd ga verder
met stap 5.
3. Om een SDK te installeren ga naar tabblad ‘SDK's en kies downloaden.
4. Als het downloaden klaar is, selecteer Apply.
5. Ga naar ‘src/main/resources/application.properties’ en dubbel klik
‘application.properties’
6. Pas de volgende regels aan, aan de hand van je ingestelde databasenaam, poort en
wachtwoord van PostgreSQL; voorbeeld afbeelding

![image](https://user-images.githubusercontent.com/89386377/216023628-bfeb8432-b2ce-4499-be5f-bed982b437c4.png)

7. Om de applicatie te starten in Intellij, klik op het groene ‘Play’ knop;

![image](https://user-images.githubusercontent.com/89386377/216023750-2088e1c6-631d-438e-8d72-31b357b308c7.png)

8. Als de knop grijs is, navigeer naar ‘src/main/java/nl.broscience../… en rechterklik op
‘BrochefWebServiceApplication’ en klik op ‘Run’Brochefweb..’

![image](https://user-images.githubusercontent.com/89386377/216023877-8a668d75-7024-4748-aadf-dd079c3dc3af.png)

## Gebruikers rollen <a name="paragraph2"></a>
Onderstaand zijn de rollen beschreven en de autorisaties die het rol toegewezen krijgt.

![image](https://user-images.githubusercontent.com/89386377/216024123-64764390-a383-4db8-8526-02a32f1dcd19.png)

Om gebruik te maken van de REST-Endpoints dient een gebruiker aangemaakt te worden
en vervolgens geautoriseerd worden
## REST-Endpoints <a name="paragraph3"></a>
Onderstaand zijn de REST-Endpoints beschreven. In het ingeleverde project map voor de
eindopdracht is een bestand geplaatst ‘Bro chef web service.postman_collection.json’.

Dat ziet er als volgt uit;

![image](https://user-images.githubusercontent.com/89386377/216024257-454ab15a-bd87-4d01-916c-75e1b7b9c4ec.png)

Als het bestand is geïmporteerd kunnen wij starten met het ophalen van bestaande data, of
nieuwe data toevoegen. Het is belangrijk dat een user account wordt aangemaakt en dat
die ook geautoriseerd is om verzoeken te plaatsen.

Vervolgens kunnen wij aan de hand van [PUT/GET/DELETE/POST] en het path, JSON data
versturen naar de webservice.

### 1. REST-Endpoint auth/role <a name="sub1"></a>
Eerst maken wij een gebruiker aan in het onderstaand voorbeeld maken wij een ‘USER’ aan
en vervolgens gaan wij die autoriseren.

![image](https://user-images.githubusercontent.com/89386377/216024798-2610ff64-b8ed-44c1-877a-52774191e466.png)

Het net aangemaakte account autoriseren zodat wij gebruik kunnen maken van de
resterende REST-Endpoints.

![image](https://user-images.githubusercontent.com/89386377/216024915-ae6ea078-0813-40d9-a80f-4abf2dcd8231.png)

Kopieer het ‘Token’: Voorbeeld afbeelding header token;

![image](https://user-images.githubusercontent.com/89386377/216025074-7cc04749-3a08-45e1-8ca3-b2d06634849d.png)

Hiermee kunnen wij de resterende REST-Endpoints aanspreken, onder tab ’Authorization’
Selecteer type ‘Bearer Token’ en kopieer de token op het veld ‘Token’. Zie voorbeeld;

![image](https://user-images.githubusercontent.com/89386377/216025178-bb0f8952-a964-4f49-9012-3cd24f03ed62.png)

## 2. REST-Endpoint /customers <a name="sub2"></a>
REST-Endpoint om customers aan te maken, verwijderen, updaten of ophalen.

![image](https://user-images.githubusercontent.com/89386377/216025381-523b9b35-ca6b-4284-9450-460cae19ebe1.png)

## 3. REST-Endpoint /goals <a name="sub3"></a>
![image](https://user-images.githubusercontent.com/89386377/216025489-eab0af61-cad0-4ea4-9d36-b35634504849.png)
## 4. REST-Endpoint /diets <a name="sub4"></a>
![image](https://user-images.githubusercontent.com/89386377/216025890-7defd9b4-ed95-4b21-9825-80a41128e077.png)
## 5. REST-Endpoint /recipes <a name="sub5"></a>
![image](https://user-images.githubusercontent.com/89386377/216026095-07f81335-ccce-425b-b2f5-a502be3e81be.png)
## 6. REST-Endpoint /products <a name="sub6"></a>
![image](https://user-images.githubusercontent.com/89386377/216026208-c6527408-2cb8-4d27-9e04-2bc1ee7fa172.png)
## 7. REST-Endpoint /single/uploadDb/ <a name="sub7"></a>
![image](https://user-images.githubusercontent.com/89386377/216026313-79c04e86-40f5-48a8-bfcc-23679ae2367b.png)

