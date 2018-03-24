
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

## Deploying to Heroku

```sh
$ heroku create
$ git push heroku master
$ heroku open
```

## Deploying to AWS Elastic Beanstalk

```sh
$ lein ring uberjar
```

... TBD

## Deploying to Azure Web Apps

```sh
$ lein ring uberwar clojure-getting-started.war
$ zip deploy.zip target/clojure-getting-started.war
```

Upload the zip file to `https://<your-app-name>.scm.azurewebsites.net/ZipDeploy`

## Deploying to [Google App Engine](https://cloud.google.com/appengine/)

```sh
$ lein ring uberwar clojure-getting-started.war
$ unzip -d war target/clojure-getting-started.war
```

Edit `DATABASE_URL` environment variable in `war/WEB-INF/appengine-web.xml`: add `$DB_NAME`, `$CONNECTION_NAME`, `$DB_USER` and `$DB_PWD`.

```sh
$ appcfg.sh -A app-id -V app-version update war
$ appcfg.sh -A app-id -V app-version migrate_traffic war
```
