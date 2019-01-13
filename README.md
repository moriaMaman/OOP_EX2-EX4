Navigation

This project represents a general packman game. 

Description 

The Coords package implements elementary action on GPS points such as adding two points, find the azimuth and elevation, find the distance between two points and more. 

In addition, in the coords package we also have the map class. In that class we have function that convert a pixel on the map picture into a coordinate on the actual map in google earth. 

The algorithm package contain all programs of calculation, like the "ShortestPathAlgo" that calculate the optimal path for the packmans to eat all the fruits.

We also added an option in the game for the player to play by itself on the screen and eats the fruits. We used the Dijkstra algorithm to calculate the shortest path to a fruit. More explanation on that will be in our wiki file.

 For this option we created the class "pathAlgo" that do all the calculation, another class to deal with the boxes on the screen.
 
Another option of the game is for the user have its to play the game by itself.

the "gameElements" package contains the objects of the game such as packman, fruit and the game object itself which include a list of the fruits and a list of the packmans in the game also we have ghosts that chase the player.
The thread package help us to move the packmans on the screen.
The last package is the gui one, it contain all the design and the action that happens on the screen.

Authors 
Moria Maman and Atara Zohar. 

Acknowledgment 

•       multiCsv how to run over a folder recursively:
-   https://stackoverflow.com/questions/1844688/how-to-   read-all-files-in-a-folder-from-java  
•	How to get UTC:
https://stackoverflow.com/questions/26637168/how-to-convert-a-date-to-milliseconds    
•	Convert CSV file to KML
https://stackoverflow.com/questions/26637168/how-to-convert-a-date-to-milliseconds
•	Convert pixel to gps and gps to pixel-
https://stackoverflow.com/questions/38748832/convert-longitude-and-latitude-coordinates-to-image-of-a-map-pixels-x-and-y-coor 
•	load file
https://stackoverflow.com/questions/40255039/how-to-choose-file-in-java


Description 
•	GIS package Include 4 interfaces such as:  
1.	GIS element  
2.	GIS layer  
3.	GIS project
4.	GIS meta data  
•	Algorithm package include:
1.	Path2kml
2.	ShortestPathAlgo.
3.	Path
4.	Multicsv
5.	Closestfruit.
6.	ClosestFruitAlgo
7.	SortComperator
8.	boxElement
9.	PathAlgo
10.	sortComperator
11.	Comperator

•	gameElement:
1.	fruit
2.	packman
3.	game
4.	ghost
5.	player

•	cords:
1.	mycoodrs
2.	map
3.	pixel

•	gui:
1.	myframe

•	thread:
1.	packmanthread
2.	playerthread
•	File format 
      1. Csv2Kml
      2. CsvReader
•	Junit: 1. MyCoordsTest
                2. mapTest
