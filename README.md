
# clojure-getting-started

An example Clojure web app that
* uses a Postgresql database via Hugsql
* can be easily deployed to any cloud PaaS:
  * [Heroku](https://www.heroku.com)
  * [AWS Elastic Beanstalk](https://aws.amazon.com/elasticbeanstalk/)
  * [Azure App Services](https://azure.microsoft.com/en-us/services/app-service/web/)
  * [Google App Engine](https://cloud.google.com/appengine/)


This application originates from Heroku's [Getting Started with Clojure](https://devcenter.heroku.com/articles/getting-started-with-clojure) article.

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

1. Configure [EB CLI to deploy an artifact](https://docs.aws.amazon.com/elasticbeanstalk/latest/dg/eb-cli3-configuration.html#eb-cli3-artifact)

2. Run:
```sh
$ lein ring uberjar
$ eb deploy
```

## Deploying to Azure App Service

1. Run:
```sh
$ lein ring uberwar ROOT.war
$ mkdir webapps
$ cp target/ROOT.war webapps
$ zip -r deploy.zip webapps
```

2. Upload the zip file to `https://<your-app-name>.scm.azurewebsites.net/ZipDeploy`.

3. In the deployment portal, remove `webapps/ROOT` directory.

## Deploying to Google App Engine

1. Run
```sh
$ git checkout google-app-engine
$ lein ring uberwar clojure-getting-started.war
$ unzip -d war target/clojure-getting-started.war
```

2. Edit `DATABASE_URL` environment variable in `war/WEB-INF/appengine-web.xml`: add `$DB_NAME`, `$CONNECTION_NAME`, `$DB_USER` and `$DB_PWD`.

3. Run
```sh
$ appcfg.sh -A app-id -V app-version update war
$ appcfg.sh -A app-id -V app-version migrate_traffic war
```


## License

Licensed with [Eclipse Public License v1.0](http://www.eclipse.org/legal/epl-v10.html).

## Thanks

This project is a grateful recipient of the [Futurice Open Source sponsorship program](http://futurice.com/blog/sponsoring-free-time-open-source-activities?utm_source=github&utm_medium=spice).
