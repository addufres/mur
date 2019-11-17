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


## DATA OBJECTS
