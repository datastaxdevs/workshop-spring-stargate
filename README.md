
# ![ok](https://github.com/DataStax-Academy/AstraPortia/blob/master/0_materials/ico.jpg?raw=true) Workshop Spring Data Cassandra and Stargate

[![Gitpod ready-to-code](https://img.shields.io/badge/Gitpod-ready--to--code-blue?logo=gitpod)](https://gitpod.io/#https://github.com/DataStax-Academy/workshop-spring-data-cassandra) 
[![License Apache2](https://img.shields.io/hexpm/l/plug.svg)](http://www.apache.org/licenses/LICENSE-2.0)
[![Discord](https://img.shields.io/discord/685554030159593522)](https://discord.com/widget?id=685554030159593522&theme=dark)

Today we show you and application using  **Apache Cassandra™** as a backend implemented with **Spring Boot**, **Spring Data**, the [Stargate](stargate.io) and relating SDK.

![SplashScreen](images/tutorials/splash.png?raw=true)

Which better business domain than the TV Show **Stargate** hoping it will not bring any confusion ^^.

![SplashScreen](images/tutorials/pic-travel.png?raw=true)


ℹ️ **Frequently asked questions**

- *Can I run the workshop on my computer?*
> There is nothing preventing you from running the workshop on your own machine. If you do so, you will need *java jdk11+*, *Maven*, an IDE like *VSCode, IntelliJ, Eclipse,Spring STS*. You will have to adapt commands and paths based on your environment and install the dependencies by yourself. **We won't provide support** to keep on track with schedule.

## Table of content

1. [Create Astra Instance](#1-create-astra-instance)
2. [Create Astra Token](#2-create-astra-token)

## 1. Create Astra Instance

**`ASTRA`** is the simplest way to run Cassandra with zero operations at all - just push the button and get your cluster. No credit card required, $25.00 USD credit every month, roughly 5M writes, 30M reads, 40GB storage monthly - sufficient to run small production workloads.  

✅ **Step 1a. If you do have an account yet register and sign In to Astra this is FREE and NO CREDIT CARD asked** [https://astra.datastax.com](https://dtsx.io/workshop): You can use your `Github`, `Google` accounts or register with an `email`.

_Make sure to chose a password with minimum 8 characters, containing upper and lowercase letters, at least one number and special character_

✅ **Step 1b. Create a "pay as you go" plan**

Follow this [guide](https://docs.datastax.com/en/astra/docs/creating-your-astra-database.html), to set up a pay as you go database with a free $25 monthly credit. You will find below recommended values to enter: 

- **For the database name** - `workshops`

- **For the keyspace name** - `stargate`

_You can technically use whatever you want and update the code to reflect the keyspace. This is really to get you on a happy path for the first run._

- **For provider and region**: Choose and provider (Gcp, Azure or AWS) and then the related region is where your database will reside physically (choose one close to you or your users).

- **Create the database**. Review all the fields to make sure they are as shown, and click the `Create Database` button.

You will see your new database `pending` in the Dashboard.

![my-pic](https://github.com/datastaxdevs/shared-assets/blob/master/astra/dashboard-pending-1000-update.png?raw=true)

The status will change to `Active` when the database is ready, this will only take 2-3 minutes. You will also receive an email when it is ready.

**👁️ Walkthough**

![image](images/tutorials/astra-create-db.gif?raw=true)


## 2. Create Table and load data


blob:https://astra.datastax.com/451e7171-467f-4d41-bedf-e70a699c21e0


## 3. Create Astra Token

To interact with our new created datasbase

✅ [Create a token for your app](https://docs.datastax.com/en/astra/docs/manage-application-tokens.html) to use in the settings screen

Copy the token value (eg `AstraCS:KDfdKeNREyWQvDpDrBqwBsUB:ec80667c....`) in your clipboard and save the CSV this value would not be provided afterward.

**👁️ Expected output**
![image](images/tutorials/astra-token.png?raw=true)


## 4. Launch Gitpod

[Gitpod](https://www.gitpod.io/) is an IDE 100% online based on Eclipse Theia. To initialize your environment simply click on the button below *(CTRL + Click to open in new tab)* You will be asked for you github account.

[![Open in Gitpod](https://gitpod.io/button/open-in-gitpod.svg)](https://gitpod.io/#https://github.com/datastaxdevs/workshop-spring-stargate)

**👁️ Expected output**

*The screenshot may be slightly different based on your default skin and a few edits in the read me.*

![TodoBackendClient](https://github.com/DataStax-Academy/workshop-spring-data-cassandra/blob/main/images/gitpod-home.png?raw=true)

You can be asked to import the project, please accept to have java features enabled for you project.

![TodoBackendClient](https://github.com/DataStax-Academy/workshop-spring-data-cassandra/blob/main/images/import.png?raw=true)

**That's it.** Gitpod provides all tools we will need today including `Maven` and exporting port `8080`. At initialization of the workspace we schedule a `mvn clean install` to download dependencies.

Also you may have noticed there is a build happening - even before we get started. The sample project already exists and loading the developer enviroment triggers a build to download all the maven dependencies so you don't have to.

## 2. Know your gitpod

The workshop application has opened with an ephemeral URL. To know the URL where your application endpoint will be exposed you can run the following command in the terminal:

```bash
gp url 8080
```

**👁️ Expected output**

![TodoBackendClient](https://github.com/DataStax-Academy/workshop-spring-data-cassandra/blob/main/images/gitpod-url.png?raw=true)

🚀 **Let's get starting**

To move to branch `PART1`, in a terminal use the following command. 

- You should read the instructions in gitpod now as moving to the next branch will update this README with the new instructions.

- When you move from one branch to another using checkout you will have the workspace populated with the solution. Your local changes will be lost.


```bash
git checkout PART1
```





In this repository, you'll find everything to have fun: working application, dataset, explanations, step by step to start the application, slides... 

<p align="left">
<a href="https://github.com/datastaxdevs/workshop-spring-stargate/wiki">
 <img src="https://dabuttonfactory.com/button.png?t=Let+us+get+started&f=Roboto-Bold&ts=26&tc=fff&hp=45&vp=20&c=11&bgt=unicolored&bgc=15d798" />
</a>
</p>