# mur

### Required Dependecies
Lombok, Spring Web, Spring Security, Spring Data JPA, MySQL Java Driver, Java Mail Sender

### Keygen
keytool -genkey -alias mur -keyalg RSA -keystore mur.jks -keysize 2048

mur	mur.jks

## USERS
USERNAME	PASSWORD

MurAdmin / thats1lol

MurMod / thats2lols

testUser1 / password

testUser2 / password

## API Paths
### LOGIN AND USER REG
User Signup

	/api/auth/signup
  
User Signup Email Verification

	/api/auth/accountVerification/{token}
  
User Login

	/api/auth/login

### POSTS 
Create Post

	/api/posts/create
  
Get All Posts

	/api/posts/query/all
  
Get Single Post By PostID

	/api/posts/query/{id}
  
Get All Posts By Thread

	/api/posts/query/all/thread/{threadId}
  
Get All Posts By Username

	/api/posts/query/user/{name}

### THREADS
Create Thread

	/api/thread/create

Get Thread

	/api/thread/query/{id}

Get All Threads

	/api/thread/query/all

Get All Posts in Thread

	/api/thread/{id}/posts/all

## DATA OBJECTS

### USER REGISTRATION
Registration requests sends username, password and email in REQUEST BODY

Once user receives activation email and clicks validation link the RESPONSE BODY contains username and authenticationToken

Login requests send off username and password in REQUEST BODY

### THREAD INTERACTION
Needs testing still
### POST INTERACTION
Needs testing still
### COMMENT INTERACTION
Needs to be built still

### Stories still to complete...

- add api query for getting thread by name... duh how did I forget this already....

- Voting on posts

- Friend lists

- User messaging between users.

- thread moderators and admins

- front end would be nice =D





