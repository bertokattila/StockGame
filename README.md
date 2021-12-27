

# Stock game <img width="60" align="left" alt="Screenshot 2021-12-26 at 17 18 10" src="https://user-images.githubusercontent.com/22593928/147414216-3f0a0a99-4232-43f7-b500-123d3916f310.png">

This was my homework for a third semester university course object-oriented programming in Java. The following documentation was created as part of the assignment, it doesn't serve any purpose other than satisfying the instructions given by the professor.

<img width="1920" alt="Screenshot 2021-12-26 at 15 50 09" src="https://user-images.githubusercontent.com/22593928/147411736-3136ad57-96ea-4eeb-a728-e9c9dad34f88.png">

As discussed in the consultation I separated the class diagram into two parts to keep it simple. The first diagram shows the business logic classes, while the second shows the user interface classes.

<img width="1636" alt="Screenshot 2021-12-26 at 17 43 05 copy" src="https://user-images.githubusercontent.com/22593928/147415483-ab534a36-d34c-4167-b483-925c3880723b.png">
 
<img width="1441" alt="Screenshot 2021-12-26 at 18 19 55" src="https://user-images.githubusercontent.com/22593928/147415510-f756b157-b0a7-4bee-88dc-4c6d28d21b79.png">

## User manual and screenshots

### 1. **Creating a new game** 

After launching the game you should see something like this: 

<img width="1162" alt="Screenshot 2021-12-26 at 15 32 14" src="https://user-images.githubusercontent.com/22593928/147411227-1c198e94-01a1-47fc-aece-b5005300aa82.png">

Select the **New game** option in the **Game** menu to get a pop-up where you can give a nickname.

<img width="408" alt="Screenshot 2021-12-26 at 15 32 57" src="https://user-images.githubusercontent.com/22593928/147411270-e659c23b-51c8-4e1b-867e-0e232ddf3c28.png">

Click on **OK** and the game starts.

### 2. **Displaying charts** 

The graph of each stock can be displayed with the corresponding **Show chart** button.

<img width="1162" alt="Screenshot 2021-12-26 at 15 36 16" src="https://user-images.githubusercontent.com/22593928/147411366-7779cc5f-fea6-45cf-bd3f-eb6a83723a1b.png">

The corresponding graph will appear in a new window. The graphs are displayed in separate windows and they're being constantly updated. Pressing the **Show chart** button again will bring the focus back to the graph of that stock. These windows can be resized, therefore you can create a custom view with ideal screen usage, even for multiple displays.

JFreeChart provides some additional useful features: you can zoom in or out and you can even create a PNG images of them. You can access these functions by right-clicking on the graph.
<img width="672" alt="Screenshot 2egt021-12-26 at 15 54 19" src="https://user-images.githubusercontent.com/22593928/147412138-995ad041-01ab-4d98-963b-8c42332d9836.png">

### 3. **Creating a position** 

If you speculate that the price of a particular stock will increase, you must choose the **Long** button, if you speculate for decrease, choose the **Short** button.

In the window that appears, enter the amount of money you'd like to use to create the position. If this amount exceeds the available capital, the program will not allow the transaction, otherwise the following window will pop up in which you can enter the leverage you want to use:

<img width="408" alt="Screenshot 2021-12-26 at 16 05 34" src="https://user-images.githubusercontent.com/22593928/147412460-30b1356d-ee5d-4c9b-bba3-790e3a6547fe.png"><img width="480" alt="Screenshot 2021-12-26 at 16 17 25" src="https://user-images.githubusercontent.com/22593928/147412463-a962b0ef-333b-4f24-8b1b-448fd8a5ec5f.png">

The created position can now be found on the **Active Positions** page.

### 4. **Selling a position** 

On the **Active Positions** page, each position has a **Sell** button.

<img width="1162" alt="Screenshot 2021-12-26 at 16 23 04" src="https://user-images.githubusercontent.com/22593928/147412600-01b8222b-ca64-4cdd-a3fd-c8d96c1a109b.png">

The **Current value** parameter shows how much money you would receive if you sold the position at that moment.

Certain types of positions may loose their whole value. This includes all SHORT type positions as well as any position with leverage greater than 100%. If this scenario occurs, the position gets automatically closed and moved to the **Closed positions** page.

### 5. **Save game** 

To save, select **Save game** option in the **Game** menu and the following window will appear:

<img width="654" alt="Screenshot 2021-12-26 at 16 28 02" src="https://user-images.githubusercontent.com/22593928/147412713-b1585e20-57a0-47e0-ad19-f41f045d3971.png">

Here you can select the saving location as well as enter the file name, which is the player's name by default. The extension must be .ser otherwise the game throws a warning:

<img width="486" alt="Screenshot 2021-12-26 at 16 29 00" src="https://user-images.githubusercontent.com/22593928/147412751-87cdf0b1-c527-4889-9681-a73b733f1696.png">

### 6. **Opening a saved game** 

It works like saving: In the **Game** menu, select **Open game**, which will open the following window:

<img width="654" alt="Screenshot 2021-12-26 at 17 06 36" src="https://user-images.githubusercontent.com/22593928/147413785-7dc2aacd-65ab-432e-83f5-cf742fcbf8cb.png">

Here you can load files with a .ser extension, but only those created by this game. Otherwise, the loading will fail and you will receive the following error message:

<img width="409" alt="Screenshot 2021-12-26 at 17 18 10" src="https://user-images.githubusercontent.com/22593928/147414052-98f41c16-3690-4630-8a98-fddc66a11b13.png">

## **External libraries used** 

- [JFreeChart](https://www.jfree.org/jfreechart/)
- [Darcula look and feel](https://github.com/bulenkov/Darcula)

 Tesing: 

- [JUnit](https://junit.org/junit5)

## **The icon** 

I created it by modifying one of the free icons on [Flaticon](https://www.flaticon.com). It is located in the **img** folder of the project.

<img width="90" alt="Screenshot 2021-12-26 at 17 18 10" src="https://user-images.githubusercontent.com/22593928/147414216-3f0a0a99-4232-43f7-b500-123d3916f310.png">

## **Classes, data structures** 

I organized the classes into 3 packages according to their role. Each class (except Main) is a member of a package. The descriptions of the classes are included in the source code as JavaDoc comments.

**Main** 

Creates and displays the main window. 

### **Game package** 
**Game** 

It represents a gameplay, stores the shares, their value history, and the player object associated with the gameplay. Its most important role is to allow gameplays to be “attached” to the gui. This plays an importatnt a role in opening and saving games. (See **Save game** section)

**Player** 

Represents a player, stores its name, capital, active and closed positions.

**Stock** 

Represents a stock, stores its name, value, and previous values.

**Position** 

Represents a position. It has its own unique id, it knows the stock which it was created from, so it can update its own value.

Contains the definition of **PositionType enum**, which can be SHORT or LONG.

Indicates its type with a PositionType attribute. It also stores the value of the leverage and whether it is still active.

### **Gui package** 
**Frame** 

The class that implements the main window, since there is only one in the program, most of its attributes and functions are static.

**StockPanel** 

A GUI element that represents a stock, its current value, the buttons you can use to manage it and display its graph.

- **PositionPanel** 

A GUI element that represents a position. Its behavior depends on the status of the position, for example, it has a Sell button only for active positions.

- **StockChart** 

Responsible for displaying the graph for a given stock. It utilizes the JFreeChart library.

- **NewGameActionListener** 

A custom event listener type that is responsible for creating a new game.

- **OpenActionListener** 

A custom event listener type that implements the opening of a saved gameplay.

- **SaveActionListener** 

A custom event listener type that implements the saving of the current gameplay.

- **MakePositionActionListener** 

A custom event listener type that initiates the creation of a position from the associated stock.

- **SellPositionActionListener** 

A custom event listener type that is responsible for selling a given stock.

- **ShowStockChartActionListener** 

A custom event listener type that initiates the displaying of the correspondent stocks graph.

### **Exceptions package** 

- **NotEnoughFundException** 

Custom exception type which is thrown when a player tries to create a position without enough capital.

**Saving** 

The current gameplay can be saved using standard Java serialization. I designed the class structure the way that only the serialization of a single **Game** object is needed during this process.

The goal was to be able to reproduce the current state of the gameplay with 100% accuracy from the resulting file, while keeping the file size to a minimum.

The game object directly contains the stocks, including their previous values, as well as the associated **Player** object. The player contains its name, capital, active and closed positions. It is important that GUI objects are left out of serialization, as they only graphically represent business-logic objects, which can easily be rebuilt after loading, hence saving them would be unnecessary redundancy.

As a result, the size of a multi-hour gameplay is only a few hundred KB.

Savings are given a .ser extension, which has no special role, but is useful, because it indicates that the file is a serialized object.
