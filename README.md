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

## NetBoard Basics (Start Here)
There are two main components to the NetBoard application

1. **NetBoardServer:** this component holds majority of the game logic and implementation
    - `NetBoardServer` is the entry point for the server-side application.
    - When `NetBoardServer` starts, it basically sits and waits for clients using the `listenForConnections()` method.
    - **LobbyThread:** Once a connection is made with a client, a new `LobbyThread` object is spawned for the purpose of handling the client's actions while he/she is in the lobby.
        - If client A wishes to "host" a game, the `LobbyThread` in charge of handling the client will add the client to `NetBoardServer`'s `playerLobby` list. At this point the `LobbyThread` object will die.
    - **ActiveGameThread:** Once client B comes along expressing a desire to "join" client A's game, a new `ActiveGameThread` object is spawned with client A and client B as the two players. Client A is removed from `NetBoardServer`'s `playerLobby`.
        - From this point on, the `ActiveGameThread` object is responsible for coordinating messages between client A and client B
        - **Game:** An `ActiveGameThread` will have a `Game` object which represents the game being played.
        - **Logic:** A `Game` will have a `Logic` object which dictates the rules of the game.
2. **NetBoardClient:** 
    - `NetBoardClient` is an extremely thin client because most of the logic will be handled on the serverside.
    - Will most probably just take care of the GUI as well as initial connection to server.

## DATA INTERCHANGE PROTOCOL
***Note:* this is important to understand. Please ask someone for clarification if you are confused.**
This is the JSON format in which the client and server will send their data to eachother.
There are 4 types of messages that will ever need to be sent between a client and a server:

1. The client wishes to host a game on the server:

    ```javascript
    {
        messageType: "host",
        hostIP: "192.168.1.1",
        hostUsername: "darksteelknight",
        gameType: "connect4"
    }
    ```
    - **Note:** if the `LobbyThread` recieves a message of type "host", it will add `darksteelknight` to `NetBoardServer`'s player lobby.
2. The client wishes to refresh the game lobby:

    ```javascript
    {
        messageType: "refresh"
    }
    ```
3. The client wishes to join a host's game as a guest:

    ```javascript
    {
        messageType: "join",
        guestIP: "192.240.3.1"
        guestUsername: "desoron",
        hostUsername: "darksteelknight",
    }
    ```
    - **Note:** if `darksteelknight` exists in the player lobby, this will spawn an `ActiveGameThread` between `desoron` and `darksteelknight`. This will also remove `darksteelknight` from the player lobby.
4. The client wishes to send a board update to an ActiveGameThread or vice versa:

    ```javascript
    {
        messageType: "boardupdate",
        gameState: {
            isValid: True,
            isConnected: True,
            turn: "darksteelknight",
            boardState: [
                char[][],
                char[][]
            ]
        }
    }
    ```
    - **Note:** the `isValid` flag will indicate whether the previously submitted move is a valid move. If this is set to false, an error message should pop up on the client.
    - **Note:** the `isConnected` flag will signal to the reciever that the game should still be running; nobody has expressed a desire to disconnect or stop the game. 
    - **Note:** `boardState is actually a list of boards (char[][]), because some game types might require more than one board.

## Example 2-player connect sequence:

![imgur_sequence_diagram](http://i.imgur.com/ylpY6eN.png "2-player sequence diagram")

## Requirements
- The Board Game Server should accommodate any board game that involves a grid layout and game elements on this layout, including games such as Chess, **Checkers**, Tic-Tac-Toe, Gomoku, **Connect Four**, Nine Men's Morris, Chutes and Ladders, Stratego, Shogi, Pente, **Battleship**…
- The Board Game Server should make it as easy as possible to create implementations of new games.
- The Board Game Server should provide a defined interface that all game plug-ins must follow.
- The Board Game Server should be a client-server Java application, not Web-based. However, the server can be a local server and have the different players all on one machine.
- The Board Game Server should provide one or more ways for people to find other players.
- The Board Game Server should support personal player profiles (the specifics of which are up to you). Login can be very simple, and does not have to be secure.
- The Board Game Server need only support 2-player games (but you can support more players if you want to).
- The Board Game Server should work by providing a player with a list of games they can play and allow them to choose which one to start.
- The Board Game Server games should be GUI-based.
- The Board Game Server should be written in Java


## Grading Criteria
- **Stakeholder:** the player (how is the experience of playing a game?)
- **Stakeholder:** future developers of the Board Game Server (how is the understandability and quality of the code and design?)
- **Stakeholder:** game developers (how is the extensibility of the Board Game Server in supporting new board games? how is the experience of plugging in a new game?)
- **Stakeholder:** you (what are your contributions to the project?)

## To open/edit the UML diagram
1. go to the [google drive](https://drive.google.com/drive/u/1/folders/0APxwav_gZipYUk9PVA)
2. open the March_14_Deliverable folder
3. right click on netboard_uml.xml -> open with -> draw.io Diagrams

