# Informatics 122 Final Project -- Client/Server Board Game
Developed/Designed by:
Max Paulus,
Max Kirchgesner,
Anping He,
Victor Stolle,
Carolyn Lai,
Daniel Albornoz,
Michael Vavro,
Jot Lambsar,
Nicholas Digeronimo

Professor Emily Navarro

## Assignment
Design and implement an extensible Board Game Server

## Requirements
- The Board Game Server should accommodate any board game that involves a grid layout and game elements on this layout, including games such as Chess, Checkers, Tic-Tac-Toe, Gomoku, **Connect Four**, Nine Men's Morris, **Chutes and Ladders**, Stratego, Shogi, Pente, **Battleship**…
- The Board Game Server should make it as easy as possible to create implementations of new games.
- The Board Game Server should provide a defined interface that all game plug-ins must follow.
- The Board Game Server should be a client-server Java application, not Web-based. However, the server can be a local server and have the different players all on one machine.
- The Board Game Server should provide one or more ways for people to find other players.
- The Board Game Server should support personal player profiles (the specifics of which are up to you). Login can be very simple, and does not have to be secure.
- The Board Game Server need only support 2-player games (but you can support more players if you want to).
- The Board Game Server should work by providing a player with a list of games they can play and allow them to choose which one to start.
- The Board Game Server games should be GUI-based.
- ~~The Board Game Server should be written in Java~~


## Grading Criteria
- **Stakeholder:** the player (how is the experience of playing a game?)
- **Stakeholder:** future developers of the Board Game Server (how is the understandability and quality of the code and design?)
- **Stakeholder:** game developers (how is the extensibility of the Board Game Server in supporting new board games? how is the experience of plugging in a new game?)
- **Stakeholder:** you (what are your contributions to the project?)

## To open/edit the UML diagram
1. go to the [google drive](https://drive.google.com/drive/u/1/folders/0APxwav_gZipYUk9PVA)
2. open the March_9_Deliverable folder
3. right click on netboard_uml.xml -> open with -> draw.io Diagrams

## DATA INTERCHANGE FORMAT
***Note:* this is important to understand. Please ask someone for clarification if you are confused.**
This is the JSON format in which the client and server will send their data to eachother:
There are 3 types of messages that will ever need to be sent between a client and a server:

1. The client wishes to host a game on the server

    ```javascript
    {
        messageType: "host",
        hostIP: "192.168.1.1",
        hostUsername: "darksteelknight",
        gameType: "connect4"
    }
    ```
    **Note:** if the server recieves a message of type "host", it will create a new ActiveGame of the chosen type and add `hostUsername` as one of the players of the game.
2. The client wishes to join a host's game as a guest

    ```javascript
    {
        messageType: "join",
        guestIP: "192.240.3.1"
        guestUsername: "desoron",
        hostUsername: "darksteelknight",
    }
    ```
    **Note:** this will set the `isFull` flag in the ActiveGame being hosted by `hostUsername`. This will also start the game between `guestUsername` and `hostUsername`
3. The client wishes to send a board update to the server or vice versa

    ```javascript
    {
        messageType: "boardupdate",
        gameState: {
            isConnected: True,
            turn: "darksteelknight",
            boardState: [
                char[][],
                char[][]
            ]
        }
    }
    ```
    **Note:** the `isConnected` flag will signal to the reciever that the game should still be running; nobody has expressed a desire to disconnect or stop the game.




