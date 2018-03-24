
# clojure-getting-started

An example Clojure web app that
* uses a Postgresql database via Hugsql
* can be easily deployed to any cloud PaaS:
 * [Heroku](https://www.heroku.com)
 * [AWS Elastic Beanstalk](https://aws.amazon.com/elasticbeanstalk/)
 * [Azure Web Apps](https://azure.microsoft.com/en-us/services/app-service/web/)


This application support the [Getting Started with Clojure](https://devcenter.heroku.com/articles/getting-started-with-clojure) article - check it out.

## Configuration

Environment variables

- `PORT` the port for the web server
- `DATABASE_URL` DB connection URI, e.g. `"postgresql://foo:bar@localhost:5432/test"`

## Running Locally

Make sure you have Leiningen installed.

```sh
$ git clone https://github.com/ykarikos/clojure-getting-started.git
$ cd clojure-getting-started
$ lein ring server-headless
```

Your app should now be running on [localhost:3000](http://localhost:3000/).

## Deploying to [Heroku](https://www.heroku.com/)

```sh
$ heroku create
$ git push heroku master
$ heroku open
```

## Deploying to [AWS Elastic Beanstalk](https://aws.amazon.com/elasticbeanstalk/)

1. Configure [EB CLI to deploy an artifact](https://docs.aws.amazon.com/elasticbeanstalk/latest/dg/eb-cli3-configuration.html#eb-cli3-artifact)

2. Run:
```sh
$ lein ring uberjar
$ eb deploy
```

## Deploying to [Azure App Service](https://azure.microsoft.com/en-us/services/app-service/)

1. Run:

```sh
$ lein ring uberwar ROOT.war
$ mkdir webapps
$ cp target/ROOT.war webapps
$ zip -r deploy.zip webapps
```

2. Upload the zip file to `https://<your-app-name>.scm.azurewebsites.net/ZipDeploy`.

3. In the deployment portal, remove `webapps/ROOT` directory.
