## BotWars 2020 - SamuraiJack version 1.0
* [General info](#general-info)
* [Technologies](#technologies)
* [Contact](#Contact) 
* [Next Steps](#next-steps)

## General info
This project is simple bot named SamuraiJack. Main purpose of my 'samurai' is collecting flag in BotWars Competition organized by Sapiens. Because of it is tournament organized only for members of organization, unfortunetly i cannot desribe more details about rules, prizes and in general "how to" this competition. 

## Technologies 
In general, my basic tools to develope software in work is Java. So, the first choice technology has been SpringBoot. As You can see, SpringBoot has been used only to create rest api, communication with game server. 
Futhermore, in project I tried to develop my skills in Java 8 Streams API, so instead of using basic operators from older than 8 version of Java i decided to choose streams. Moreover, in a few line of code I was looking for a possibilities to use functionality from java.util.function package. 

To cast JSON request into GameRequestObject I used Jackson as a simplest way to reading info from game server. I realized response in similar way.

The obvious approch was integrate game with simple datebase ( such as SQLite or some no-sql db like mongodb ), but unfortunately i didn't have enough time to do it ( about 2 evenings), so i create 3 or 4 static variables to read previous state of game.

## Next Steps
So, my desire is to complete project into satisfying shape, so I would like to add some feature :
* add connect with db 
* handle unsatisfying scenario 
* .. in general proper solution to win competition is implement some things in deep learning network

## Contact

If You want to get to know smth more, and i.g. test api, send me priv message -> [messeger](m.me/krowytezludzie)
