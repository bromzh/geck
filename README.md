# Geck
<dfn>Geck</dfn> - is a demo JavaEE Application.

Includes:

 * JPA example (2 entities with simple mapping, persistence via JTA, simple DAO layer, lazy fetching)
 * JAX-RS example

# Requirements

 1. Java SDK 8
 2. Maven
 3. WildFly Application Server
 4. python 2.6+ and pygmentize (for pretty json output)

# Install and run application
## 0. Install WildFly App Server

```bash
$ wget http://download.jboss.org/wildfly/8.2.0.Final/wildfly-8.2.0.Final.zip
$ unzip wildfly-8.2.0.Final.zip
$ sudo ln -s `pwd`/wildfly-8.2.0.Final /opt/wildfly
$ cd /opt/wildfly/bin
$ ./add-user.sh
```

## 1. Run WildFly

```bash
$ cd /opt/wildfly/bin
$ ./standalone.sh
```

Admin console available on http://localhost:9990/

## 2. Run application
In a new terminal session:

```bash
$ git clone https://github.com/bromzh/geck.git
$ cd geck
$ mvn clean install
$ mvn wildfly:deploy
```

Go to http://localhost:8080/geck/

# Testing REST API
## User CRUD
### Create new user

```bash
$ curl -s -H "Content-Type: application/json" \
    -X POST \
    -d '{"name": "user1", "age": 42, "email": "user1@example.com"}' \
    http://localhost:8080/geck/rest/users/ \
    | python -m json.tool | pygmentize -l json
```

```json
{
    "age": 42,
    "email": "user1@example.com",
    "id": 1,
    "name": "user1"
}
```

Add another user:

```bash
$ curl -s -H "Content-Type: application/json" \
    -X POST \
    -d '{"name": "user2", "age": 27, "email": "user2@example.com"}' \
    http://localhost:8080/geck/rest/users/ \
    | python -m json.tool | pygmentize -l json
```

```json
{
    "age": 27,
    "email": "user2@example.com",
    "id": 2,
    "name": "user2"
}
```

### Get users list

```bash
$ curl -s http://localhost:8080/geck/rest/users/ \
    | python -m json.tool | pygmentize -l json
```

```json
[
    {
        "age": 42,
        "email": "user1@example.com",
        "id": 1,
        "name": "user1"
    },
    {
        "age": 27,
        "email": "user2@example.com",
        "id": 2,
        "name": "user2"
    }
]
```

### Get user by id

```bash
$ curl -s http://localhost:8080/geck/rest/users/1 \
    | python -m json.tool | pygmentize -l json
```

```json
{
    "age": 42,
    "email": "user1@example.com",
    "id": 1,
    "name": "user1"
}
```

### Change user data

```bash
$ curl -s -H "Content-Type: application/json" \
    -X PUT \
    -d '{"name": "user1", "age": 43, "email": "user1@example.com"}' \
    http://localhost:8080/geck/rest/users/1 \
    | python -m json.tool | pygmentize -l json
```

```json
{
    "age": 43,
    "email": "user1@example.com",
    "id": 1,
    "name": "user1"
}
```

### Delete user

```bash
$ curl -s -X DELETE http://localhost:8080/geck/rest/users/2
```

```bash
$ curl -s http://localhost:8080/geck/rest/users/ \
    | python -m json.tool | pygmentize -l json
```

```json
[
    {
        "age": 43,
        "email": "user1@example.com",
        "id": 1,
        "name": "user1"
    }
]
```

## Post CRUD
### Create new post

```bash
$ curl -s -H "Content-Type: application/json" \
    -X POST \
    -d '{"title": "Post 1", "text": "This is a first post", "owner": {"id": 1}}' \
    http://localhost:8080/geck/rest/posts/ \
    | python -m json.tool | pygmentize -l json
```

```json
{
    "id": 3,
    "owner": {
        "age": 43,
        "email": "user1@example.com",
        "id": 1,
        "name": "user1"
    },
    "text": "This is a first post",
    "title": "Post 1"
}
```

### Get posts list

```bash
$ curl -s http://localhost:8080/geck/rest/posts/ \
    | python -m json.tool | pygmentize -l json
```

```json
[
    {
        "id": 3,
        "owner": {
            "age": 43,
            "email": "user1@example.com",
            "id": 1,
            "name": "user1"
        },
        "text": "This is a first post",
        "title": "Post 1"
    }
]
```

### Delete user and his posts

```bash
$ curl -s -X DELETE http://localhost:8080/geck/rest/users/1
```

```bash
$ curl -s http://localhost:8080/geck/rest/users/ \
    | python -m json.tool | pygmentize -l json
```

```json
[]
```

```bash
$ curl -s http://localhost:8080/geck/rest/posts/ \
    | python -m json.tool | pygmentize -l json
```

```json
[]
```
