# ![ok](https://github.com/DataStax-Academy/AstraPortia/blob/master/0_materials/ico.jpg?raw=true) Workshop Spring Data Cassandra and Stargate

[![Gitpod ready-to-code](https://img.shields.io/badge/Gitpod-ready--to--code-blue?logo=gitpod)](https://gitpod.io/#https://github.com/DataStax-Academy/workshop-spring-data-cassandra)
[![License Apache2](https://img.shields.io/hexpm/l/plug.svg)](http://www.apache.org/licenses/LICENSE-2.0)
[![Discord](https://img.shields.io/discord/685554030159593522)](https://discord.com/widget?id=685554030159593522&theme=dark)

Today we showcase an application using **Apache Cassandra™** as a backend implemented with **Spring Boot**, **Spring Data**, the **[Stargate](http://stargate.io)** and related SDK.

![SplashScreen](images/tutorials/splash.png?raw=true)

Which better business domain than the TV Show **Stargate** hoping it will not bring any confusion ^^.

![SplashScreen](images/tutorials/pic-travel.png?raw=true)

ℹ️ **Frequently asked questions**

- _Can I run the workshop on my computer?_
  > There is nothing preventing you from running the workshop on your own machine. If you do so, you will need _java jdk11+_, _Maven_, an IDE like _VSCode, IntelliJ, Eclipse, Spring STS_. You will have to adapt commands and paths based on your environment and install the dependencies by yourself. **We won't provide support** to keep on track with schedule.

## Materials for the Session

It doesn't matter if you join our workshop live or you prefer to do at your own pace, we have you covered. In this repository, you'll find everything you need for this workshop:

- [Slide deck](./slides.pdf)
- [Workshop video](https://www.youtube.com/watch?v=mL9oDZPJfwk)
- [Discord chat](https://bit.ly/cassandra-workshop)
- [Questions and Answers](https://community.datastax.com/)

## Table of contents

1. [Create Astra DB Instance](#1-create-astra-db-instance)
2. [Create Tables and insert data](#2-create-table-and-insert-data)
3. [Load dataSet as a CSV](#3-load-dataset-as-a-csv)
4. [Create Astra Token](#4-create-astra-token)
5. [Launch Gitpod](#5-launch-gitpod)
6. [Know your Gitpod](#6-know-your-gitpod)
7. [Setup your Application](#7-setup-your-application)
8. [Run Unit tests](#8-run-some-unit-tests)
9. [Run the Application](#9-run-the-application)
10. [Using Stargate Rest API](#10-using-stargate-rest-api)
11. [Using Stargate Document API](#11-using-stargate-document-api)
12. [Walkthrough Stargate SDK](#12-walkthrough-sdk)
13. [Homeworks](#13-homeworks)

## 1. Create Astra DB Instance

**`ASTRA DB`** is the simplest way to run Cassandra with zero operations at all - just push the button and get your cluster. No credit card required, $25.00 USD credit every month, roughly 5M writes, 30M reads, 40GB storage monthly - sufficient to run small production workloads.

✅ **Step 1a. If you do have an account yet register and sign In to Astra DB this is FREE and NO CREDIT CARD asked** [https://astra.datastax.com](https://astra.dev/9-2): You can use your `Github`, `Google` accounts or register with an `email`.

_Make sure to chose a password with minimum 8 characters, containing upper and lowercase letters, at least one number and special character_

✅ **Step 1b. Create a "pay as you go" plan**

Follow this [guide](https://docs.datastax.com/en/astra/docs/creating-your-astra-database.html), to set up a pay as you go database with a free $25 monthly credit. You will find below recommended values to enter:

- **For the database name** - `workshops`

- **For the keyspace name** - `stargate`

_You can technically use whatever you want and update the code to reflect the keyspace. This is really to get you on a happy path for the first run._

- **For provider and region**: Choose a provider (GCP, Azure or AWS) and then the related region is where your database will reside physically (choose one close to you or your users).

- **Create the database**. Review all the fields to make sure they are as shown, and click the `Create Database` button.

You will see your new database `pending` in the Dashboard.

![my-pic](images/tutorials/db-pending.png?raw=true)

The status will change to `Active` when the database is ready, this will only take 2-3 minutes. You will also receive an email when it is ready.

**👁️ Walkthrough**

![image](images/tutorials/astra-create-db.gif?raw=true)

[🏠 Back to Table of Contents](#table-of-content)

## 2. Create Table and insert data

Once the database is created we want to create the tables to insert Data.

✅ **Step2a: Locate and open CQLConsole**

- Click the name of you database `workshops` in the panel on the left

- Locate the tab `CQL Console`, the prompt will open, there is no need to enter credentials here.

![image](images/tutorials/cqlshconsole.png?raw=true)

✅ **Step 2b: Navigate to your keyspace**

> To ease the copy-paste you can use the small clipboard icons as show in the walkthrough. If the CTL+C and CTRL+V does not work in your browser you can also _right-click_ and then select _paste_.

- Enter the following statement in CQL console to list existing keyspaces, you should see the one you created with the database.

```sql
describe keyspaces;
```

- Enter the following statement in CQL console to select your keyspace:

```sql
use stargate;
```

✅ **Step 2c: Create Entities**

- Enter the following statement in CQL console to Create a table `chevrons` with the following fields

```sql
CREATE TABLE IF NOT EXISTS stargate.chevrons(
   area text,
   code int ,
   name text,
   picture text,
   PRIMARY KEY ((area), code)
) WITH CLUSTERING ORDER BY (code ASC);
```

✅ **Step 2d: Populate entries**

- Enter the following statement in CqlSH console to enter the different chevrons in the database

```sql
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 1, 'Earth', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/001glyph-earth.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 2, 'Crater', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/002glyph-crater.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 3, 'Virgo', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/003glyph-virgo.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 4, 'Bootes', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/004glyph-bootes.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 5, 'Centaurus', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/005glyph-centarus.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 6, 'Libra', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/006glyph-libra.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 7, 'Serpenscaput', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/007glyph-serpenscaput.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 8, 'Norma', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/008glyph-norma.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 9, 'Scorpio', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/009glyph-scorpio.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 10, 'Cra', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/010glyph-cra.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 11, 'Scutum', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/011glyph-scutum.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 12, 'Sagitarus', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/012glyph-sagittarius.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 13, 'Aquila', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/013glyph-aquila.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 14, 'Mic', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/014glyph-mic.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 15, 'Capricorn', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/015glyph-capricorn.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 16, 'Piscesaustrinus', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/016glyph-piscesaustrinus.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 17, 'Equuleus', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/017glyph-equuleus.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 18, 'Aquarius', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/018glyph-aquarius.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 19, 'Pegasus', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/019glyph-pegasus.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 20, 'Sculptor', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/020glyph-sculptor.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 21, 'Pisces', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/021glyph-pisces.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 22, 'Andromeda', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/022glyph-andromeda.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 23, 'Triangulum', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/023glyph-triangulum.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 24, 'Aries', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/024glyph-aries.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 25, 'Perseus', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/025glyph-perseus.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 26, 'Cetus', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/026glyph-cetus.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 27, 'Taurus', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/027glyph-taurus.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 28, 'Auriga', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/028glyph-auriga.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 29, 'Eridanus', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/029glyph-eridanus.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 30, 'Orion', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/030glyph-orion.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 31, 'Canisminor', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/031glyph-canisminor.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 32, 'Monoceros', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/032glyph-monoceros.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 33, 'Gemini', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/033glyph-gemini.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 34, 'Hydra', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/034glyph-hydra.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 35, 'Lynx', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/035glyph-lynx.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 36, 'Cancer', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/036glyph-cancer.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 37, 'Sextans', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/037glyph-sextans.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 38, 'Leominor', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/038glyph-leominor.jpg?raw=true');
INSERT INTO chevrons (area, code, name, picture) VALUES ('Milky Way', 39, 'Leo', 'https://github.com/datastaxdevs/workshop-spring-stargate/blob/main/images/glyphs/039glyph-leo.jpg?raw=true');
```

You can notice than those are real images, thanks to [StargateWiki](http://stargate-sg1-solutions.com/wiki/Main_Page).

![#](images/glyphs/001glyph-earth.jpg?raw=true)
![#](images/glyphs/002glyph-crater.jpg?raw=true)
![#](images/glyphs/003glyph-virgo.jpg?raw=true)
![#](images/glyphs/004glyph-bootes.jpg?raw=true)...

✅ **Step 2e: Show the results**

We have inserted 39 symbols of the `Milky Way` galaxy with `INSERT` statements but we could have used other solutions like [dsbulk](https://github.com/datastax/dsbulk), Spark, Apis or Astra Data Loader. We will show some of them to you later.

- Validate the number of chevrons

```sql
select count(*) from stargate.chevrons;
```

- Show the chevrons known for the `Milky Way` galaxy

```sql
select code,name from stargate.chevrons where area='Milky Way';
```

[🏠 Back to Table of Contents](#table-of-content)

## 3. Load dataSet as a CSV

Inserting a couple of values with CQL console is great but quite verbose, correct? We created the table and inserted a few values.

In Astra DB there is a tool to speed up that process and both create and import data from a CSV, instead.

✅ **Step 3a: Download the dataset**

To download the DATASET, **right-click** _(or CTRL + Click to open in new tab)_ the button below and download the target file on your machine.

> _If the file opens in the browser save it with the name `destinations.csv`. The name is important as the filename will be the table name._

<p align="left">
<a href="https://raw.githubusercontent.com/datastaxdevs/workshop-spring-stargate/main/dataset/destinations.csv">
 <img src="https://dabuttonfactory.com/button.png?t=Download Dataset&f=Roboto-Bold&ts=26&tc=fff&hp=45&vp=20&c=11&bgt=unicolored&bgc=15d798" />
</a>
</p>

✅ **Step 3b: Open Astra Data Loader**

Locate the `Upload Data` button to open the Data Loader.

![#](images/tutorials/dataloader-01-button.png)

✅ **Step 3c: Upload the dataset**

Click on the area _Drag n drop a single file_ and look for the file `destinations.csv` on your machine, this file has been downloaded in step **3a**.

![#](images/tutorials/dataloader-02-upload.png)

Once the file has been uploaded notice the `Upload Successful` message in green. You can now click `NEXT`

✅ **Step 3d: Define the target table**

- Locate the field Table Name and make sure it is set to `destinations`

![#](images/tutorials/dataloader-03-parsed.png)

Scroll down to show the the **Keys and Clustering** part of the screen and enter the following

- use the dropdown `galaxy` which will be our partition key (assuming there are less than 100,000 stargates in our galaxy) and a lot of galaxy ^^.

- `name` will be our clustering key in order to ensure a unique name of the planet in the galaxy.

- You can now click `NEXT`

![#](images/tutorials/dataloader-04-datamodel.png)

✅ **Step 3e: Define the target keyspace**

- In the **Target Keyspace** combo box find and select our keyspace `stargate`

- Then click `NEXT`

![#](images/tutorials/dataloader-05-keyspace.png)

✅ **Step 3f: Show Data**

After a few seconds (about 30s),you will get an email informing you that the batch has been scheduled.

![#](images/tutorials/dataloader-06-mail1.png)

As you can see the operation here is asynchronous. About a minute later your will get another email to tell you the data has been inserted.

![#](images/tutorials/dataloader-07-mail2.png)

Using the CQL Console enter the CQL command that was suggested in the email as below.

```sql
SELECT * FROM stargate.destinations LIMIT 10;
```

**👁️ Expected output**
![#](images/tutorials/dataloader-08-data.png)

❓ **QUIZ:** Try to find by the correct CQL statement to retrieve the coordinates of planet Chulak in our galaxy.

<details><summary><b>Click to view Solution</b></summary>
<p>

```sql
SELECT chevron1,chevron2,chevron3,chevron4,chevron5,chevron6 FROM stargate.destinations WHERE galaxy='Milky Way' and name='Chulak';
```

</p>
</details>

Yes now we do have the cartouche (a carved tablet or drawing representing a scroll with rolled-up ends) - `9,2,23,16,37,20` ([You can check that we are correct](http://stargate-sg1-solutions.com/wiki/Chulak))

![#](images/glyphs/009glyph-scorpio.jpg?raw=true)
![#](images/glyphs/002glyph-crater.jpg?raw=true)
![#](images/glyphs/023glyph-triangulum.jpg?raw=true)
![#](images/glyphs/016glyph-piscesaustrinus.jpg?raw=true)
![#](images/glyphs/037glyph-sextans.jpg?raw=true)
![#](images/glyphs/020glyph-sculptor.jpg?raw=true)
![#](images/glyphs/001glyph-earth.jpg?raw=true)

🎉🎉 **Congratulations** we do have both chevrons and coordinates of our destination.

[🏠 Back to Table of Contents](#table-of-content)

## 4. Create Astra Token

However, to save _Teal'c_ from _Apophysis_ we still need to create a **token** that we will use as our credentials.

✅ **Step 4a: Generate Token**

Following the [Manage Application Tokens docs](https://docs.datastax.com/en/astra/docs/manage-application-tokens.html) create a token with `Database Admnistrator` roles.

- Go the `Organization Settings`

- Go to `Token Management`

- Pick the role `Database Admnistrator` on the select box

- Click Generate token

**👁️ Walkthrough**

![image](images/tutorials/astra-create-token.gif?raw=true)

This is what the token page looks like. You can now download the values as a CSV. We will need those values but you can also keep this window open for use later.

![image](images/tutorials/astra-token.png?raw=true)

Notice the clipboard icon at the end of each value.

- `clientId:` We will use it as a _username_ to contact Cassandra

- `clientSecret:` We will use it as a _password_ to contact Cassandra

- `appToken:` We will use it as a api token Key to interact with APIs.

To know more about roles of each token you can have a look to [this video.](https://www.youtube.com/watch?v=nRqu44W-bMU)

**Note: Make sure you don't close the window accidentally or otherwise - if you close this window before you copy the values, the application token is lost forever. They won't be available later for security reasons.**

> **⚠️ Important**
> ```
> The instructor will show you on screen how to create a token 
> but will have to destroy to token immediately for security reasons.
> ```

We are now set with the database and credentials. Let's start coding with Spring !

[🏠 Back to Table of Contents](#table-of-content)

## 5. Launch Gitpod

[Gitpod](https://www.gitpod.io/) is an IDE 100% online based on [VS Code](https://github.com/gitpod-io/vscode/blob/gp-code/LICENSE.txt?lang=en-US). To initialize your environment simply click on the button below _(CTRL + Click to open in new tab)_ You will be asked for you github account, as needed.

[![Open in Gitpod](https://gitpod.io/button/open-in-gitpod.svg)](https://gitpod.io/#https://github.com/datastaxdevs/workshop-spring-stargate)

**👁️ Expected output**

_The screenshot may be slightly different based on your default skin and a few edits in the read me._

![gitpod](images/tutorials/gitpod-01-home.png?raw=true)

**That's it.** Gitpod provides all tools we will need today including `Maven` and exporting port `8080`. At initialization of the workspace we schedule a `mvn clean install` to download dependencies.

Also you may have noticed that a build is happening - even before we get started. The sample project already exists and loading the developer enviroment triggers a build to download all the maven dependencies so you don't have to.

[🏠 Back to Table of Contents](#table-of-content)

## 6. Know your gitpod

✅ **Step 6a: Know your public URL**

The workshop application has opened with an ephemeral URL. To know the URL where your application endpoint will be exposed you can run the following command in the terminal after the build has completed.

```bash
gp url 8080
```

**👁️ Expected output**

![gitpod](images/tutorials/gitpod-02-url.png?raw=true)

✅ **Step 6b: Build the project**

- Using maven build the project and download its dependencies.

```bash
cd /workspace/workshop-spring-stargate/stargate-demo && mvn clean package install -Dmaven.test.skip=true
```

**👁️ Expected output**

![gitpod](images/tutorials/gitpod-04-build.png?raw=true)

[🏠 Back to Table of Contents](#table-of-content)

## 7. Setup your application

To run the application you need to provide the credentials and identifier to the application. you will have to provide 6 values in total as shown below

![gitpod](images/tutorials/gitpod-05-appyml.png?raw=true)

✅ **Step 7a: Enter 3 values from the token**

- Open the file `stargate-demo/src/main/resources/application.yml` as show below.

- Replace `client-id`, `clientSecret`, `application-token` with values shown on the Astra token screen or picking the values from the CSV token file your dowloaded before including the AstraCS: part of the token.

> To ease the copy-paste you can use the small clipboard icons as show in the walkthrough.

✅ **Step 7b: Enter 3 values related to your DB**

- In Astra DB go back to home page by clicking the logo

- Select you database `workshops` in the left panel and then copy values for `cloud-region` and `database-id` (clusterID) from the details page.

**👁️ Walkthrough**

![gitpod](images/tutorials/copy-credentials.gif?raw=true)

TADA your application is now configured we can finally play with some code.

[🏠 Back to Table of Contents](#table-of-content)

## 8. Run some unit tests

The application is now set you should be able to interact with your DB. Let's demonstrate some capabilities.

✅ **Step 8a: Use CqlSession**

Interaction with Cassandra are implemented in Java through drivers and the main Class is `CqlSession`.

Higher level frameworks like Spring, Spring Data, or even quarkus will rely on this object so let's make sure it is part of your Spring context with a `@SpringBootTest`.

```bash
mvn test -Dtest=com.datastax.demo.stargate.Ex1_UseCqlSessionTest
```

**👁️ Expected output**

```bash
[..]
+ Code=38, name='Leominor',
+ Code=39, name='Leo',
[OK] - Test Successfully you ROCK !
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 8.42 s - in com.datastax.demo.stargate.Ex1_UseCqlSessionTest
18:23:34.167 INFO  com.datastax.stargate.sdk.StargateClient      : Closing CqlSession.
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
-----------------------------------------------------------------------[INFO] BUILD SUCCESS
-----------------------------------------------------------------------[INFO] Total time:  13.985 s
[INFO] Finished at: 2021-04-27T18:23:34+02:00
------------------------------------------------------------------------
```

✅ **Step 8b: Working With Spring Data**

Spring Data allows Mapping `Object <=> Table` based on annotation at the java bean level. Then by convention CQL query will be executed under the hood.

```bash
mvn test -Dtest=com.datastax.demo.stargate.Ex2_UseSpringDataChevronTest
```

This test perform 3 operations:

- Find all Chevrons in the table
- Find a chevron by its primary key
- Find all chevrons in our galaxy (partition key)

**👁️ Expected output**

```bash
[...]
 [OK]  - Test #2.1 Successful - you ROCK !
[...]
[OK]  - Test #2.2 Successful - you ROCK !
[...]
[OK]  - Test #2.3 Successful - you ROCK !
```

We can also query the destination table to find coordinates:

```bash
mvn test -Dtest=com.datastax.demo.stargate.Ex3_UseSpringDataDestinationTest
```

[🏠 Back to Table of Contents](#table-of-content)

## 9. Run the application

✅ **Step 9a: Start the application**

Navigate to the folder and simply start the application with the following command.

```bash
cd /workspace/workshop-spring-stargate/stargate-demo
mvn spring-boot:run
```

Your application is now started you should have a peek at the logs here

```bash
Picked up JAVA_TOOL_OPTIONS: -Xmx1879m
  _________ __                             __
  /   _____//  |______ _______  _________ _/  |_  ____
  \_____  \\   __\__  \\_  __ \/ ___\__  \\   __\/ __ \
  /        \|  |  / __ \|  | \/ /_/  > __ \|  | \  ___/
 /_______  /|__| (____  /__|  \___  (____  /__|  \___  >
        \/           \/     /_____/     \/          \/
 ________
 \______ \   ____   _____   ____
  |    |  \_/ __ \ /     \ /  _ \
  |    `   \  ___/|  Y Y  (  <_> )
 /_______  /\___  >__|_|  /\____/
         \/     \/      \/

 DataStax Developer Advocate team

Starting StargateDemoApplication using Java 11.0.10 on
No active profile set, falling back to default profiles: default
+ Load Environment Variables
+ Load Builder parameters
+ HttpClient Initialized
+ API(s) Devops is [ENABLED]
+ Load Secure Connect: /home/gitpod/.astra/secure_connect_bundle_58a2e502-fccf-4524-ac22-e59277e63edc.zip
+ CQL Credentials: ${clientId}${/clientSecret}
+ API(s) Document is [ENABLED] https://58a2e502-fccf-4524-ac22-e59277e63edc-us-east-1.apps.astra.datastax.com/api/rest
+ API(s) REST Data is [ENABLED] https://58a2e502-fccf-4524-ac22-e59277e63edc-us-east-1.apps.astra.datastax.com/api/rest
+ API(s) GraphQL [ENABLED] https://58a2e502-fccf-4524-ac22-e59277e63edc-us-east-1.apps.astra.datastax.com/api/graphql
+ API(s) Cql is [ENABLED]
+ Keyspace stargate
[StargateClient] has been initialized
[AstraClient] has been initialized.
[THYMELEAF][restartedMain] Template Mode 'XHTML' is deprecated. Using Template Mode 'HTML' instead.
Started StargateDemoApplication in 7.883 seconds (JVM running for 8.497)
```

**👁️ Expected output**

![gitpod](images/tutorials/gitpod-06-start.png?raw=true)

✅ **Step 9b: Open a new terminal in gitpod**

The application is running on our first terminal. To enter new commands please create a new terminal in gitpod.

![gitpod](images/tutorials/new-terminal.png?raw=true)

✅ **Step 9c: Open the application**

Are you ready? Now is the time to play the demo.

It is better to use a dedicated TAB in the browser to open the application in full screen. Use this command to show the URL.

```bash
gp url 8080
```

If you have the link in the terminal gitpod, it's a lot easier to create a new tab by clicking `Follow link`.

![gitpod](images/tutorials/follow-link.png?raw=true)

✅ **Step 9d: Play the demo**

- Click the Stargate logo, the music starts if it did not start automatically

![SplashScreen](images/tutorials/demo-home.png?raw=true)

- Click on the planet name you want to reach here `CHULAK` the coordinates are not entered in the Stargate.

- Click on one orange `chrevron` in the order to make the wheel start spinning. Click the chevrons one after the other to simulate the dialing. As soon as all chevrons are engaged the vortex will open.

![SplashScreen](images/tutorials/demo-home-2.png?raw=true)

Activate the rest of the chevrons to open the stargate...Buckle up !

![SplashScreen](images/tutorials/demo-home-3.png?raw=true)

Congratulations you Played the demo !

![demo](images/demo.gif?raw=true)

[🏠 Back to Table of Contents](#table-of-content)

## 10. Using Stargate Rest API

The gateway [Stargate](stargate.io) allows you to execute the operations through a REST API. Let's list the chevrons.

✅ **Step 10a: Open swagger**

In a terminal that is not running the demo enter the command in order to open the swagger UI from Astra (URL has been built based on the values you entered in `application.yaml`)

```bash
/workspace/workshop-spring-stargate/open-swagger.sh
```

**👁️ Script output**

![SplashScreen](images/tutorials/open-swagger.png?raw=true)

**👁️ Expected output**

![gitpod](images/tutorials/gitpod-07-swagger.png?raw=true)

✅ **Step 10b: List Chevrons**

- In the Swagger UI page locate the blue line `/v1/keyspaces/{keyspaceName}/tables/{tableName}/rows` in the block `DATA`

![gitpod](images/tutorials/restapi-retrieve-rows.png?raw=true)

- Click the button `Try it Out` on the top right hand corner.

- Enter the following values

| FIELD                 | VALUE                        |
| --------------------- | ---------------------------- |
| **X-Cassandra-Token** | `AstraCS....` (_your token_) |
| **keyspaceName**      | `stargate`                   |
| **tableName**         | `chevrons`                   |
| **pageSize**          | _let it blank_               |
| **pageState**         | _let it blank_               |

- Click the button `Execute`

**Expected output**

![gitpod](images/tutorials/restapi-retrieve-rows-2.png?raw=true)

[🏠 Back to Table of Contents](#table-of-content)

## 11. Using Stargate Document API

The REST API covers the same features as the CQL interface and you need to know your schema.

Stargate also provide a way to insert **schemaless** JSON Document as you would do with a document oriented datbase, you are welcomed.

✅ **Step 11a: Create a document**

- Locate ` ​/v2​/namespaces​/{namespace-id}​/collections​/{collection-id} Create a new document` in the block `DOCUMENT`

- Click the button `Try it Out` on the top right hand corner.

- Enter the following values

| FIELD                 | VALUE                        |
| --------------------- | ---------------------------- |
| **X-Cassandra-Token** | `AstraCS....` (_your token_) |
| **namespace-id**      | `stargate`                   |
| **collection-id**     | `sampledoc`                  |
| **pageSize**          | _let it blank_               |
| **body**              |

```json
{
  "videoid": "e466f561-4ea4-4eb7-8dcc-126e0fbfd573",
  "email": "clunven@sample.com",
  "title": "A video",
  "upload": "2020-02-26 15:09:22 +00:00",
  "url": "http://google.fr",
  "frames": [1, 2, 3, 4],
  "tags": ["cassandra", "accelerate", "2020"],
  "formats": {
    "mp4": { "width": 1, "height": 1 },
    "ogg": { "width": 1, "height": 1 }
  }
}
```

**Expected output**

![gitpod](images/tutorials/docapi-createdoc.png?raw=true)

- Click the button `Execute`

The api will create a new table for the collection and insert the JSON document. A new unique identifier is generated and returned as `documentId`.

**Expected output**
![gitpod](images/tutorials/docapi-createdoc-2.png?raw=true)

[🏠 Back to Table of Contents](#table-of-content)

## 12. Walkthrough SDK

Well you had an overview about the APIs exposed by Stargate. There is also a [GraphQL API if you want to know more](https://docs.datastax.com/en/astra/docs/using-the-astra-graphql-api.html).

A SDK or _Software Developement Kit_ is used here to ease the usage of each API. It is also the one creating a bean `CqlSession` that will be used with Spring Data Cassandra as is.

The Astra SDK has been installed with a single starter dependency. More information is [here](https://github.com/datastax/astra-sdk-java/wiki)

```xml
<dependency>
  <groupId>com.datastax.astra</groupId>
  <artifactId>astra-spring-boot-starter</artifactId>
  <version>0.1.14</version>
</dependency>
```

✅ **Step 12a: Using devops API with SDK**

In a gitpod terminal use Maven to execute a unit test illustrating the usage of the `Devops API` from the SDK.

```bash
cd /workspace/workshop-spring-stargate/stargate-demo
mvn test -Dtest=com.datastax.demo.stargate.Ex4_SdkDevopsApi
```

**Expected Output**

```bash
13:48:16.753 INFO  com.datastax.demo.stargate.Ex4_SdkDevopsApi   : Started Ex4_SdkDevopsApi in 6.184 seconds (JVM running for 7.136)
Database 'workshops'
+ id=3c7fc647-c03b-4a0c-aa6b-a00dd677ac53
+ region=eu-central-1
+ keyspace=stargate
```

✅ **Step 12b: Using REST API with SDK**

In a gitpod terminal use Maven to execute a unit test illustrating the usage of the `REST API` from the SDK.

```bash
mvn test -Dtest=com.datastax.demo.stargate.Ex5_SdkRestApi
```

**Expected Output**

```bash
13:47:38.123 INFO  com.datastax.demo.stargate.Ex5_SdkRestApi     : Started Ex5_SdkRestApi in 6.375 seconds (JVM running for 7.375)
Earth
Crater
Virgo
Bootes
Centaurus
Libra
Serpenscaput
Norma
Scorpio
Cra
Scutum
Sagitarus
Aquila
Mic
Capricorn
Piscesaustrinus
Equuleus
Aquarius
Pegasus
Sculptor
13:47:41.173 INFO  com.datastax.stargate.sdk.StargateClient      : Closing CqlSession.
```

✅ **Step 12c: Using DOC API with SDK**

In a gitpod terminal use Maven to execute a unit test illustrating the usage of the `DOC API` from the SDK.

```bash
mvn test -Dtest=com.datastax.demo.stargate.Ex6_SdkDocApi
```

**Expected Output**

```bash
13:46:43.360 INFO  com.datastax.demo.stargate.Ex6_SdkDocApi      : Started Ex6_SdkDocApi in 5.935 seconds (JVM running for 6.887)
Document:
+ id=063d3fe9-8ec0-4b9b-887a-90da52ee0f51
+ email= clunven@sample.com
```

[🏠 Back to Table of Contents](#table-of-content)

## 13. Homeworks

<img src="https://github.com/datastaxdevs/workshop-spring-stargate/raw/main/images/tutorials/badge.png?raw=true" width="200" align="right" />

Don't forget to complete your upgrade and get your verified skill badge! Finish and submit your homework!

1. Complete the practice steps from this repository as described above. Make screenshots alongside the steps
2. Learn more about spring in the [course](https://www.datastax.com/dev/spring) and complete scenario `Build a Spring Boot REST Service` at the bottom of the page and make a screenshot of the "congratulations" page.
3. Submit your homework [here](https://github.com/datastaxdevs/workshop-spring-stargate/issues/new?assignees=HadesArchitect&labels=homework%2Cpending&template=homework-assignment.md&title=%5BHW%5D+%3CNAME%3E)

That's it, you are done! Expect an email next week!

[🏠 Back to Table of Contents](#table-of-content)

## 14. The END

Congratulations, your made it to the END of the show.

**💚 Share the love**

<a href="https://www.facebook.com/sharer/sharer.php?u=https://github.com/datastaxdevs/workshop-spring-stargate"><img src="images/tutorials/share-facebook.png"/></a>

<a href="https://twitter.com/intent/tweet?url=https://github.com/datastaxdevs/workshop-spring-stargate&text=I attended a wonderful workshop today , thank you @Datastax, @clunven, @sonicdmg"><img src="images/tutorials//share-twitter.png"/></a>

<a href="https://www.linkedin.com/shareArticle?mini=true&url=https://github.com/datastaxdevs/workshop-spring-stargate&title=&summary=I attended a wonderful workshop today , thank you @Datastax, @clunven, @sonicdmg&source="><img src="images/tutorials/share-linkedin.png"/></a>

**🧑🏻‍🤝‍🧑🏽 Let's get in touch**

| ![B](images/tutorials/david.png)                           | ![B](images/tutorials/cedrick.png)                 |
| ---------------------------------------------------------- | -------------------------------------------------- |
| David Gilardi <br>[@SonicDMG](https://github.com/SonicDMG) | Cedrick Lunven<br>[@clun](https://github.com/clun) |

---

[![thankyou](images/tutorials/thankyou.gif)]()
