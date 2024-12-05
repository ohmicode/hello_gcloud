# gcloud appengine hello_world

with Objectify ORM to access Firestore

### setup
~~~
gcloud auth application-default set-quota-project project-id
gcloud config set project project-id
gcloud app regions list
gcloud app create --project=project-id --region=europe-west
~~~

### services to enable
~~~
gcloud services list
gcloud services enable servicemanagement.googleapis.com
gcloud services enable servicecontrol.googleapis.com
~~~

### build and deploy
~~~
mvn clean package
mvn endpoints-framework:openApiDocs
nano target/openapi-docs/openapi.json
gcloud endpoints services deploy target/openapi-docs/openapi.json
mvn appengine:deploy
~~~

### run locally
~~~
gcloud auth application-default login
export ENDPOINTS_SERVICE_NAME=project-id.appspot.com
export GOOGLE_CLOUD_PROJECT=project-id
mvn appengine:run
~~~
