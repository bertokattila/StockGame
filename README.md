

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

Select the **New game** option in the **Game** menu to get a pup-up where you can give a nickname.

<img width="408" alt="Screenshot 2021-12-26 at 15 32 57" src="https://user-images.githubusercontent.com/22593928/147411270-e659c23b-51c8-4e1b-867e-0e232ddf3c28.png">

Click on **OK** and the game starts.

### 2. **Displaying charts** 

The graph of each stock can be displayed with the corresponding **Show chart** button.

<img width="1162" alt="Screenshot 2021-12-26 at 15 36 16" src="https://user-images.githubusercontent.com/22593928/147411366-7779cc5f-fea6-45cf-bd3f-eb6a83723a1b.png">

The corresponding graph will appear in a new window. The graph of each stock is displayed in a separate window and they're being constantly updated. Pressing the **Show chart** button again will bring the focus back to the graph of that stock. These windows can be resized, creating a custom view with ideal screen usage, even for multiple displays.

JFreeChart provides some additional useful features: you can zoom in on graphs, but you can even create a PNG images of them, and so on. You can access these functions by right-clicking on the graph.
<img width="672" alt="Screenshot 2egt021-12-26 at 15 54 19" src="https://user-images.githubusercontent.com/22593928/147412138-995ad041-01ab-4d98-963b-8c42332d9836.png">

### 3. **Creating a position** 

If you speculate that the price of a particular stock will increase, you must choose the **Long** button, if you speculate for decrease, choose the **Short** button.

In the window that appears, enter the amount of money you'd like to use to create the position. If this amount exceeds the available capital, the program will, of course, not allow the transaction, otherwise the following window will pop up in which you can enter the leverage you want to use:

<img width="408" alt="Screenshot 2021-12-26 at 16 05 34" src="https://user-images.githubusercontent.com/22593928/147412460-30b1356d-ee5d-4c9b-bba3-790e3a6547fe.png"><img width="480" alt="Screenshot 2021-12-26 at 16 17 25" src="https://user-images.githubusercontent.com/22593928/147412463-a962b0ef-333b-4f24-8b1b-448fd8a5ec5f.png">

The created position can now be viewed on the **Active Positions** page.

### 4. **Selling a position** 

On the **Active Positions** page, each position has a **Sell** button that can be used to sell.

<img width="1162" alt="Screenshot 2021-12-26 at 16 23 04" src="https://user-images.githubusercontent.com/22593928/147412600-01b8222b-ca64-4cdd-a3fd-c8d96c1a109b.png">

The **Current value** parameter shows how much money you would receive if you sold the position at that moment.

Certain types of positions may loose their whole value. This includes all SHORT type positions as well as any position with leverage greater than 100%. If this scenario occurs, the position gets automatically closed and moved to the **Closed positions** page.

### 5. **Save game** 

To save, select **Save game** option in the **Game** menu and the following window will appear:

<img width="654" alt="Screenshot 2021-12-26 at 16 28 02" src="https://user-images.githubusercontent.com/22593928/147412713-b1585e20-57a0-47e0-ad19-f41f045d3971.png">

Here you can select the saving location as well as enter the file name, which is the player's name by default. The extension must be .ser, otherwise the game throws a warning:

<img width="486" alt="Screenshot 2021-12-26 at 16 29 00" src="https://user-images.githubusercontent.com/22593928/147412751-87cdf0b1-c527-4889-9681-a73b733f1696.png">

### 6. **Opening a saved game** 

It works like saving: In the **Game** menu, select **Open game**, which will open the following window:

<img width="654" alt="Screenshot 2021-12-26 at 17 06 36" src="https://user-images.githubusercontent.com/22593928/147413785-7dc2aacd-65ab-432e-83f5-cf742fcbf8cb.png">

Here you can load files with a .ser extension, but only those created by this game. Otherwise, the load will fail and you will receive the following error message:

<img width="409" alt="Screenshot 2021-12-26 at 17 18 10" src="https://user-images.githubusercontent.com/22593928/147414052-98f41c16-3690-4630-8a98-fddc66a11b13.png">

**External libraries used** 

- [JFreeChart](https://www.jfree.org/jfreechart/)
- [Darcula look and feel](https://github.com/bulenkov/Darcula)

Tesing: 

- [JUnit](https://junit.org/junit5)

**The icon** 

I created it by modifying one of the free icons on [Flaticon](https://www.flaticon.com). It is located in the **img** folder of the project.

**Classes, data structures** 

I organized the classes into 3 packages according to their role. Each class (except Main) is a member of a package. The description of the classes is included in the source code as JavaDoc comments.

**Main** 

Creates and displays the main window. 

- **Game package** 
- **Game** 

Egy jétékmenetet reprezentál, tárolja a részvényeket, azok értékelőzményeit, valamint a játékmenethez tartozó játékost (Player). Legfontosabb szerepe, hogy lehetővé teszi, játékmenetek “csatolását” a gui-hoz, aminek a mentés és mentett játék betöltésénel van szerepe. (Lásd. Mentések fejezet) 

**Player** 

Egy játékost reprezentál, tárolja annak nevét, tőkéjét, aktív és már lezárt pozícióit. 

- **Stock** 

Egy részvényt reprezentál, tárolja a nevét értékét, és korábbi értékeit (így grafikonon lehet ábrázolni) 

- **Position** 

Egy pozíciót reprezentál. A megkülönboztetés segítésére van saját egyedi id-ja, ismeri a részvényt, amiből létrejött, így tudja frissíteni az értékét. 

Tartalmazza a **PositionType enum** definícióját, aminek értéke lehet SHORT vagy LONG. 

Egy ilyen PositionType attribútimmal indikálja a típusát. Továbbá tárolja a tőkeáttét értékét, illetve azt, hogy aktív-e még.

- **Gui package** 
- **Frame** 

A központi ablakot megvalósító osztály, mivel a programban egyetlen ilyen van, a legtöbb attribúma és függvénye static. Tartalmazza  

- **StockPanel** 

GUI-elem, ami ábrázol egy részvényt, annak aktuális értékét, a gombokat, amikkel kezelni lehet, valamint amivel meg lehet jeleníteni a grafikonját. 

- **PositionPanel** 

GUI-elem, ami megvalósítja egy pozíció ábrázolását. Viselkedése függ a pozíció állapotától is, például csak aktív pozícióknál van Sell button, stb. 

- **StockChart** 

Osztály, ami megvalósítja egy részvény árfolyam-változásának ábrázolását. Ehhez felhasználja a JFreeChart library-t. 

- **NewGameActionListener** 

Saját esemény-figyelő típus, ami megvalósítja egy új játék tétrehozását. 

- **OpenActionListener** 

Saját esemény-figyelő típus, ami kezdeményezi egy mentett játékmenet betöltését. 

- **SaveActionListener** 

Saját esemény-figyelő típus, ami kezdeményezi az aktuális játékmenet elmentését. 

- **MakePositionActionListener** 

Saját esemény-figyelő típus, ami kezdeményezi a hozzá tartozó részvényből pozíció létrehozását. 

- **SellPositionActionListener** 

Saját esemény-figyelő típus, ami kezdeményezi a hozzá tartozó pozíció eladását. 

- **ShowStockChartActionListener** 

Saját esemény-figyelő típus, ami megjleneníti a hozzá tartozó részvény grafikonját. 

- **Exceptions package** 
- **NotEnoughFundException** 

Saját kivétel típus, ami akkor dobódik, ha úgy probál a játékos pozíciót létrehozni, ha arra nincsen fedezete. 

**Mentések** 

Az aktuális játékmenetet el lehet menteni, ez szabványos Java szerializációval történik. Az osztálystruktúrát úgy terveztem, hogy egyetlen **Game** típusú objektum szerialzálására legyen szükség. 

A cél az volt, hogy a játékmenet aktuális állapotát 100%-os pontossággal reprodukálni lehessen a létrejövő fájlból, ugyanakkor ez a lehető legkisebb fájlméret mellett történjen. 

A game objektum közvetlenül tartalmazza a részvényeket (így azok korábbi értékeit is), valamint a hozzá tartozó **Player** típusú objektumot. A player pedig nyilvántartja a nevét, tőkéjét, valamint az aktív és lezárt pozícióit. Fontos, hogy a GUI-objektumok mindenképpen kimaradnak a szerializációbol, hiszen azok csak grafikusan reprezentálják a business-logic objektumokat, azaz betöltés után újra felépíthetők, elmenteni őket felesleges redundancia lenne. 

Ennek eredményeképpen egy több órás játékmenet mérete is csupán pár száz KB. 

A mentések .ser kiterjesztést kapnak, ennek különösebb szerepe nincsen, de hasznos a felhasználó számára, hiszen indikálja, hogy szerializált objektumot tartalmaz. 

**Use-case diagram és leírások** 

![](Aspose.Words.86143563-45d8-4d51-8736-f1d8707bb6f9.016.png)
<img width="1434" alt="Screenshot 2021-12-26 at 18 28 08" src="https://user-images.githubusercontent.com/22593928/147415665-ae06bd76-568c-43e9-80db-170564a5e553.png">
- **Pozíció létrehozása**
  - Long vagy short típusú pozíció létrehozása egy választott részvényen, opcionálisan tőkeáttéttel. A funkció **Stocks** oldalon érhető majd el. Mindegyik pozíció kap egy saját id-t, később ez alapján (meg persze az értéke és a részvény neve alapján) lehet azonosítani.
- **Pozíció eladása**
  - A már meglévő pozíció eladása. **Active positions** oldalon érhető el. Fel vannak sorolva a pozíciók, mindegyikhez saját Sell button tartozik.
- **Árfolyamok megtekintése**
  - A részvények árfolyamának megtekintése a **Stocks** oldalon lehetséges, Az árfolyam-előzmények grafikonokkal vannak ábrázolva, amiket az egyes részvényekhez tartozó **Show chart** buttonnal lehet megnyitni, ezek automatikusan frissülnek is.
- **Játék elmentése**
  - A **Game** JMenuben található **Save game** opcióval elérhető, létrehoz egy mentést.
- **Mentett játék betöltése**
  - A **Game** JMenuben található **Open game** opcióval elérhető, egy mentés fájlt ki lehet választani és betölteni.
- **Nyitott pozíciók megtekintése**
  - **Active positions** oldalon van a felsorolás, ahol a pozíciók attribútumai mellett a kezdeti és aktuális értékük is látható.
- **Új játékos létrehozása**
  - A **Game** JMenuben található **New game** opcióval elérhető, meg kell adni a játékosnak a nevét, aki valamennyi kezdőtőkével lesz ellátva.
- **Lezárt pozíciók megtekintése**
  - Értelemszerűen a **Closed positions** oldalon vannakfelsorolva. Az elértéktelenedés miatt lezáródott pozíciók is ide tartoznak.
- **Árfolyamok változása**
  - 3 másodpercenként változik minden részvény értéke. A változás mértéke random van meghatározva, de úgy, hogy valósághű látszatot keltsen.




