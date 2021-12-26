

# Stock game <img width="60" align="left" alt="Screenshot 2021-12-26 at 17 18 10" src="https://user-images.githubusercontent.com/22593928/147414216-3f0a0a99-4232-43f7-b500-123d3916f310.png">

This was my homework for a third semester university course object-oriented programming in Java. The following documentation was created as part of the assignment, it doesn't serve any purpose other than satisfying the instructions given by the professor.

<img width="1920" alt="Screenshot 2021-12-26 at 15 50 09" src="https://user-images.githubusercontent.com/22593928/147411736-3136ad57-96ea-4eeb-a728-e9c9dad34f88.png">

Az oszálydiagramokat a konzultáción megbeszéltek szerint két részre bontottam, hogy átlátható maradjon. Az első osztálydiagram ábrázolja a business-logic, a második pedig a GUI- osztályokat.

<img width="1636" alt="Screenshot 2021-12-26 at 17 43 05 copy" src="https://user-images.githubusercontent.com/22593928/147415483-ab534a36-d34c-4167-b483-925c3880723b.png">
 
<img width="1441" alt="Screenshot 2021-12-26 at 18 19 55" src="https://user-images.githubusercontent.com/22593928/147415510-f756b157-b0a7-4bee-88dc-4c6d28d21b79.png">

## User manual and screenshots

1. **Új játék létrehozása** 

A program elindítása után az alábbi ablak látszódik: 

<img width="1162" alt="Screenshot 2021-12-26 at 15 32 14" src="https://user-images.githubusercontent.com/22593928/147411227-1c198e94-01a1-47fc-aece-b5005300aa82.png">

A **Game** menüben a **New game** feliratra kattintva a program megkérdezi a felhasználót, hogy mi legyen a játékos neve.

<img width="408" alt="Screenshot 2021-12-26 at 15 32 57" src="https://user-images.githubusercontent.com/22593928/147411270-e659c23b-51c8-4e1b-867e-0e232ddf3c28.png">
Miután ezt megadta, elindul a játék.

2. **Grafikonok megjelenítése** 

Minden részvény grafikonja megjeleníthető a hozzá tartozó **Show chart** gombbal. 
<img width="1162" alt="Screenshot 2021-12-26 at 15 36 16" src="https://user-images.githubusercontent.com/22593928/147411366-7779cc5f-fea6-45cf-bd3f-eb6a83723a1b.png">

Ekkor megjelenik a hozzá tartozó grafikon új ablakban. Minden részvény grafikonja külön ablakban jelenik meg és az idő előrehaladtával folyamatosan frissül. A **Show chart** gomb újabb megnyomásával a fókusz rákerül a grafikonjának az ablakára. Ezek az ablakok az átméretezés hatására nyúlni képesek, így létrehozható egy egyedi nézet, ideális képernyőkihasználtsággal, akár több kijelző esetén is.  

A JFreeChart ad néhány további hasznos funkciót: bele lehet nagyítani a grafikonokba, de akár PNG képet is lehet létrehozni belőlük stb. Ezek a funkciók a grafikonra jobb klikkelve érhetők el. 
<img width="672" alt="Screenshot 2egt021-12-26 at 15 54 19" src="https://user-images.githubusercontent.com/22593928/147412138-995ad041-01ab-4d98-963b-8c42332d9836.png">

3. **Pozíció létrehozása** 

A **Stock** fülre kell váltani a részvények megjelenítéséhez. Amennyiben a játékos arra szeretne spekulálni, hogy az adott részvény árfolyama nőni fog, a **Long** gombot kell választania, amennyiben a csökkenésre spekulálna, a **Short** gombot. 

A megjelenő ablakban kell megadni az összeget, amennyiért a pozíciót létre szeretné hozni. Ha ez esetleg meghaladná a játékos rendelkezésre álló tőkéjét, akkor a program természetesen nem engedi azt létrehozni. Ha egy érvényes érték lett megadva, felugrik egy következő ablak, amiben a használni kívánt tőkeáttétet lehet megadni.

<img width="408" alt="Screenshot 2021-12-26 at 16 05 34" src="https://user-images.githubusercontent.com/22593928/147412460-30b1356d-ee5d-4c9b-bba3-790e3a6547fe.png"><img width="480" alt="Screenshot 2021-12-26 at 16 17 25" src="https://user-images.githubusercontent.com/22593928/147412463-a962b0ef-333b-4f24-8b1b-448fd8a5ec5f.png">


A létrehozott pozíciót innentől az **Active Positions** oldalon lehet megtekinteni. 

4. **Pozíció eladása** 

Az **Active Positions** oldalon minden pozícióhoz tartozik egy **Sell** gomb, amivel el lehet adni. 

<img width="1162" alt="Screenshot 2021-12-26 at 16 23 04" src="https://user-images.githubusercontent.com/22593928/147412600-01b8222b-ca64-4cdd-a3fd-c8d96c1a109b.png">


A **Current value** paraméter mutatja, hogy ha az adott pillanatban eladná a játékos a pozícióját, akkor mennyi pénzt kapna érte. 

Bizonyos típusú pozíciók maguktól is elértéktelenedhetnek. Ilyen az összes SHORT típusú pozíció, valamint bármilyen (100%-nál nagyobb) tőkeáttéttel rendelkező pozíció. Ebben az esetben automatikusan lezáródik és átkerül a **Closed** **positions** oldalra.

5. **Játék elmentése** Mentéshez a **Game** menüben a **Save game** opciót kell választani, ekkor mejelenik az alábbi ablak:

<img width="654" alt="Screenshot 2021-12-26 at 16 28 02" src="https://user-images.githubusercontent.com/22593928/147412713-b1585e20-57a0-47e0-ad19-f41f045d3971.png">

Itt kiválaszthatja a felhasználó a mentés helyét, valamint megadhatja a fájl nevét, ami alapértelmetetten a játékos neve. A kiterjesztés mindéképpen .ser, mást nem enged menteni a program. És dob egy warningot: 
<img width="486" alt="Screenshot 2021-12-26 at 16 29 00" src="https://user-images.githubusercontent.com/22593928/147412751-87cdf0b1-c527-4889-9681-a73b733f1696.png">

6. **Mentett játék betöltése** 

A mentéshez hasonlóan működik: A **Game** menüben kell kiválasztani az **Open game** opciót, aminek hatására megnyílik a következő ablak: 

<img width="654" alt="Screenshot 2021-12-26 at 17 06 36" src="https://user-images.githubusercontent.com/22593928/147413785-7dc2aacd-65ab-432e-83f5-cf742fcbf8cb.png">

Itt .ser kiterjesztésű fájlokat tölthet be a felhasználó, de csak olyanokat, amiket ez a program hozott létre. Ellenkező esetben a betöltés sikertelen lesz és az alábbi hibaüzenet fog látszódni: 

<img width="409" alt="Screenshot 2021-12-26 at 17 18 10" src="https://user-images.githubusercontent.com/22593928/147414052-98f41c16-3690-4630-8a98-fddc66a11b13.png">

**Felhasznált külső könyvtárak és fordítás** 

A project **lib** mappájában találhatóak, szükség van rájuk a fordításhoz. 

- JFreeChart 
- Dracula look and feel 

Teszteléshez: 

- Junit (és a neki szükséges Hamcrest) 

A fejlesztést IntelliJ-ben végeztem, de teszteltem a kari felhőben is, Eclipse-ben is lefordul. Mellékelek egy futtatható jar fájlt is. A fordításkor a Dracula dob néhány warningot, ennek utána olvastam: ismert probléma amire még nincsen megoldás, de szerencsére nem befolyásol semmit, így figyelmen kívül lehet hagyni. 

**Az ikon** 

A flaticon.com egyik ingyenes ikonját módosítva készítettem el. A projekt **img** mappájában található. 



**Osztályok, adatszerkezetek** 

Az osztályokat 3 package-be szerveztem szerepük szerint. Mindegyik osztály (a Main) kivételével tagja valamelyik package-nek. Az osztályok belsejének (attribútumok, függvények) leírása a forráskódban szerepel JavaDoc kommentként. 

**Main** 

Létrehozza és megjeleníti a fő ablakot. 

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




