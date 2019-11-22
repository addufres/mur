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

### USER AUTHENTICATION / LOGIN
User Signup

	/api/auth/signup
  
User Signup Email Verification

	/api/auth/accountVerification/{token}
  
User Login

	/api/auth/login

### THREADS
Create Thread

	/api/thread/create

Get Thread

	/api/thread/query/{id}

Get All Threads

	/api/thread/query/all

Get All Posts in Thread

	/api/thread/{id}/posts/all

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

### COMMENTS
Create Comment

	/api/comments/create

Get Comments for a post

	/api/comments/query/{postId}

Get Comments for a user

	/api/comments/query/user/{username}

### VOTING
Vote

	/api/votes/vote

- UPVOTE Request value = 0
- DOWNVOTE Request value = 1

## DATA OBJECTS

### USER AUTHENTICATION / LOGIN

### THREADS

### POST

### COMMENT

### VOTE


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





