Movie Database v1.0
A minor side note before the instructison:
This project was written over the course of 2 months, mostly during my
WinterBreak between my Junior and Senior year while attending SUNY Albany.
It is written in Java but also includes usage of new JAR's and techniques 
including:
	SQLite Database
	JSON API
	Lambda Expressions
	UML Documentary
	Storyboard
	Design Docs
	HashMaps
The best way to learn is by doing! Also I wanted to take a second to 
dedicate this project to my beautiful, smart, funny wife Becky, who 
supported me through this whole project. Now on to the instructions and 
overview.

This program is a simple Movie database that takes advantage of the IMDB 
Database, via the OMDB.com API. It also allows custom movies and artwork
to be saved.

When the MovieDatabase.exe is run for the first time, it will create a 
Movie.db file, this is the database. Back it up! If its deleted and you
do not have a backup, then all the movies you added will be lost. 
(Import and Export feature will be added in a later version(See end of file
for more about future add on's))

To add a Custom Movie:
1)Click on the Movie menu option in the top left hand corner. 
2)Click add.
3)This will cause a box to popup with some fields to enter. If you would like
	to add a custom movie, fill in the data. The format of the data is as followed:
		Title, Director, Genre, and Plot can have any characters in it (Letter,
		number, special characters)
		Released must be in either:
			Day/Month/Year (01/01/2016)
			Day Month Year (01 Jan 2016)
		Rating must be purely numbers
		Runtime must include the word min with one space(110 min)
5)Clicking on the picture will popup a dialog box to choose your own picture.
6)After you have selected your picture in the popup dialog box, click ok. The
	boxart in the Add Window box will update.
7)Once the data is to your liking, click OK to add the movie. This will update
	the main screen, clear the Add Window popup box, and allow you to add another
	movie. Movies are added in Alphabetical order.

To add a Movie from the database:
1)Click on the Movie menu option in the top left hand corner.
2)Click add.
3)Click Search.
4)Click Search...again. This will popup a new Window that is used to search movies.
	You can search movies through two options:
		By Movie title. If the movie is misspelled or it cant find the movie it will
			return an error. Also if the movie you were looking for dosent come back
			then you would use the...
		By IMDB id. This can be found on the imdb website in the URL. It usually begins
			with tt (Top Gun: tt0092099) and comes after imdb.com/title/'IMDB id'. This
			will return the movie from the IMDB database.
5)Once you click OK or hit enter, the Add Window box fills in with data. This data can
	be edited at this point. Some movies may be missing data and require filling in.
6)Same instructions as Steps 3-7 follows from the Custom Movie instructions.

To Modify an entry:
1)Click on the box art on the Main Window.
2)This will popup the modify window.
3)Follow steps 3-6 from the Custom Movie instructions.
4)Then click modify. This will change the data in the database and the Main Window
	and close the window.
5)If you change any data and click the X, a pop up will display to ask if you want
	to save the data.

To Delete an entry:
1)Steps 1-2 from the Modify an entry instructions
2)Click Delete. A popup will display asking if you want to delete. 
3)If you delete the entry, it will update the Main Window and close the modify window.

And thats really it. 
Future wishlist:
	Change order of movies by drag an drop
	Detect duplicates
	Get movies based on certain critera
	Video game support
	Import and Export database
	And much more!
	
Questions? Comments? Concerns? Wishlist? Advice? Food order? 
Email: n.gottschalt@gmail.com
	