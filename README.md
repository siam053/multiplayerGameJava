# MultiplayerGameJava

Battle Royale Simplified

Server Side:
Server will start from 'ServerWindow' Class. After starting server it will wait about 10 seconds for
adding clients. The work of accepting new client is done by 'ClientAdder'. For each client server
will create a sender thread and a receiver thread. Receiver thread will receive data as string . Then it
will process data and update the players of server side. Sender thread will create data from players
in scene and send it to corresponding client.

Client Side:
Client will start from 'MainWindow' Class. After entering name a file will be opened using his name
if he is a new user (it is not case sensitive). In this file Career result of clients are stored. after
selecting play game button a field will appear for inserting server ip. after connecting to server it
will wait for 15 seconds for getting data of all players and then will show in scene. For each time
refreshing it will send data to server as string. and client reciever will receive data as object. in
client side it will be updated using players playerdata each time refreshed. By pressing space bullets
will be shooted. Then if it hits any other player health of the player will decrease. game will be over
if health is 0. after game over score of file will be updated in GameWindow.

The game is not yet completed. It'll be completed soon.

Project Members: 1. Wakil Islam (wakilislam.24csedu.026@gmail.com)
                 2. Siam Ahmed (siam.24csedu.053@gmail.com)
                 3. Ather Nur Kaushitk (kaushik10762@gmail.com)
                 
Project Mentor: Hasnain Heickal 
                Assistant Professor, Dept. of Computer Science and Engineering, University of Dhaka
                Mail:  hasnain@cse.du.ac.bd
